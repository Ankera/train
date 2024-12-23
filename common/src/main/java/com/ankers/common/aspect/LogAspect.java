package com.ankers.common.aspect;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Aspect
@Component
public class LogAspect {

    public LogAspect() {
        System.out.println("Common LogAspect");
    }

    private final static Logger LOG  = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.ankers..*Controller.*(..))")
    public void controllerPointcut() {}

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {

        MDC.put("LOG_ID", System.currentTimeMillis() + RandomUtil.randomString(3));

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        LOG.info("===============开始请求===============");
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("请求方法: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("远程地址: {}", request.getRemoteAddr());


        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        // 排除字段，敏感字国或太长的字段不显示：身份证、手机号、邮箱、密码等
        String[] excludeProperties = {"mobile"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        LOG.info("请求参数: {}", JSONObject.toJSONString(arguments, excludeFilter));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        // 摧除字段，敏感字段或太长的字殿不显示：身份证、手机号、邮箱、密码等
        String[] excludeProperties = {"mobile"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        LOG.info("返回结果: {}", JSONObject.toJSONString(result, excludeFilter));
        long endTime = System.currentTimeMillis();
        LOG.info("=========== 请求结束 耗时: {} ms =============", endTime - startTime);
        return result;
    }
}
