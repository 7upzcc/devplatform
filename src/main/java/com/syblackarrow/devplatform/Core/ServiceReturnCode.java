package com.syblackarrow.devplatform.Core;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

public enum ServiceReturnCode {

    FAIL("操作失败", -1),
    SUCCESS("操作成功", 0);

    private String name;
    private Integer code;

    private ServiceReturnCode(String name, Integer code) {
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
