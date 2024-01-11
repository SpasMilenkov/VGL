package com.jaba.vgl.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity(name = "Game")
@Table(name = "game_table")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Game {

    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Name must not be empty.")
    private String name;

    @Column(
            name = "company_id",
            nullable = false
    )
    @NotNull(message = "Company id must not be empty.")
    private Long companyId;

    @Column(
            name = "release_date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Release date must not be empty.")
    private String releaseDate;

    //Bi-directional
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "company_id",
            referencedColumnName = "id",
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(
                    name = "company_id_fk"
            )
    )
    private Company company;

    @ManyToMany(mappedBy = "games")
    Set<User> users;
}
