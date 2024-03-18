package com.noah.backend.domain.account.repository;

import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.repository.custom.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {
}
