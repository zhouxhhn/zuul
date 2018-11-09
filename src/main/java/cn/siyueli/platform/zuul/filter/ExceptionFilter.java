/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import cn.siyueli.platform.zuul.constants.ZuulConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 异常过滤器
 */
@Component
public class ExceptionFilter extends ZuulFilter {

  private static Logger log = LoggerFactory.getLogger(ExceptionFilter.class);

  @Override
  public String filterType() {
    return FilterConstants.ERROR_TYPE;
  }

  @Override
  public int filterOrder() {
    return ZuulConstants.errorOrder;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    RequestContext ctx = RequestContext.getCurrentContext();
    Throwable throwable = ctx.getThrowable();
    log.error("this is a ErrorFilter :" + throwable.getCause().getMessage(), throwable);
    ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    ctx.set("error.exception", throwable.getCause());
    return null;
  }
}
