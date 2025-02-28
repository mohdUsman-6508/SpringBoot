package com.lineup.lineup.controller;


import com.lineup.lineup.model.Task;
import com.lineup.lineup.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/task/")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<Set<Task>> getTasks(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUserId(userId));
    }

    @PostMapping("{userId}")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable Long userId) {
        return ResponseEntity.status(201).body(taskService.createTask(task, userId));
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task,@PathVariable Long id) {
        return ResponseEntity.status(204).body(taskService.updateTask(task,id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
