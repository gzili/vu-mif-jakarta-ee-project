package lt.vu.mif.api.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lt.vu.mif.api.services.UpdateChecker;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class CheckForUpdates {
    @Inject
    private UpdateChecker updateChecker;

    private CompletableFuture<Integer> checkForUpdatesTask = null;

    private LocalDateTime lastCheckDate = null;

    public boolean isChecking() {
        return checkForUpdatesTask != null && !checkForUpdatesTask.isDone();
    }

    public LocalDateTime getLastCheckDate() {
        return lastCheckDate;
    }

    public Integer getUpdateCount() throws ExecutionException, InterruptedException {
        if (checkForUpdatesTask == null) {
            return null;
        }

        if (checkForUpdatesTask.isDone()) {
            return checkForUpdatesTask.get();
        }

        return null;
    }

    public void initiateCheck() {
        if (!isChecking()) {
            checkForUpdatesTask = CompletableFuture.supplyAsync(() -> {
                int count = updateChecker.getUpdateCount();
                lastCheckDate = LocalDateTime.now();
                return count;
            });
        }
    }
}
