package com.noah.backend.domain.trade.entity;

import com.noah.backend.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE trade SET is_deleted = TRUE WHERE trade_id = ?")
public class Trade extends BaseEntity {
}
