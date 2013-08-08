/*
 * Copyright (C) SHELLTEA.
 */
package org.shelltea.seeker.access;

import org.shelltea.seeker.entity.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Xiong Shuhong(shelltea@gmail.com)
 */
public interface AccountDao extends PagingAndSortingRepository<Account, Long> {

}
