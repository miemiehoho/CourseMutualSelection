package edu.gdut.imis.courseMutualSelection.enums;

/**
 * 错误状态码
 *
 * @author miemiehoho
 * @date 2021/11/18 22:28
 */
public enum ErrorStatus {

    SYSTEM_ERROR(-9999, "系统异常"),
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
