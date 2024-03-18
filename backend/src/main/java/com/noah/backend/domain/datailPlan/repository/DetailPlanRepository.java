package com.noah.backend.domain.datailPlan.repository;

import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.datailPlan.repository.custom.DetailPlanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailPlanRepository extends JpaRepository<Account, Long>, DetailPlanRepositoryCustom {
}
