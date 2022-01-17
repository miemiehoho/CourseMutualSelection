package edu.gdut.imis.courseMutualSelection.handler;

import com.alibaba.fastjson.JSON;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.service.LoginService;
import edu.gdut.imis.courseMutualSelection.utils.UserThreadLocal;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author miemiehoho
 * @date 2021/11/23 23:48
 */
@Component // 让spring扫描到
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在调用controller方法（handler）之前会执行这个方法
        /**
         * 1.判断请求的接口路径是否为handlerMethod
         * 2.判断token是否为空，如果为空，未登录
         * 3.token如果不为空进行登录验证
         * 4.如果认证成功，放行
         */
        if (!(handler instanceof HandlerMethod)) {
            // handler可能是资源RequestResourceHandler，springboot程序访问静态资源默认去classpath下的staticm目录去查询
            return true;
        }
        String token = request.getHeader("Authorization");
        // 日志，暂时放这里
        log.info("===========================request start=================================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}", requestURI);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("===========================request end========================");
        if (StringUtils.isBlank(token)) {
            Result result = Result.fail(ErrorStatus.NO_LOGIN.getCode(), ErrorStatus.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        User user = loginService.checkToken(token);
        if (user == null) {
            Result result = Result.fail(ErrorStatus.NO_LOGIN.getCode(), ErrorStatus.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        UserThreadLocal.put(user);
        return true;
    }

    /**
     * 方法执行完毕的处理
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 如果不删除ThreadLocal中用完的信息，会有内存泄漏的风险
        UserThreadLocal.remove();
    }
}
