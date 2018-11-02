package cn.charlie166.web.base.controller;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.charlie166.web.store.tools.StringUtils;
import cn.charlie166.web.store.tools.TextUtils;

@RequestMapping(value = "/demo")
@RestController
public class DemoController extends BaseController {

	/***
	* @Title: tree 
	* @Description: 树形数据
	* @return
	 */
	@RequestMapping(value = "/tree")
	public Object tree(){
		Map<String, Object> root = Maps.newHashMap();
		root.put("name", "总部");
		root.put("open", Boolean.TRUE);
		Map<String, Object> c1 = Maps.newHashMap();
		c1.put("name", "学院1");
		c1.put("open", Boolean.TRUE);
		Map<String, Object> c1_1 = Maps.newHashMap();
		c1_1.put("name", "班级1_1");
		Map<String, Object> c1_1_1 = Maps.newHashMap();
		c1_1_1.put("name", "小组1_1_1");
		c1_1.put("children", Arrays.asList(c1_1_1));
		Map<String, Object> c1_2 = Maps.newHashMap();
		c1_2.put("name", "班级1_2");
		c1_2.put("checked", Boolean.TRUE);
		c1.put("children", Arrays.asList(c1_1, c1_2));
		Map<String, Object> c2 = Maps.newHashMap();
		c2.put("name", "学院2");
		Map<String, Object> c2_1 = Maps.newHashMap();
		c2_1.put("name", "班级2_1");
		Map<String, Object> c2_2 = Maps.newHashMap();
		c2_2.put("name", "班级2_2");
		Map<String, Object> c2_3 = Maps.newHashMap();
		c2_3.put("name", "班级2_3");
		c2.put("children", Arrays.asList(c2_1, c2_2, c2_3));
		root.put("children", Arrays.asList(c1, c2));
		return root;
	}
	
	/**
	* @Title: table 
	* @Description: 表格数据
	* @return
	 */
	@RequestMapping(value = "/table")
	public Object table(){
		Enumeration<String> names = this.request.getParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			if(StringUtils.hasContent(name)){
				String value = this.request.getParameter(name);
				this.logger.debug("name:[{}]-value:[{}]", name, value);
			}
		}
		Map<String, Object> tableMap = Maps.newHashMap();
		int count = 12;
		tableMap.put("total", Integer.valueOf(count));
		List<Map<String, Object>> rows = Lists.newArrayListWithCapacity(count);
		Random ran = new Random();
		for(int i = 1; i <= count; i++){
			Map<String, Object> row = Maps.newHashMap();
			row.put("id", Integer.valueOf(i));
			row.put("name", "第" + TextUtils.toSimpleChineseNum(i) + "号");
			row.put("address", "地址第" + TextUtils.toSimpleChineseNum(i) + "号");
			row.put("age", Integer.valueOf(18 + ran.nextInt(10)));
			rows.add(row);
		}
		tableMap.put("rows", rows);
		return tableMap;
	}
	
}