package edu.gdut.imis.courseMutualSelection.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一返回值
 *
 * @author miemiehoho
 * @date 2021/11/18 22:16
 */
@Data
@AllArgsConstructor // 自动生成所有构造方法
public class Result {
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    /**
     * 成功的方法
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    /**
     * 失败的方法
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result fail(int code, String msg) {
        return new Result(false, code, msg, null);
    }
}
