package com.renu.todolist.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoListInterface extends JpaRepository<TodoListEntity, Integer>{

}
