package lt.vu.mif.api.services;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;
import java.util.logging.Logger;

@ApplicationScoped
public class UpdateChecker {
    private final Logger logger = Logger.getLogger(UpdateChecker.class.getCanonicalName());

    public int getUpdateCount() {
        logger.info("initiating update check");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}

        int updateCount = new Random().nextInt(5);
        logger.info("found " + updateCount + " updates");

        return updateCount;
    }
}
