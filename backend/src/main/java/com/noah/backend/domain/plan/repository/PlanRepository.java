package com.noah.backend.domain.plan.repository;

import com.noah.backend.domain.groupaccount.entity.GroupAccount;
import com.noah.backend.domain.plan.repository.custom.PlanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<GroupAccount, Long>, PlanRepositoryCustom {
}
