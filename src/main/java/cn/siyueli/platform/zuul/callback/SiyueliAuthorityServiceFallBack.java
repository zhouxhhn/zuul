/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.siyueli.platform.zuul.callback;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyueli.platform.zuul.service.SiyueliAuthorityService;
import org.springframework.stereotype.Component;

@Component
public class SiyueliAuthorityServiceFallBack implements SiyueliAuthorityService {

  @Override
  public ResponseData judgetAuthority(String uri) {
    return ResponseData.build(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());
  }
}
