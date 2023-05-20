package aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public aspect Logging {
    private static final Logger logger = LogManager.getLogger();

    pointcut publicMethods(): execution(public * domain..*(..))
            || execution(public * observer..*(..))
            || execution(public * repository..*(..))
            || execution(public * service..*(..))
            || execution(public * controller..*(..))
            || execution(public * utils..*(..))
            || execution(public * main..*(..));

    before(): publicMethods() {
        logger.traceEntry("{}; arguments = {}", thisJoinPointStaticPart.getSignature(), thisJoinPoint.getArgs());
    }

    after() returning(Object returnValue): publicMethods() {
        if (returnValue == null) {
            logger.traceExit(thisJoinPointStaticPart.getSignature().toString());
        } else {
            logger.traceExit(thisJoinPointStaticPart.getSignature().toString() + " = {}", returnValue);
        }
    }

    after() throwing(Throwable ex): publicMethods() {
        logger.error("{} throws {}", thisJoinPointStaticPart.getSignature(), ex);
    }
}
