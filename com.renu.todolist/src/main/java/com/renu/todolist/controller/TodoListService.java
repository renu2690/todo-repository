package com.renu.todolist.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {

    @Autowired
    private TodoListInterface todoListRepository;

    // ===================== Get Todo by ID =====================
    public TodoListEntity getTodoById(Integer id) {
        return todoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Todo list with ID " + id + " not found"));
    }

    // ===================== Get All Todos =====================
    public List<TodoListEntity> getAllTodoList() {
        return todoListRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    // ===================== Create Todo =====================
    public TodoListEntity createTodo(TodoListEntity todoListEntity) {
        // Ensure child descriptions are linked to parent
        if (todoListEntity.getDescriptions() != null) {
            todoListEntity.getDescriptions().forEach(desc -> desc.setTodoList(todoListEntity));
        }

        // Save parent (children are saved automatically because of CascadeType.ALL)
        return todoListRepository.save(todoListEntity);
    }


    // ===================== Update Todo =====================
    public TodoListEntity updateTodo(Integer id, TodoListEntity todoDetails) {
        TodoListEntity existingTodo = todoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Todo list with ID " + id + " not found"));

        existingTodo.setDescription(todoDetails.getDescription());
        existingTodo.setStatus(todoDetails.getStatus());

        // Update descriptions and maintain bidirectional relationship
        if (todoDetails.getDescriptions() != null) {
            existingTodo.getDescriptions().clear(); // remove old descriptions
            todoDetails.getDescriptions().forEach(desc -> existingTodo.addDescription(desc));
        }

        return todoListRepository.save(existingTodo);
    }

    // ===================== Delete Todo =====================
    public void deleteTodoById(Integer id) {
        if (!todoListRepository.existsById(id)) {
            throw new NoSuchElementException("Todo list with ID " + id + " not found");
        }
        todoListRepository.deleteById(id);
    }
}
