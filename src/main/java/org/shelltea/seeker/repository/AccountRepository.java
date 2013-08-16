/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import org.shelltea.seeker.entity.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
	Account findByEmail(String email);

	Account findByUsername(String username);
}
