package com.noah.backend.domain.groupaccount.repository;

import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.groupaccount.repository.custom.GroupAccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupAccountRepository extends JpaRepository<GroupAccount, Long>, GroupAccountRepositoryCustom {
}
