/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.callback;

import org.springframework.stereotype.Component;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyueli.platform.zuul.service.AuthorityService;

@Component
public class AuthorityServiceFallBack implements AuthorityService {

  @Override
  public ResponseData judgetAuthority(String uri) {
    return ResponseData.build(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());
  }
}
