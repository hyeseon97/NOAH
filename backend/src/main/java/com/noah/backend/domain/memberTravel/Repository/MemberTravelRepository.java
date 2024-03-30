package com.noah.backend.domain.memberTravel.Repository;

import com.noah.backend.domain.memberTravel.Repository.custom.MemberTravelRepositoryCustom;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTravelRepository extends JpaRepository<MemberTravel, Long> , MemberTravelRepositoryCustom {

}
