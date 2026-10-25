package task;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<String, Task> tasks;

    public TaskService() {
        this.tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Cannot add null task.");
        }
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Task ID already exists: " + task.getTaskId());
        }
        tasks.put(task.getTaskId(), task);
    }

    public void deleteTask(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null.");
        }
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID not found: " + taskId);
        }
        tasks.remove(taskId);
    }

    public void updateName(String taskId, String name) {
        Task task = getTaskOrThrow(taskId);
        task.setName(name);
    }

    public void updateDescription(String taskId, String description) {
        Task task = getTaskOrThrow(taskId);
        task.setDescription(description);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    private Task getTaskOrThrow(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null.");
        }
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task ID not found: " + taskId);
        }
        return task;
    }
}
