package me.sukumdev.bookapi.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;
}
