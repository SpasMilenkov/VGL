package com.jaba.vgl.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity(name = "GameDetails")
@Table(name = "game_details_table")
@NoArgsConstructor
@AllArgsConstructor
public @Data class GameDetails {

    @Id
    @SequenceGenerator(
            name = "game_details_sequence",
            sequenceName = "game_details_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_details_sequence"
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
            name = "company_id",
            nullable = false
    )
    @NotNull(message = "Company id must not be empty.")
    private Long companyId;

    @OneToMany(mappedBy = "gameDetails",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Review> reviews;

    //Uni-directional
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
}
