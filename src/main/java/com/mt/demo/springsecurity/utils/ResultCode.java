package com.mt.demo.springsecurity.utils;

/**
 * ResultCode
 *
 * @author MT.LUO
 * 2018/1/12 10:46
 * @Description:
 */
public class ResultCode {
    public static int RESULT_CODE_OK = 200;
    public static int RESULT_CODE_ERROR = 400;
    public static int RESULT_CODE_PARAM_ERROR = 401;
    public static int RESULT_CODE_METHOD_NOT_SUPPORT = 402;
    public static int RESULT_CODE_MEDIA_NOT_SUPPORT = 403;

    public static int RESULT_CODE_LOGIN_ERROR = 300;
    public static int RESULT_CODE_LOGIN_FAIL = 301;
    public static int RESULT_CODE_AUTH_LIMIT = 302;
    public static int RESULT_CODE_SERVER_ERROR = 500;
    public static int RESULT_CODE_DATABASE_ERROR = 501;


    /* 用户名重复 */
    public static int RESULT_CODE_USERNAME_EXIST = 601;
    /* 用户不存在 */
    public static int RESULT_CODE_USER_NOT_EXIST = 602;
}
