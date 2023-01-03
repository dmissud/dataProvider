package org.dbs.biblio.dataprovider.application.common;

import com.codahale.metrics.Timer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class LogAspect {
    @Around(value = "@annotation(org.dbs.biblio.dataprovider.application.common.LogExecutionTime)")
    public Object metricGlobal(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature method = (MethodSignature) proceedingJoinPoint.getSignature();
        LogExecutionTime ann = method.getMethod().getAnnotation(LogExecutionTime.class);
        Timer timer = new Timer();
        try (Timer.Context context = timer.time()) {
            try {
                return proceedingJoinPoint.proceed();
            } finally {
                double timeInSecond = context.stop() / 1000000000.0;
                log.info("Temps écoulé pour {}:{}({}) : {} sec", ann.vType(), ann.vApi(), ann.vMethod(), timeInSecond);
            }
        }
    }
}
