/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.filter;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 过滤器出现异常时，添加属性，
 * 为了在ErrorExtFilter的shouldFilter方法中获取并以此判断异常是否来自post阶段的过滤器
 */
public class FilterExceptionProcessor extends FilterProcessor {

  @Override
  public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
    try {
      return super.processZuulFilter(filter);
    } catch (ZuulException e) {
      RequestContext ctx = RequestContext.getCurrentContext();
      ctx.set("failed.filter", filter);
      throw e;
    }
  }
}
