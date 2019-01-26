package com.syblackarrow.devplatform.Core;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControllerReturn {

    private ControllerReturnCode controllerReturnCode;
    private String extendsMessage;
    private Object data;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }

    public static ControllerReturn getControllerReturn(ControllerReturnCode controllerReturnCode, String message, Object data) {
        ControllerReturn cr = new ControllerReturn();
        cr.setControllerReturnCode(controllerReturnCode);
        if (StrUtil.isNotEmpty(message)) {
            cr.setExtendsMessage(message);
        }
        if (data != null) {
            cr.setData(data);
        }
        cr.setData(data);
        return cr;
    }

    public static String SUCCESS(String message) {
        return ControllerReturn.getControllerReturn(ControllerReturnCode.SUCCESS, message, null).toString();
    }

    public static String SUCCESS(String message, Object data) {
        return ControllerReturn.getControllerReturn(ControllerReturnCode.SUCCESS, message, data).toString();
    }

    public static String FAIL(String message) {
        return ControllerReturn.getControllerReturn(ControllerReturnCode.FAIL, message, null).toString();
    }

    public static String FAIL(String message, Object data) {
        return ControllerReturn.getControllerReturn(ControllerReturnCode.FAIL, message, data).toString();
    }
}
