package com.syblackarrow.devplatform.Core;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

public enum UserSettingsCode {

    IS_UPLOAD("is_upload", "是否允许登录"),
    FILE_SIZE("file_size","上传文件最大限制");

    private String name;
    private String content;

    private UserSettingsCode(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public String toString() {
        Map thisMap = new HashMap();
        thisMap.put("name", this.name);
        thisMap.put("content", this.content);
        return JSONUtil.toJsonStr(thisMap);
    }

    public String getName(){
        return this.name ;
    }

}
