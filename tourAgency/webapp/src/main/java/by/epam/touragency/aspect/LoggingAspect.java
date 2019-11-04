package by.epam.touragency.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LogManager.getLogger();

    @Pointcut("execution(* by.epam.touragency.repository.impl.*.*(..))")
    private void allLogEventMethods(){
    }

    @Before("allLogEventMethods()")
    public void logBefore(){
        LOGGER.debug("Starting executing");
    }

    @AfterReturning(pointcut = "execution(* by.epam.touragency.repository.impl.*.query(..))",
    returning = "retVal")
    public void logAfter(Object retVal){
        LOGGER.debug("Method returned value " + retVal);
    }

    @AfterThrowing(pointcut = "allLogEventMethods()", throwing = "e")
    public void logAfterThrowing(Exception e){
        LOGGER.error("The method ended with exception: ", e);
    }


}
