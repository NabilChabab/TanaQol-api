package com.example.tanaqolapi.model;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("DRIVER")
public class Driver extends AppUser{

    private String license;
    @Column(nullable = false, columnDefinition = "DOUBLE PRECISION DEFAULT 0.0")
    private Double rating = 0.0;
}
