package com.damazon.backend.model;

import com.damazon.backend.enums.Category;
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
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Name of the product is required.")
    @Size(min = 3, message = "Name must be of at least 3 characters")
    private String name;

    @NotNull(message = "Category field is required")
    @Enumerated(EnumType.STRING)
    private Category category;
    private double price;
}
