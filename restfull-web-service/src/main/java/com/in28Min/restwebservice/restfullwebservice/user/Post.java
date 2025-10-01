package com.in28Min.restwebservice.restfullwebservice.user;

import java.time.LocalTime;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	private Integer id;

	private String details;

	private LocalTime postTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private UserJPA user;

	public Post() {
	}

	public UserJPA getUser() {
		return user;
	}

	public void setUser(UserJPA user) {
		this.user = user;
	}

	public Post(Integer id, String details, LocalTime postTime) {
		super();
		this.id = id;
		this.details = details;
		this.postTime = postTime;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", details=" + details + ", postTime=" + postTime + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalTime postTime) {
		this.postTime = postTime;
	}


}
