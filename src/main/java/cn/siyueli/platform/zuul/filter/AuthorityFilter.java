/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.filter;

import cn.siyueli.platform.zuul.util.ProjectMatchUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyueli.platform.zuul.constants.ZuulConstants;
import cn.siyueli.platform.zuul.service.AuthorityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**头信息中的authority验证、用户信息的解析
 */
@Component
public class AuthorityFilter extends ZuulFilter {

  @Autowired
  private AuthorityService authorityService;

  private static Logger log = LoggerFactory.getLogger(AuthorityFilter.class);

  @Override
  public String filterType() {
    return FilterConstants.PRE_TYPE;
  }


  @Override
  public int filterOrder() {
    return ZuulConstants.authorityOrder;
  }

  @Override
  public boolean shouldFilter() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    String uri = request.getRequestURI();
    if (ProjectMatchUtil.isSiyueli(uri)) {
      return false;
    }
    return false;
  }

  /**
   * 拦截url，对url进行权限控制的具体实现
   */
  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    String uri = request.getRequestURI();
    try{

      // 访问swagger api文档或者访问公共模块，放行
      // /api/siyueli-member/v2/api-docs

      boolean apiDocsFlag = Pattern.matches("^/api/[\\s\\S]+/v2/api-docs$", uri);

      if(apiDocsFlag){
        return null;
      }

      ResponseData responseData =  authorityService.judgetAuthority(uri);
      if(ResponseBackCode.SUCCESS.getValue() != responseData.getCode()){
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        ctx.setResponseBody("{\"code\":"+responseData.getCode()+",\"msg\":\""+responseData.getMsg()+"\",\"data\":\""+responseData.getData()+"\"}");
        ctx.getResponse().setContentType("text/html;charset=UTF-8");
        return null;
      }
    }catch (Exception e){
      ctx.setSendZuulResponse(false);
      ctx.setResponseStatusCode(401);
      ctx.setResponseBody("{\"code\":"+ResponseBackCode.ERROR_FAIL.getValue()+",\"msg\":\""+"权限服务没开启"+"\",\"data\":"+null+"}");
      ctx.getResponse().setContentType("text/html;charset=UTF-8");
      return null;
    }
    return null;
  }
}
