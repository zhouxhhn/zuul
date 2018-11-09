/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.config;

import com.spring4all.swagger.EnableSwagger2Doc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@EnableSwagger2Doc
@Configuration
@ConfigurationProperties(prefix = "swagger-custom")
public class SwaggerDocConfig implements SwaggerResourcesProvider {

  private List<String> swaggerSpec;

  public List<String> getSwaggerSpec() {
    return swaggerSpec;
  }

  public void setSwaggerSpec(List<String> swaggerSpec) {
    this.swaggerSpec = swaggerSpec;
  }

  @Override public List<SwaggerResource> get() {
    List resources = new ArrayList<>();
    swaggerSpec.forEach(resource -> {
      resources.add(swaggerResource(resource, resource+"/v2/api-docs", "1.0"));
    });

    return resources;
  }

  private SwaggerResource swaggerResource(String serviceName, String location, String version) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(serviceName);
    swaggerResource.setLocation("/api/"+location);
    // 设置版本
    swaggerResource.setSwaggerVersion(version);
    return swaggerResource;
  }
}
