package com.bearprogrammer.blog.sample;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ToDo {

	@Id
	@GeneratedValue
	Integer id;

	@Size(min=3, max=1024)
	String action;

	@ManyToOne
	User createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	Calendar created;

	@ManyToOne
	User updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	Calendar updated;

	@NotNull
	@ManyToOne
	User assignedTo;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDo other = (ToDo) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (assignedTo == null) {
			if (other.assignedTo != null)
				return false;
		} else if (!assignedTo.equals(other.assignedTo))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}

	public String getAction() {
		return action;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public Calendar getCreated() {
		return created;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public Integer getId() {
		return id;
	}

	public Calendar getUpdated() {
		return updated;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result
				+ ((assignedTo == null) ? 0 : assignedTo.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		return result;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

}
