package com.bearprogrammer.blog.sample;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ToDoHistory {

	@Id
	@GeneratedValue
	Integer id;

	String action;

	Calendar updated;

	String updatedBy;

	@ManyToOne
	ToDo toDo;

	public ToDoHistory() {
		// Empty constructor
	}

	public ToDoHistory(ToDo toDo) {
		this.toDo = toDo;
		
		this.action = toDo.getAction();
		this.updated = toDo.getUpdated();
		this.updatedBy = toDo.getUpdatedBy();
	}

	public String getAction() {
		return action;
	}

	public Integer getId() {
		return id;
	}

	public ToDo getToDo() {
		return toDo;
	}

	public Calendar getUpdated() {
		return updated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setToDo(ToDo toDo) {
		this.toDo = toDo;
	}

	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
