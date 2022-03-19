package edu.gdut.imis.courseMutualSelection.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.utils.UserThreadLocal;
import edu.gdut.imis.courseMutualSelection.validation.AccessLimit;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author miemiehoho
 * @date 2022/3/19 11:22
 */
@Component
public class AccessLimitInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int second = accessLimit.second();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = null;
            if (needLogin) {
                key = request.getRequestURI() + ":" + UserThreadLocal.get().getId();
            } else {
                key = request.getRequestURI();
            }
            Integer count = (Integer) redisTemplate.opsForValue().get(key);
            if (count == null) {
                redisTemplate.opsForValue().set(key, 0, second, TimeUnit.SECONDS);
                return true;
            } else if (count < maxCount) {
                redisTemplate.opsForValue().increment(key);
                return true;
            } else {
                render(response, ErrorStatus.ACCESS_LIMIT);
                return false;
            }
        }
        return true;
    }

    /**
     * 构建返回对象
     *
     * @param response
     * @param accessLimit
     */
    private void render(HttpServletResponse response, ErrorStatus accessLimit) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(Result.success(accessLimit)));
        writer.flush();
        writer.close();
    }
}
