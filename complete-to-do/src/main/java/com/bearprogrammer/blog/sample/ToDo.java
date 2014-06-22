package com.bearprogrammer.blog.sample;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ToDo {

	@Id
	@GeneratedValue
	Integer id;

	String action;

	String createdBy;

	Calendar created;

	String updatedBy;

	Calendar updated;

	String assignedTo;

	public String getAction() {
		return action;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public Calendar getCreated() {
		return created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Integer getId() {
		return id;
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

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
