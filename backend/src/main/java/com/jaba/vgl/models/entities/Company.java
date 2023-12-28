package com.jaba.vgl.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Company")
@Table(name = "company_table")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Company {
    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )
    @Column(name = "id")
    public Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Company name must not be empty.")
    public String name;

    @Column(
            name = "studio",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Company studio must not be empty.")
    public String studio;

    @OneToMany(mappedBy = "company",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public List<Game> games;
}
