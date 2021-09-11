package duke.task;

/**
 * Encapsulates the idea of a task (which is abstract to prevent instantiation).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object and whether it is completed.
     *
     * @param description the description of the task.
     * @param isDone      whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the string representation of whether the task is done.
     *
     * @return the string representation of whether its completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task to be completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts and formats a Task object into a string to be stored in the database.
     *
     * @return the formatted string representation.
     */
    public String databaseString() {
        return String.format("%s | %s", isDone ? 1 : 0, description);
    }

    /**
     * Converts a Task object into a string representation.
     *
     * @return the string representation of a Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
