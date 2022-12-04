package peaksoft.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String surName;
    private String email;
    private String password;
}

