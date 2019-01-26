package com.syblackarrow.devplatform.Core;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

public enum ControllerReturnCode {
    FAIL("操作失败", -1),
    PAGE_NOT_FINDED("页面没找到", 404),
    SUCCESS("操作成功", 0),
    USER_NOT_FINDED("用户没找到", 1000);

    private String name;
    private Integer code;

    private ControllerReturnCode(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        Map thisMap = new HashMap();
        thisMap.put("message",this.name) ;
        thisMap.put("code",this.code) ;
        return JSONUtil.toJsonStr(thisMap);
    }

}
