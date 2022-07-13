package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP 클래스 선언 : 부가 기능을 주입하는 클래스
@Component // IoC 컨테이너가 해당 객체를 생성 및 관리
@Slf4j // logging 기능
public class DebuggingAspect {
    // 대상 메소드 선택 : CommentSerice#create()
    // @Pointcut("execution(* com.example.firstproject.service.CommentService.create(..))")
    // 모든 메소드 선택 : *
    // @Pointcut("execution(* com.example.firstproject.service.CommentService.*(..))")
    // api 패키지의 모든 메소드
    @Pointcut("execution(* com.example.firstproject.api.*.*(..))")
    private void cut() {
    }

    // 실행 시점 설정
    @Before("cut()") // cut()의 대상이 수행 되기 이전
    public void loggingArgs(JoinPoint joinPoint) { // cut()의 대상 메소드
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스 명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드 명
        String methodName = joinPoint.getSignature()
                .getName();

        // 입력값 로깅하기
        // CommentService#create()의 입력값 => 5
        // CommentService#create()의 입력값 => CommentDto(id=null, ...)
        for (Object obj : args) { // 향상된 for문 . foreach 문
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);

        }
    }

    // 실행 시점 설정 cut()에 지정된 대상 호출 성공 후 실행
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, // cut()의 대상 메소드
                                   Object returnObj) { // 리턴 값
        // 클래스 명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드 명
        String methodName = joinPoint.getSignature()
                .getName();



        // 변환값 로깅
        // CommentService#create()의 변환값 => CommentDto(id=10, . . . .)
        log.info("{}#{}의 반환값 => {}",className, methodName, returnObj);
    }

}
