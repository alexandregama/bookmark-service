package com.bookmarks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmarks.account.AccountRepository;
import com.bookmarks.bookmark.Bookmark;
import com.bookmarks.bookmark.BookmarkRepository;

@RestController
@RequestMapping(value = "/{userId}/bookmarks")
public class BookmarkRestController {

	@Autowired
	private BookmarkRepository bookmarkRepository;

	@Autowired
	private AccountRepository accountRepository;

	@RequestMapping(value = "/{bookmarkId}")
	public Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
		this.validateUser(userId);

		return bookmarkRepository.findOne(bookmarkId);
	}

	private void validateUser(String userId) {
		accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

}
