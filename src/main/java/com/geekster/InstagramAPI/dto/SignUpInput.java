package com.geekster.InstagramAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Integer age;

    private String email;

    @Size(min = 6,max = 18,message = "Length of the must must lie inbetween 6 to 18 characters")
    private String userPassword;

    private String phoneNumber;
}
