/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.siyue.platform.base.ResponseData;
import cn.siyueli.platform.zuul.callback.AuthorityServiceFallBack;

@FeignClient(name = "authority-service", path = "/sales/authority", fallback = AuthorityServiceFallBack.class)
public interface AuthorityService {

  @RequestMapping(value = "/judgetAuthority", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData judgetAuthority(@RequestParam(value = "uri")  String uri);
}

