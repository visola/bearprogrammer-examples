package com.bearprogrammer.blog.sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contact {

	@Id
	@GeneratedValue
	Integer id;

	String value;

	Type type;

	@ManyToOne
	Person owner;

	public Integer getId() {
		return id;
	}

	public Person getOwner() {
		return owner;
	}

	public Type getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
