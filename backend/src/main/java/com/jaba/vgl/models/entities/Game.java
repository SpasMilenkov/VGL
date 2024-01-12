package com.jaba.vgl.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

@Entity(name = "Game")
@Table(name = "game_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Game {

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
            name = "steam_id",
            nullable = false
    )
    @NotNull(message = "Name must not be empty.")
    private Long steamId;
    @Column(
            name = "release_date",
            nullable = false
    )
    @NotNull(message = "Name must not be empty.")
    private Date releaseDate;

    @Column(
            name = "stduio_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull(message = "Name must not be empty.")
    private String studioName;

    @ManyToMany
    @JoinTable(
            name = "user_game_table",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> users;
}
