package com.noah.backend.domain.ticket.repository;

import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.ticket.repository.custom.TicketRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Account, Long> , TicketRepositoryCustom {
}
