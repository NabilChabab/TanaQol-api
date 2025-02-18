package com.example.tanaqolapi.services.dto;

import com.example.tanaqolapi.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profile;
    private Double rating;
    private Role role;
}
