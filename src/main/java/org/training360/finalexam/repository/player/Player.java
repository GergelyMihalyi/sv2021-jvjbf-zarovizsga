package org.training360.finalexam.repository.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.repository.team.Team;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private PositionType position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, LocalDate birthDate, PositionType position, Team team) {
        this.name = name;
        this.birthDate = birthDate;
        this.position = position;
        this.team = team;
    }
}
