package com.renu.todolist.controller;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todolist")
public class TodoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    // Parent side of OneToMany
    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DescriptionEntity> descriptions = new ArrayList<>();

    // ✅ convenience method to keep both sides in sync
    public void addDescription(DescriptionEntity descriptionEntity) {
        descriptions.add(descriptionEntity);
        descriptionEntity.setTodoList(this);
    }

    public void removeDescription(DescriptionEntity descriptionEntity) {
        descriptions.remove(descriptionEntity);
        descriptionEntity.setTodoList(null);
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DescriptionEntity> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<DescriptionEntity> descriptions) {
		this.descriptions = descriptions;
	}
    
    
}
