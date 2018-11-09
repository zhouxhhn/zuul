/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * zuul常量
 */
public class ZuulConstants {

  public static final String authority = "siyueli-form";

  public static final String API_DOCS = "/v2/api-docs";

  public static final String API_SALES= "sales";

  public static final String API_SIYUELI = "siyueli";

  public static final String API_SIYUE = "siyue";

  /**
   * AuthorityFilter中的filterOrder大小
   */
  public static final int authorityOrder = 0;

  public static final int SIYUELI_AUTHORITY_ORDER = 5;

  /**
   * ErrorFilter中的filterOrder大小
   */
  public static final int errorOrder = 10;

  /**
   * ErrorExtFilter中的filterOrder大小，
   * errorExtOrder要大于errorOrder
   */
  public static final int errorExtOrder = 20;

  /**
   * 熔断器中返回给客户端的信息，该信息待定，确定好后再商量
   */
  public static final String responseBody = "{\"code\":\"500\",\"msg\":\"服务不可用，请稍后重试\",\"data\":null}";

  public static final int rawStatusCode = 200;

  public static final String statusText = "Service Unavailable";
}
