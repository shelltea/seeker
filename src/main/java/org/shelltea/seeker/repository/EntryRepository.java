/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import org.shelltea.seeker.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
