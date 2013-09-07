/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import org.shelltea.seeker.entity.StarredEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public interface StarredEntryRepository extends JpaRepository<StarredEntry, Long> {
}
