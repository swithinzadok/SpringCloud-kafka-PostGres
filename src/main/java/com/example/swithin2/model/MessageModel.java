package com.example.swithin2.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Messages")
public class MessageModel {

	public MessageModel() {

	}

	@Id
	private String message;

	public MessageModel(String message) {

		super();

		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
