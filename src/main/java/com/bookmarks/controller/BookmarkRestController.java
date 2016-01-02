package com.bookmarks.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookmarks.account.AccountRepository;
import com.bookmarks.bookmark.Bookmark;
import com.bookmarks.bookmark.BookmarkRepository;

@RestController
@RequestMapping(value = "/{userId}/bookmarks")
public class BookmarkRestController {

	private BookmarkRepository bookmarkRepository;

	private AccountRepository accountRepository;

	@Autowired
	public BookmarkRestController(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
		this.bookmarkRepository = bookmarkRepository;
		this.accountRepository = accountRepository;
	}

	@RequestMapping(value = "/{bookmarkId}", method = RequestMethod.GET)
	public Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
		this.validateUser(userId);

		return bookmarkRepository.findOne(bookmarkId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Bookmark> readBookmarks(@PathVariable String userId) {
		this.validateUser(userId);

		return bookmarkRepository.findByAccountUsername(userId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addBookmark(@PathVariable String userId, @RequestBody Bookmark input) {
		this.validateUser(userId);

		return this.accountRepository.findByUsername(userId).map(account -> {
			Bookmark bookmarkSaved = bookmarkRepository.save(new Bookmark(account, input.getUri(), input.getDescription()));

			HttpHeaders httpHeaders = new HttpHeaders();
			URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bookmarkSaved.getId()).toUri();
			httpHeaders.setLocation(uriLocation);

			return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
		}).get();
	}

	private void validateUser(String userId) {
		accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

}
