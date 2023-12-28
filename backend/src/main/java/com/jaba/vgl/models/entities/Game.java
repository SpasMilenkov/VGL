package com.jaba.vgl.models.entities;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.GameDto;
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
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Description must not be empty.")
    private String description;

    @Column(
            name = "rating",
            nullable = false
    )
    @NotNull(message = "Description must not be empty.")
    private Float rating;

    @Column(
            name = "genre",
            nullable = false
    )
    @NotNull(message = "Genre must not be empty.")
    private GameGenre genre;

    @Column(
            name = "company_id",
            nullable = false
    )
    @NotNull(message = "Company id must not be empty.")
    private Long companyId;

    @Column(
            name = "is_favourite",
            nullable = false,
            columnDefinition = "boolean default false"
    )
    @NotNull(message = "IsFavourite must not be empty.")
    private Boolean isFavourite;

    @Column(
            name = "release_date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Release date must not be empty.")
    private String releaseDate; //TODO: change to date? (to discuss date format)

    //Bi-directional
    @ManyToOne
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
