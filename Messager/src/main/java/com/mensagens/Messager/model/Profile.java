package com.mensagens.Messager.model;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {

	
	private int id;
	private String profileName;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDateTime created;
	
	
	public Profile(){
		
	}

	
	

	public Profile(int id, String profileName, String firstName, String lastName, String email, LocalDateTime created) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.created = created;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProfileName() {
		return profileName;
	}


	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDateTime getCreated() {
		return created;
	}


	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	
	
	
	
	
}
