/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import java.util.List;

import org.shelltea.seeker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByAccountId(Long accountId);

	Category findByAccountIdAndTitle(Long accountId, String title);
}
