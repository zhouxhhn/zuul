/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.service;

import cn.siyue.platform.base.ResponseData;
import cn.siyueli.platform.zuul.callback.AuthorityServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authority-service", path = "/siyueli/authority", fallback = AuthorityServiceFallBack.class)
public interface SiyueliAuthorityService {

  @RequestMapping(value = "/judgetAuthority", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData judgetAuthority(@RequestParam(value = "uri") String uri);
}

