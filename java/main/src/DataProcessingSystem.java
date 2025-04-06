import java.util.concurrent.*;
import java.util.logging.*;

public class DataProcessingSystem {

    private static final BlockingQueue<Task> taskQueue = new ArrayBlockingQueue<>(100);
    private static final ConcurrentLinkedQueue<Result> results = new ConcurrentLinkedQueue<>();
    private static final Logger logger = Logger.getLogger(DataProcessingSystem.class.getName());

    public static void main(String[] args) {
        int numWorkers = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numWorkers);

        // Populate the task queue
        for (int i = 0; i < 20; i++) {
            taskQueue.add(new Task("Task-" + i));
        }

        // Start worker threads
        for (int i = 0; i < numWorkers; i++) {
            executor.submit(new Worker(taskQueue, results));
        }

        // Shutdown executor after tasks are processed
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                logger.warning("Executor did not terminate in the expected time.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            logger.severe("Executor interrupted during shutdown: " + e.getMessage());
            executor.shutdownNow();
        }

        // Print results
        System.out.println("Processed Results:");
        for (Result result : results) {
            System.out.println(result);
        }
    }
}
