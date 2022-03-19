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
    ACCOUNT_EXIST(10004, "账户已存在"),
    TOKEN_ERROR(10003, "TOKEN不合法"),
    NO_LOGIN(10004, "未登录"),
    UPLOAD_ERROR(20001, "上传文件失败"),
    SELECT_LIMIT(30001, "达到选题人数限制"),
    TOPIC_NULL(30002, "选题不存在"),
    SELECT_UNDERSTOCK(40001, "课程已经选满"),
    REPECT_SELECT(40002, "重复选课"),
    SELECT_QUEUE(40003, "选课排队中"),
    ILLEGAL_REQUEST(40004, "请求非法"),
    ACCESS_LIMIT(40005,"访问过于频繁"),
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
