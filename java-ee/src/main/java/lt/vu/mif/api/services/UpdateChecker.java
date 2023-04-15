package lt.vu.mif.api.services;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

@ApplicationScoped
public class UpdateChecker {
    public int getUpdateCount() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}

        return new Random().nextInt(5);
    }
}
