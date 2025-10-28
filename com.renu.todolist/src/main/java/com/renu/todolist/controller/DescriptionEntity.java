package com.renu.todolist.controller;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todolistdesc")
public class DescriptionEntity {

   
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "descid")
    @SequenceGenerator(name = "descid", sequenceName = "descid", allocationSize = 1)
    private Integer descid;


    @JsonProperty("detaileddesc")
    private String detaileddesc;

    // Child side (many descriptions → one todolist)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todolist_fk_id", nullable = false) // ✅ foreign key column
    @JsonBackReference   // ✅ prevents infinite JSON recursion
    private TodoListEntity todoList;

	public Integer getDescid() {
		return descid;
	}

	public void setDescid(Integer descid) {
		this.descid = descid;
	}

	public String getDetaileddesc() {
		return detaileddesc;
	}

	public void setDetaileddesc(String detaileddesc) {
		this.detaileddesc = detaileddesc;
	}

	public TodoListEntity getTodoList() {
		return todoList;
	}

	public void setTodoList(TodoListEntity todoList) {
		this.todoList = todoList;
	}
    
    
}

