package com.noah.backend.domain.plan.repository;

import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.plan.entity.Plan;
import com.noah.backend.domain.plan.repository.custom.PlanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long>, PlanRepositoryCustom {
}
