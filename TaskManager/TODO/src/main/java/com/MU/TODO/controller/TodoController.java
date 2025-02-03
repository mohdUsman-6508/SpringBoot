package com.MU.TODO.controller;

import com.MU.TODO.models.Todo;
import com.MU.TODO.models.User;
import com.MU.TODO.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/{username}/{id}")
    public Todo createTodo(@PathVariable String username, @PathVariable Long id, @RequestBody Todo todo) {
        return todoService.createTodo(username, id, todo);
    }

    @GetMapping("/{username}")
    public List<Todo> getTodos(@PathVariable String username) {
        return todoService.getTodosByUser(username);
    }

    @PatchMapping("/{todoId}/{completed}")
    public Todo toggleTodo(@PathVariable Long todoId, @PathVariable boolean completed) {
        return todoService.updateTodo(todoId, completed);
    }

    @DeleteMapping("/{todoId}")
    public boolean deleteTodo(@PathVariable Long todoId) {
        return todoService.deleteTodo(todoId);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return todoService.getAllUsers();
    }

}
