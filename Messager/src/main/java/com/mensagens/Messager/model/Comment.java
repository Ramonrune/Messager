package com.mensagens.Messager.model;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

	private int id;
	private String message;
	private LocalDateTime created;
	private int authorId;
	private int messageId;
	
	public Comment(){
		
	}
	
	
	
	public Comment(int id, String message, LocalDateTime created, int authorId, int messageId) {
		super();
		this.id = id;
		this.message = message;
		this.created = created;
		this.authorId = authorId;
		this.messageId = messageId;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	
	
	
}
