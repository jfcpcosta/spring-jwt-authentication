package io.reativ.samples.springauthenticationtoken.controllers;

import io.reativ.samples.springauthenticationtoken.models.Task;
import io.reativ.samples.springauthenticationtoken.repositories.TasksRepository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private TasksRepository taskRepository;

    public TasksController(TasksRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public void create(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @GetMapping
    public List<Task> get() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getOne(@PathVariable long id) {
        Task existingTask = taskRepository.findById(id).get();
        Assert.notNull(existingTask, "Task not found");

        return existingTask;
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id).get();
        Assert.notNull(existingTask, "Task not found");

        existingTask.setDescription(task.getDescription());
        taskRepository.save(existingTask);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        Task taskToDel = taskRepository.findById(id).get();
        taskRepository.delete(taskToDel);
    }
}
