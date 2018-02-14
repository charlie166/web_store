package org.apache.taglibs.standard.tag.rt.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import org.apache.taglibs.standard.tag.common.core.ParamSupport;
import org.apache.taglibs.standard.tag.common.core.UrlSupport;
import org.apache.taglibs.standard.util.UrlUtil;
import cn.charlie166.web.store.tools.WebUtils;

/**
* @ClassName: UrlTag 
* @Description: 覆写默认jstl中的url标签
* @company 
* @author liyang
* @Email charlie166@163.com
* @date 2018年2月7日 
*
 */
public class UrlTag extends UrlSupport {

	private static final long serialVersionUID = 1L;
	
    //*********************************************************************
    // Private state

    private String var;                          // 'var' attribute
    private int scope;                 // processed 'scope' attr
    private ParamSupport.ParamManager params;     // added parameters

    //*********************************************************************
    // Constructor and initialization

    public UrlTag() {
        super();
        init();
    }

    private void init() {
        value = var = null;
        params = null;
        context = null;
        scope = PageContext.PAGE_SCOPE;
    }
    
    public void setValue(String value) throws JspTagException {
        this.value = value;
    }

    public void setContext(String context) throws JspTagException {
        this.context = context;
    }


    //*********************************************************************
    // Collaboration with subtags

    // inherit Javadoc

    public void addParameter(String name, String value) {
        params.addParameter(name, value);
    }


    //*********************************************************************
    // Tag logic

    // resets any parameters that might be sent

    @Override
    public int doStartTag() throws JspException {
        params = new ParamSupport.ParamManager();
        return EVAL_BODY_BUFFERED;
    }


    // gets the right value, encodes it, and prints or stores it

    @Override
    public int doEndTag() throws JspException {
        String result;                // the eventual result
        // add (already encoded) parameters
        String baseUrl = this.resolveCustomUrl(value, context, pageContext);
        result = params.aggregateParams(baseUrl);
        // if the URL is relative, rewrite it
        if (!UrlUtil.isAbsoluteUrl(result)) {
            HttpServletResponse response = ((HttpServletResponse) pageContext.getResponse());
            result = response.encodeURL(result);
        }
        /**清除掉链接中的jsession参数**/
        result = WebUtils.wipeSessionId(result);
        // store or print the output
        if (var != null) {
            pageContext.setAttribute(var, result, scope);
        } else {
            try {
                pageContext.getOut().print(result);
            } catch (java.io.IOException ex) {
                throw new JspTagException(ex.toString(), ex);
            }
        }
        return EVAL_PAGE;
    }

    // Releases any resources we may have (or inherit)

    @Override
    public void release() {
        init();
    }


    /**
    * @Title: resolveCustomUrl 
    * @Description: 自定义处理地址
    * @param url 标签内设置的地址值
    * @param context 标签内设置的上下文
    * @param pageContext 
    * @return
    * @throws JspException
     */
    private String resolveCustomUrl(String url, String context, PageContext pageContext) throws JspException {
        /*** don't touch absolute URLs ***/
        if (UrlUtil.isAbsoluteUrl(url)) {
            return url;
        }
        /** normalize relative URLs against a context root **/
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        return WebUtils.resolveCustomUrl(url, context, request);
    }
    
}