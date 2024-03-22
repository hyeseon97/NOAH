package com.noah.backend.domain.apis.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class AirlineCodeDto {
    // 항공사 코드들
    private List<String> codes;
}
