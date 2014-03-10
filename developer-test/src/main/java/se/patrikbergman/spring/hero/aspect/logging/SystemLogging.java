package se.patrikbergman.spring.hero.aspect.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class SystemLogging {

    private static final Logger log = Logger.getLogger(SystemLogging.class);

    @Around("@annotation( loggedAnnotation ) ")
    public Object processSystemRequest(final ProceedingJoinPoint pjp, Logged loggedAnnotation) throws Throwable {
        try {
            Object retVal = pjp.proceed();

            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            Method targetMethod = methodSignature.getMethod();

            log.info("Invoked " + targetMethod.getDeclaringClass().getSimpleName() + "." + targetMethod.getName() + "()");

            return retVal;

        } catch (Throwable t) {
            log.error(t.getMessage());
            throw t;
        }
    }
}
