/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.hystrix;

import cn.siyueli.platform.zuul.constants.ZuulConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 网关熔断处理
 * 如果网关后面的微服务挂了，定义一个fallback类，用于熔断处理
 */
@Component
public class ServiceFallbackProvider implements FallbackProvider {

  private String responseBody = ZuulConstants.responseBody;

  private Logger log = LoggerFactory.getLogger(ServiceFallbackProvider.class);

  /**需要回退的服务名称，必须是Consul中注册的，全部用*
   * 指定要处理的微服务实例，该例表示对所有的微服务进行加入熔断器回退
   */
  @Override
  public String getRoute() {
    return "*";
  }

  @Override
  public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
    return new ClientHttpResponse() {
      @Override
      public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.OK;
      }

      @Override
      public int getRawStatusCode() throws IOException {
        return this.getStatusCode().value();
      }

      @Override
      public String getStatusText() throws IOException {
        return this.getStatusCode().getReasonPhrase();
      }

      @Override
      public void close() {

      }

      @Override
      public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(responseBody.getBytes("utf-8"));
      }

      @Override
      public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
        headers.setContentType(mt);
        return headers;
      }
    };
  }
}
