package me.sukumdev.bookapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthDto {

    private String email;
    private String password;
}
