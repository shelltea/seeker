/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import org.shelltea.seeker.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
