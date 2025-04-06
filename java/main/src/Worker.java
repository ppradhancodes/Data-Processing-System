import java.util.concurrent.*;
import java.util.logging.*;

public class Worker implements Runnable {

    private final BlockingQueue<Task> taskQueue;
    private final ConcurrentLinkedQueue<Result> results;
    private static final Logger logger = Logger.getLogger(Worker.class.getName());

    public Worker(BlockingQueue<Task> taskQueue, ConcurrentLinkedQueue<Result> results) {
        this.taskQueue = taskQueue;
        this.results = results;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Retrieve and process a task from the queue
                Task task = taskQueue.poll(2, TimeUnit.SECONDS);
                if (task == null) {
                    break; // Exit if no more tasks are available
                }

                logger.info(Thread.currentThread().getName() + " processing " + task.getName());
                processTask(task);

                // Add the result to the shared resource
                results.add(new Result(task.getName(), "Success"));
                logger.info(Thread.currentThread().getName() + " completed " + task.getName());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.severe(Thread.currentThread().getName() + " interrupted: " + e.getMessage());
            } catch (Exception e) {
                logger.severe(Thread.currentThread().getName() + " encountered an error: " + e.getMessage());
            }
        }
    }

    // Simulate task processing with a delay
    private void processTask(Task task) throws InterruptedException {
        Thread.sleep(1000); // Simulate processing delay
    }
}
