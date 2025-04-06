public class Result {
    private final String taskName;
    private final String status;

    public Result(String taskName, String status) {
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
