package edu.gdut.imis.courseMutualSelection.enums;

/**
 * 错误状态码
 *
 * @author miemiehoho
 * @date 2021/11/18 22:28
 */
public enum ErrorStatus {

    SYSTEM_ERROR(-9999, "系统异常"),
    PARAMS_ERROR(10001, "参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002, "用户名或密码不存在"),
    TOKEN_ERROR(10003, "TOKEN不合法"),
    NO_LOGIN(10004, "未登录"),
    ;
    private int code;
    private String msg;

    ErrorStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
