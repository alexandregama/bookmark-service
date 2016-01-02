package com.bookmarks.controller;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2706294551122647978L;

	public UserNotFoundException(String userId) {
		super(userId);
	}

}
