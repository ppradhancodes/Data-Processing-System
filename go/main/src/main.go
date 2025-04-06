package main

import (
	"context"
	"fmt"
	"sync"
)

func main() {
	// Shared resources: tasks and results channels
	tasks := make(chan Task, 20)
	results := make(chan Result, 20)
	numWorkers := 4

	// Populate the task queue
	for i := 0; i < 20; i++ {
		tasks <- Task{Name: fmt.Sprintf("Task-%d", i)}
	}
	close(tasks) // Close the task channel to signal no more tasks will be added

	// Create a context to manage worker cancellation
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()

	// WaitGroup to wait for all workers to finish processing
	var wg sync.WaitGroup

	// Start worker goroutines
	for i := 0; i < numWorkers; i++ {
		wg.Add(1)
		go Worker(ctx, tasks, results, &wg)
	}

	// Wait for all workers to finish
	wg.Wait()
	close(results) // Close results channel after workers are done

	// Print processed results
	fmt.Println("Processed Results:")
	for result := range results {
		fmt.Printf("Result: {TaskName: %s, Status: %s}\n", result.TaskName, result.Status)
	}
}
