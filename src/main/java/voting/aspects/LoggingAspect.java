package voting.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by andrius on 2/26/17.
 */

@Component
@Aspect
public class LoggingAspect {

    private final Logger logger = Logger.getLogger(LoggingAspect.class);

    // nurodomos vietos kurios bus naudojamos advaise - pointcuts
    @Pointcut("execution(* voting.Application.main(..))")
    protected void executeMain() {}

    @Before("executeMain()") // pass pointcut name - where it is needed to use advice
    public void beforeExecuteMain(JoinPoint jp) {
        System.out.println("Application STARTED");
        logger.info("Application started!");
    }
}
