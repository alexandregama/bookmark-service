package com.bookmark.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "username", length = 15)
	private String username;

	@JsonIgnore
	@Column(name = "password", length = 10)
	private String password;

	@OneToMany(mappedBy = "account")
	@Column(name = "bookmarks")
	private Set<Bookmark> bookmarks = new HashSet<>();

	@Deprecated // JPA eyes only
	Account() {
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Set<Bookmark> getBookmarks() {
		return bookmarks;
	}
}
