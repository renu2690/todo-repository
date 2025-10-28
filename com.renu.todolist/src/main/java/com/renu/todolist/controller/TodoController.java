package com.renu.todolist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todolist")
public class TodoController {

    @Autowired
    private TodoListService todoListService;

    // ===================== Get Todo by ID =====================
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Integer id) {
        try {
            TodoListEntity todo = todoListService.getTodoById(id);
            return ResponseEntity.ok(todo);
        } catch (NoSuchElementException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Todo list with ID " + id + " not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Unexpected error: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===================== Get All Todos =====================
    @GetMapping
    public ResponseEntity<?> getAllTodoList() {
        try {
            List<TodoListEntity> todos = todoListService.getAllTodoList();
            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Unable to fetch todo lists: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ===================== Create Todo =====================
    @PostMapping
    public ResponseEntity<?> saveTodo(@RequestBody TodoListEntity todoListEntity) {
        try {
            TodoListEntity savedTodo = todoListService.createTodo(todoListEntity);
            return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to save todo: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    // ===================== Update Todo =====================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Integer id, @RequestBody TodoListEntity todoDetails) {
        try {
            TodoListEntity updatedTodo = todoListService.updateTodo(id, todoDetails);
            return ResponseEntity.ok(updatedTodo);
        } catch (NoSuchElementException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Todo list with ID " + id + " not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to update todo: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    // ===================== Delete Todo =====================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Integer id) {
        try {
            todoListService.deleteTodoById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Todo list with ID " + id + " deleted successfully.");
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Todo list with ID " + id + " not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete todo: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
