package aspects;

import java.util.logging.Level;
import java.util.logging.Logger;

public aspect SystemMonitoringAspect {
    private final Logger logger = Logger.getLogger("Contest");

    public pointcut monitoredOperation(): execution(* *.*(..)) && !within(aspects..*);

    Object around(): monitoredOperation() {
        long start = System.nanoTime();
        try {
            return proceed();
        } finally {
            long complete = System.nanoTime();
            logger.log(Level.INFO, "Operation " + thisJoinPointStaticPart.getSignature().toShortString()
                    + " took " + (complete - start) + " nanoseconds");
        }
    }
}
