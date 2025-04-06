# Data Processing System
## Overview
The Data Processing System simulates multiple worker threads (or goroutines) processing tasks in parallel. It is implemented in both Java and Go, showcasing concurrency management, shared resource handling, and error handling in each language.

Each implementation:
- Uses a shared queue to distribute tasks among workers.
- Processes tasks concurrently using threads (Java) or goroutines (Go).
- Logs worker activity and handles errors gracefully.
- Outputs processed results.

## Project Structure
The project is organized as follows:

Data-Processing-System/
├── java/
│ └── main/
│ └── src/
│ ├── DataProcessingSystem.java
│ ├── Task.java
│ ├── Result.java
│ └── Worker.java
├── go/
│ └── main/
│ └── src/
│ ├── main.go
│ ├── task.go
│ ├── result.go
│ └── worker.go

---

## How to Run

### Java Implementation

#### Prerequisites
1. Install [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (version 8 or later).
2. Ensure `javac` and `java` are available in your system's PATH.

#### Steps to Run
##### 1. Navigate to the `Data-Processing-System/java/main/src` directory:
cd Data-Processing-System/java/main/src

##### 2. Compile all `.java` files:
javac *.java

##### 3. Run the program:
java DataProcessingSystem


#### Expected Output
You will see logs of workers processing tasks, followed by processed results:
INFO: pool-1-thread-1 processing Task-0
INFO: pool-1-thread-2 processing Task-1
INFO: pool-1-thread-3 processing Task-2

Processed Results:
Result{taskName='Task-0', status='Success'}
Result{taskName='Task-1', status='Success'}
Result{taskName='Task-2', status='Success'}


---

### Go Implementation

#### Prerequisites
1. Install [Go](https://golang.org/dl/) (version 1.16 or later).
2. Ensure `go` is available in your system's PATH.

#### Steps to Run
1. Navigate to the `Data-Processing-System/go/main/src` directory:
cd Data-Processing-System/go/main/src

2. Run the program directly:
go run *.go

3. Alternatively, build an executable and run it:
go build -o data_processing_system .
./data_processing_system # On Linux/MacOS
data_processing_system.exe # On Windows


#### Expected Output
You will see logs of workers processing tasks, followed by processed results:
Worker processing Task-0
Worker completed Task-0

Processed Results:
Result: {TaskName: Task-0, Status: Success}
Result: {TaskName: Task-1, Status: Success}
Result: {TaskName: Task-2, Status: Success}


---

## Comparison Between Java and Go

| Feature                  | Java Implementation                           | Go Implementation                          |
|--------------------------|-----------------------------------------------|-------------------------------------------|
| **Concurrency Model**    | Thread-based (`ExecutorService`)              | Goroutines with channels                  |
| **Shared Resource**      | `BlockingQueue` for tasks                     | Buffered channels for tasks               |
| **Error Handling**       | Try-catch blocks                              | Context cancellation and error handling via logs |
| **Logging**              | `java.util.logging.Logger`                    | Standard `log` package                    |
| **Scalability**          | Thread pool with fixed size                   | Lightweight goroutines                    |

---

## Key Features

### Concurrency Management:
- Java uses a thread pool (`ExecutorService`) for efficient thread management.
- Go leverages lightweight goroutines with channels for task distribution.

### Error Handling:
- Java uses try-catch blocks for exceptions like `InterruptedException`.
- Go uses context cancellation and proper handling of closed channels.

### Logging:
Both implementations log worker activity and errors for debugging purposes.

### Modularity:
The project is divided into logical components (`Task`, `Result`, `Worker`) in both languages.

