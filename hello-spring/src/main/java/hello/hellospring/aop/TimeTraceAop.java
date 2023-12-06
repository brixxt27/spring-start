package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {
    @Around("!execution(* hello.hellospring.config..*(..))"
            + "&& execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Start: " + joinPoint.toString());
        long start = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeDiff = finish - start;
            System.out.println("End " + joinPoint.toString() + " " + timeDiff + " ms");
        }
    }
}
