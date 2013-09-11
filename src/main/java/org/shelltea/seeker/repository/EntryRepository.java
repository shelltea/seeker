/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import java.util.Collection;
import java.util.List;

import org.shelltea.seeker.entity.Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {
	List<Entry> findByFeedIdIn(Collection<Long> feedIds);

	Page<Entry> findByFeedIdIn(Collection<Long> feedIds, Pageable pageable);

	Entry findByUrl(String url);
}
