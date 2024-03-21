package com.noah.backend.domain.review.repository;

//import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.review.entity.Review;
import com.noah.backend.domain.review.repository.custom.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> , ReviewRepositoryCustom {
}
