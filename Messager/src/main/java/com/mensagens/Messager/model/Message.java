package com.mensagens.Messager.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

	private int id;
	private String message;
	private LocalDateTime created;
	private int author;
	//private Map<Integer, Comment> comments = new HashMap<>();
	private List<Link> links = new ArrayList<>();
	
	
	public Message(){
		
	}
	
	public Message(int id, String message, LocalDateTime created, int author) {
		this.id = id;
		this.message = message;
		this.created = created;
		this.author = author;
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
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
/*
	@XmlTransient
	public Map<Integer, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Integer, Comment> comments) {
		this.comments = comments;
	}*/

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String url, String rel){
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
	
	
	
	
	
	
}
