package com.lineup.lineup.service;


import com.lineup.lineup.exception.ResourceNotFoundException;
import com.lineup.lineup.model.Category;
import com.lineup.lineup.model.Task;
import com.lineup.lineup.model.User;
import com.lineup.lineup.repository.CategoryRepository;
import com.lineup.lineup.repository.TaskRepository;
import com.lineup.lineup.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public TaskService(UserRepository userRepository, TaskRepository taskRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    public Set<Task> getTasksByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getTasks();
        } else {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
    }

    public Task createTask(Task task, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Category> existingCategory = categoryRepository.findByCategoryName(task.getCategoryName());
            if (existingCategory.isPresent()) {
                task.setCategory(existingCategory.get());
            } else {
                task.setCategory(categoryService.createCategory(new Category(task.getCategoryName())));
            }
            task.setUserId(userId);
            taskRepository.save(task);
            user.get().getTasks().add(task);
            userRepository.save(user.get());
            return task;
        } else {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
    }

    public Task updateTask(Task task, Long id) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            if (task.getTitle() != null) {
                existingTask.get().setTitle(task.getTitle());
            }
            if (task.getCompleted() != null) {
                existingTask.get().setCompleted(task.getCompleted());
            }
            return taskRepository.save(existingTask.get());
        }

        throw new ResourceNotFoundException("Task not found with id " + task.getId());
    }

    public void deleteTask(Long id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
    }
}
