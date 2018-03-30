package cn.charlie166.web.weixin.service.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import cn.charlie166.web.base.service.impl.BaseServiceImpl;
import cn.charlie166.web.store.constant.CustomException;
import cn.charlie166.web.store.constant.ExceptionCodes;
import cn.charlie166.web.store.service.inter.AttachmentService;
import cn.charlie166.web.store.tools.JsonUtils;
import cn.charlie166.web.store.tools.StringUtils;
import cn.charlie166.web.store.tools.WebUtils;
import cn.charlie166.web.weixin.domain.WeixinMenu;
import cn.charlie166.web.weixin.domain.dto.WeixinResponseBaseDTO;
import cn.charlie166.web.weixin.domain.dto.WeixinTokenDTO;
import cn.charlie166.web.weixin.domain.dto.WeixinUserDTO;
import cn.charlie166.web.weixin.service.inter.WeixinBaseService;

/***
* @ClassName: WeixinBaseServiceImpl 
* @Description: 微信基础服务实现类
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年3月28日 
*
 */
public class WeixinBaseServiceImpl extends BaseServiceImpl implements WeixinBaseService {

	@Value(value = "${weixin.server.url}")
	protected String baseUrl;
	@Value(value = "${weixin.id}")
	protected String id;
	@Value(value = "${weixin.secret}")
	protected String secret;
	@Value(value = "${weixin.token}")
	protected String token;
	
	/**用于存储微信token的文件，避免短时间重复获取***/
	private static final String WEIXIN_TOKEN_FILE = "weixin_token.txt";
	
	@Autowired
	private AttachmentService attachmentService;

	@Override
	public String getBaseUrl() {
		return this.baseUrl;
	}

	@Override
	public String getAccessToken() {
		return this.handleToken();
	}
	
	/**
	* @Title: handleToken 
	* @Description: 从文件处理token，如果要过期了，就重新请求
	* @return
	 */
	private String handleToken(){
		String content = attachmentService.getStringOrDefault(WEIXIN_TOKEN_FILE, "");
		if(StringUtils.hasContent(content)){
			int ind = content.indexOf(":");
			String timeStr = content.substring(0, ind);
			if(timeStr.matches("\\d+")){
				LocalDateTime timeOfGet = Instant.ofEpochMilli(Long.valueOf(timeStr)).atZone(ZoneId.systemDefault()).toLocalDateTime();
				/**微信是2个小时有效期，这里设置成1个小时多**/
				LocalDateTime shouldRefreshTime = timeOfGet.plusHours(1).plusMinutes(40);
				/**时间还在有效期**/
				if(LocalDateTime.now().isBefore(shouldRefreshTime)){
					return content.substring(ind + 1);
				}
			}
		}
		/**没有有效的token，发送请求重新获取**/
		StringBuilder thisUrl = new StringBuilder();
		LocalDateTime now = LocalDateTime.now();
		thisUrl.append(this.getBaseUrl()).append("token?grant_type=client_credential&appid=").append(this.id).append("&secret=").append(this.secret);
		try {
			HttpResponse httpResponse = WebUtils.weixinGet(thisUrl.toString());
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value()){
				String result = EntityUtils.toString(httpResponse.getEntity(), Charset.defaultCharset());
				WeixinTokenDTO json = JsonUtils.fromJson(result, WeixinTokenDTO.class);
				if(json != null){
					if(json.getExpires_in() != null && StringUtils.hasContent(json.getAccess_token())){
						/**保存内容到文件**/
						String storeContext = now.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond() * 1000 + ":" + json.getAccess_token();
						attachmentService.saveString(storeContext, WEIXIN_TOKEN_FILE);
						return json.getAccess_token();
					} else {
						logger.error("获取微信token失败, 错误码: {}, 错误信息: {}", json.getErrcode(), json.getErrmsg());
					}
				} else {
					logger.error("获取微信token返回无法解析对象");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw CustomException.instance(ExceptionCodes.WEIXIN_GET_TOKEN_FAILED);
	}

	@Override
	public void refreshMenu() {
		WeixinMenu firstMenu = new WeixinMenu();
		firstMenu.setName("来啊");
		firstMenu.setType("location_select");
		WeixinMenu menu = new WeixinMenu();
		menu.setButton(Arrays.asList(firstMenu));
		StringBuilder thisUrl = new StringBuilder();
		thisUrl.append(this.getBaseUrl()).append("menu/create?access_token=").append(this.getAccessToken());
		try {
			HttpResponse hr = WebUtils.weixinPost(thisUrl.toString(), menu);
			if(hr.getStatusLine().getStatusCode() == HttpStatus.OK.value()){
				String result = EntityUtils.toString(hr.getEntity(), Charset.defaultCharset());
				WeixinResponseBaseDTO json = JsonUtils.fromJson(result, WeixinResponseBaseDTO.class);
				if(json != null){
					if(json.getErrcode() == null || json.getErrcode().intValue() == 0){
						logger.debug("刷新微信菜单成功");
					}
				} else {
					logger.error("刷新微信菜单返回无法解析对象");
				}
				this.throwWeixinException(json, "刷新微信菜单");
			} else {
				logger.error("微信响应返回码: {}", hr.getStatusLine().getStatusCode());
				throw CustomException.instance(ExceptionCodes.WEIXIN_REQUEST_FAILED);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("刷新微信菜单失败", e);
			throw CustomException.instance(ExceptionCodes.WEIXIN_REQUEST_FAILED);
		}
	}
	
	/**
	* @Title: throwWeixinException 
	* @Description:
	* @param dto
	 */
	private void throwWeixinException(WeixinResponseBaseDTO dto, String tip){
		StringBuilder msg = new StringBuilder(StringUtils.hasContent(tip) ? tip : "微信请求失败");
		if(dto != null){
			if(dto.getErrcode() != null){
				msg.append(", 错误码: " + dto.getErrcode());
			}
			if(StringUtils.hasContent(dto.getErrmsg())){
				msg.append(", 错误信息: " + dto.getErrmsg());
			}
		}
		throw CustomException.instance(ExceptionCodes.WEIXIN_REQUEST_FAILED, msg.toString());
	}

	@Override
	public WeixinUserDTO userInfo(String openId) {
		if(!StringUtils.hasContent(openId))
			throw CustomException.instance(ExceptionCodes.COMMON_PARAM_ABSENT);
		StringBuilder thisUrl = new StringBuilder();
		thisUrl.append(this.getBaseUrl()).append("user/info?access_token=").append(this.getAccessToken()).append("&openid=").append(openId).append("&lang=zh_CN");
		try {
			HttpResponse hr = WebUtils.weixinGet(thisUrl.toString());
			if(hr.getStatusLine().getStatusCode() == HttpStatus.OK.value()){
				String result = EntityUtils.toString(hr.getEntity(), Charset.defaultCharset());
				WeixinUserDTO json = JsonUtils.fromJson(result, WeixinUserDTO.class);
				if(json != null){
					if(json.getErrcode() == null || json.getErrcode().intValue() == 0){
						return json;
					}
				} else {
					logger.error("请求获取微信用户信息返回无法解析对象");
				}
				this.throwWeixinException(json, "获取微信用户信息");
			} else {
				logger.error("请求获取微信用户信息-微信响应返回码: {}", hr.getStatusLine().getStatusCode());
				throw CustomException.instance(ExceptionCodes.WEIXIN_REQUEST_FAILED);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("请求获取微信用户信息失败", e);
		}
		throw CustomException.instance(ExceptionCodes.WEIXIN_REQUEST_FAILED);
	}
}