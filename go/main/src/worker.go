package main

import (
	"context"
	"log"
	"sync"
	"time"
)

// Worker processes tasks from the tasks channel and writes results to the results channel
func Worker(ctx context.Context, tasks <-chan Task, results chan<- Result, wg *sync.WaitGroup) {
	defer wg.Done() // Mark this worker as done when it finishes

	for {
		select {
		case <-ctx.Done(): // Handle cancellation signal
			log.Println("Worker shutting down due to context cancellation")
			return
		case task, ok := <-tasks: // Retrieve a task from the tasks channel
			if !ok { // Exit if the channel is closed and no more tasks are available
				return
			}

			log.Printf("Worker processing %s\n", task.Name)
			result := processTask(task)
			results <- result // Send the result to the results channel
			log.Printf("Worker completed %s\n", task.Name)
		}
	}
}

// Simulate task processing with a delay and return a result
func processTask(task Task) Result {
	time.Sleep(1 * time.Second) // Simulate processing delay
	return Result{TaskName: task.Name, Status: "Success"}
}
