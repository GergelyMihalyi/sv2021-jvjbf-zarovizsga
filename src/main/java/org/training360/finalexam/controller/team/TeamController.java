package org.training360.finalexam.controller.team;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.dto.player.CreatePlayerCommand;
import org.training360.finalexam.dto.team.UpdateWithExistingPlayerCommand;
import org.training360.finalexam.dto.team.CreateTeamCommand;
import org.training360.finalexam.dto.team.TeamDTO;
import org.training360.finalexam.exception.PlayerNotFoundException;
import org.training360.finalexam.exception.TeamNotFoundException;
import org.training360.finalexam.service.team.TeamService;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@AllArgsConstructor
public class TeamController {
    TeamService teamService;

    @GetMapping
    public List<TeamDTO> listTeams() {
        return teamService.listTeams();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createTeam(@Valid @RequestBody CreateTeamCommand command) {
        return teamService.createTeam(command);
    }

    @PostMapping("/{id}/players")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createPlayerToTeam(@PathVariable("id") long id, @Valid @RequestBody CreatePlayerCommand command) {
        return teamService.addNewPlayerToTeam(id, command);
    }


    @PutMapping("/{id}/players")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO addPlayerToTeam(@PathVariable("id") long id, @RequestBody UpdateWithExistingPlayerCommand command) {
        return teamService.addPlayerToTeam(id, command);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(TeamNotFoundException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("teams/not-found"))
                .withTitle("not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(PlayerNotFoundException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("players/not-found"))
                .withTitle("not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
