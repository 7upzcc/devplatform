/*
 * -------------------------------------------------
 * 通用的服务层返回包装，其中包括了一组自定义的返回代码，返回
 * 的扩展消息字符串，和一个可以携带返回内容的对象
 * 2019年03月19日 赵昌昌
 * -------------------------------------------------
 */
package com.syblackarrow.devplatform.Core;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceReturn {
    /*
     * ---------------------------------------------
     * 成员变量部分：
     * serviceReturnCode:一个自定义的返回代码枚举类型
     * extendsMessage:一个扩展消息字符串
     * data：一个可以携带返回内容的对象
     * ---------------------------------------------
     */
    private ServiceReturnCode serviceReturnCode;
    private String extendsMessage = null ;
    private Object data;

    /*
     * ---------------------------------------------
     * 覆盖toString方法。返回json结构的字符串
     * ---------------------------------------------
     */
    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }

    /*
     * ---------------------------------------------
     * ServiceReturn的初始化方法，用来获得一个自定义的
     * ServiceReturn实例
     * ---------------------------------------------
     */
    public static ServiceReturn getServiceReturn(ServiceReturnCode serviceReturnCode, String message, Object data) {
        ServiceReturn sr = new ServiceReturn();
        sr.setServiceReturnCode(serviceReturnCode);
        if (StrUtil.isNotEmpty(message)) {
            sr.setExtendsMessage(message);
        }
        if (data != null) {
            sr.setData(data);
        }
        sr.setData(data);
        return sr;
    }

    /*
     * ---------------------------------------------
     * 一个静态的快捷方法，直接返回一个携带成功代码的
     * ServiceReturn实例
     * 其中自定义了成功消息的文本
     * ---------------------------------------------
     */
    public static String SUCCESS(String message) {
        return ServiceReturn.getServiceReturn(ServiceReturnCode.SUCCESS, message, null).toString();
    }

    /*
     * ---------------------------------------------
     * 一个静态的快捷方法，直接返回一个携带成功代码的
     * ServiceReturn实例
     * 其中自定义了成功消息的文本和一个自定义的返回对象
     * ---------------------------------------------
     */
    public static String SUCCESS(String message, Object data) {
        return ServiceReturn.getServiceReturn(ServiceReturnCode.SUCCESS, message, data).toString();
    }

    /*
     * ---------------------------------------------
     * 一个静态的快捷方法，直接返回一个携带失败代码的
     * ServiceReturn实例
     * 其中自定义了失败消息的文本
     * ---------------------------------------------
     */
    public static String FAIL(String message) {
        return ServiceReturn.getServiceReturn(ServiceReturnCode.FAIL, message, null).toString();
    }

    /*
     * ---------------------------------------------
     * 一个静态的快捷方法，直接返回一个携带失败代码的
     * ServiceReturn实例
     * 其中自定义了失败消息的文本和一个自定义的返回对象
     * ---------------------------------------------
     */
    public static String FAIL(String message, Object data) {
        return ServiceReturn.getServiceReturn(ServiceReturnCode.FAIL, message, data).toString();
    }
}
