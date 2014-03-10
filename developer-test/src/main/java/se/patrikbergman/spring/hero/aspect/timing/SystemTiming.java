package se.patrikbergman.spring.hero.aspect.timing;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class SystemTiming {

    private static final Logger log = Logger.getLogger(SystemTiming.class);

    @Around("@annotation( timedAnnotation ) ")
    public Object processSystemRequest(final ProceedingJoinPoint pjp, Timed timedAnnotation) throws Throwable {
        try {
            long start = System.currentTimeMillis();
            Object retVal = pjp.proceed();
            long end = System.currentTimeMillis();
            long differenceMs = end - start;

            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            Method targetMethod = methodSignature.getMethod();

            log.info(targetMethod.getDeclaringClass().getSimpleName() + "." + targetMethod.getName() + "() took " + differenceMs + " ms");

            return retVal;

        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }
}
