/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import cn.siyueli.platform.zuul.constants.ZuulConstants;

import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 处理post过滤器引起的异常
 */
@Component
public class ExceptionExtFilter extends SendErrorFilter {

  @Override
  public String filterType() {
    return FilterConstants.ERROR_TYPE;
  }

  @Override
  public int filterOrder() {
    // 大于ErrorFilter的值
    return ZuulConstants.errorExtOrder;
  }

  @Override
  public boolean shouldFilter() {
    // 判断：仅处理来自post过滤器引起的异常
    RequestContext ctx = RequestContext.getCurrentContext();
    ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");
    return failedFilter != null && "post".equals(failedFilter.filterType());
  }
}
