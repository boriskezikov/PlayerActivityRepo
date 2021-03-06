package com.example.lesson1.advice;

import com.example.lesson1.entity.PlayerEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AdviceProcessor {


    @Pointcut("@annotation(com.example.lesson1.advice.Audit)")
    public void annotated() {
    }


    @Pointcut("execution(public * com.example.lesson1.service.PlayerService.*(..))")
    public void playerServiceMethods() {
    }


    @Before("annotated()")
    public void printMethodInfo(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String name = jp.getSignature().getName();
        Class declaringType = jp.getSignature().getDeclaringType();
        log.info("aspect() - method {}.{} called with params: {}", declaringType, name, args);
    }

    @AfterReturning(value = "playerServiceMethods()", returning = "entity")
    public void printAllMethodsRetunrs(JoinPoint jp, PlayerEntity entity) {
        Object[] args = jp.getArgs();
        String name = jp.getSignature().getName();
        Class declaringType = jp.getSignature().getDeclaringType();
        log.info("aspect() - method {}.{} called with params: {}. Returned: {}", declaringType, name, args, entity);
    }

//    @Pointcut("@annotation(com.example.lesson1.advice.SetTerminated) && args(user,..)")
//    public void aroundProcess(PlayerEntity user) {}
//
//    @Around(value = "aroundProcess(user)")
//    public void changeState(ProceedingJoinPoint pjp, PlayerEntity user) throws Throwable {
//        user.setTerminated(true);
//        pjp.proceed();
//    }

}
