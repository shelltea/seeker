/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.repository;

import org.shelltea.seeker.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByEmail(String email);

	Account findByUsername(String username);
}
