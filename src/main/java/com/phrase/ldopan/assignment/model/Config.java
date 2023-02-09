package com.phrase.ldopan.assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity (name = "config")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Config {
    @Id
    private Long id = 1L; //TODO: DEMO - just 1 row in the database

    @NonNull
    @NotEmpty(message = "Username cannot be empty.")
    private String username;

    @NonNull
    @NotEmpty(message = "Password cannot be empty.")
    private String password;
}