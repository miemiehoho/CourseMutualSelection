package edu.gdut.imis.courseMutualSelection.handler;

import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author miemiehoho
 * @date 2021/11/18 22:13
 */
// 对加了@controller注解的方法进行拦截处理 AOP的实现
@ControllerAdvice
public class AllExceptionHandler {

    // 进行异常处理，处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回json格式数据，不加这个注解返回的是页面
    public Result doException(Exception e) {
        e.printStackTrace(); // 简介打印栈堆信息 TODO 后续加上日志处理，记录到日志里
        return Result.fail(ErrorStatus.SYSTEM_ERROR.getCode(), ErrorStatus.SYSTEM_ERROR.getMsg());
    }
}
