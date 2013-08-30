/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import org.shelltea.seeker.entity.StarredItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public interface StarredItemRepository extends JpaRepository<StarredItem, Long> {
}
