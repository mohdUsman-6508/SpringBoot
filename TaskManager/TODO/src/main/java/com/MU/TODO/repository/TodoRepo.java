package com.MU.TODO.repository;

import com.MU.TODO.models.Todo;
import com.MU.TODO.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo {

    Todo createTodo(String username, Long id, Todo todo);

    List<Todo> getTodosByUser(String username);

    Todo updateTodo(Long todoId, boolean completed);

    boolean deleteTodo(Long todoId);

    List<User> getAllUsers();
}
