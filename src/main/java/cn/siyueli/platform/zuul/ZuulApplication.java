/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul;

import com.netflix.zuul.FilterProcessor;
import cn.siyueli.platform.zuul.filter.FilterExceptionProcessor;
import cn.siyueli.platform.zuul.hystrix.ServiceFallbackProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * zuul应用入口
 */
@EnableFeignClients
@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZuulApplication.class, args);

    //启用自定义的核心处理器，来完成对post过滤器异常的处理
    FilterProcessor.setProcessor(new FilterExceptionProcessor());
  }

  /**
   * 实例化熔断对象
   */
  @Bean
  public FallbackProvider routeZuulFallbackProvider() {
    return new ServiceFallbackProvider();
  }

}
