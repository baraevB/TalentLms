package peaksoft.dto;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String surName;
    private String email;
    private LocalDate creatDate;
}
