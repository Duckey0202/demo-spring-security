package com.mt.demo.springsecurity.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * ResultModel
 *
 * @author MT.LUO
 * 2018/1/12 10:39
 * @Description:
 */
public class ResultModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private Object object;

    public ResultModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultModel(int code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static ResultModel ok(String msg) {
        return new ResultModel(ResultCode.RESULT_CODE_OK, msg);
    }

    public static ResultModel ok(String msg, Object o) {
        return new ResultModel(ResultCode.RESULT_CODE_OK, msg, o);
    }

    public static ResultModel error(int code, String msg) {
        return new ResultModel(code, msg);
    }

    public static ResultModel error(int code, String msg, Object o) {
        return new ResultModel(code, msg, o);
    }

    public static ResultModel error(BindingResult result, MessageSource messageSource) {
        StringBuffer msg = new StringBuffer();
        //获取错误字段集合
        List<FieldError> fieldErrors = result.getFieldErrors();
        //获取本地locale,zh_CN
        Locale currentLocale = LocaleContextHolder.getLocale();
        //遍历错误字段获取错误消息
        for (FieldError fieldError : fieldErrors) {
            //获取错误信息
            String errorMessage = messageSource.getMessage(fieldError, currentLocale);
            //添加到错误消息集合内
            msg.append(fieldError.getField() + "：" + errorMessage + " , ");
        }
        return ResultModel.error(ResultCode.RESULT_CODE_ERROR, msg.toString());
    }

    @Override
    public String toString() {
        return '{' + "\"code\":" + code + ", \"msg\":\"" + msg  + "\", \"object\":" + object + "}";
    }
}
