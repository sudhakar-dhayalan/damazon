package com.damazon.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "User name should not be null.")
    @Size(min = 3, message = "User name must be more than 3 characters.")
    private String userName;

    @NotNull(message = "User name should not be null.")
    @Size(min = 5, message = "User name must be more than 5 characters.")
    private String password;

}
