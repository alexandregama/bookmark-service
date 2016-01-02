package com.bookmarks.bookmark;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	Optional<Bookmark> findByAccountUsername(String username);

}
