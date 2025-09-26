package com.in28Min.restwebservice.restfullwebservice.user;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Post {

	@Id
	private Integer id;

	private String details;

	private LocalTime postTime;

	public Post() {
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
