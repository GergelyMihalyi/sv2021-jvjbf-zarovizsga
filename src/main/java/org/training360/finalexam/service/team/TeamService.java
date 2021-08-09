package org.training360.finalexam.service.team;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.training360.finalexam.dto.player.CreatePlayerCommand;
import org.training360.finalexam.dto.team.UpdateWithExistingPlayerCommand;
import org.training360.finalexam.dto.team.CreateTeamCommand;
import org.training360.finalexam.dto.team.TeamDTO;
import org.training360.finalexam.exception.PlayerNotFoundException;
import org.training360.finalexam.exception.TeamNotFoundException;
import org.training360.finalexam.repository.player.Player;
import org.training360.finalexam.repository.player.PlayerRepository;
import org.training360.finalexam.repository.team.Team;
import org.training360.finalexam.repository.team.TeamRepository;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {
    private ModelMapper modelMapper;
    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;

    public TeamDTO createTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        teamRepository.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    public List<TeamDTO> listTeams() {
        return teamRepository.findAll().stream()
                .map(e -> modelMapper.map(e, TeamDTO.class))
                .collect(Collectors.toList());
    }

    public TeamDTO addPlayerToTeam(long id, UpdateWithExistingPlayerCommand command) {
        Team team =
                teamRepository.findById(id)
                        .orElseThrow(() -> new TeamNotFoundException("team not found"));
        Player player = playerRepository.findById(command.getPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("player not found"));
        if (isValidPlayer(team, player)) {
            player.setTeam(team);
            playerRepository.save(player);
        }

        return modelMapper.map(team, TeamDTO.class);
    }

    private boolean isValidPlayer(Team team, Player player) {
        Team playerTeam = player.getTeam();
        long samePositionPlayer = team.getPlayers().stream().filter(e -> e.getPosition().equals(player.getPosition())).count();
        return playerTeam == null && samePositionPlayer < 2L;
    }


    public TeamDTO addNewPlayerToTeam(long id, CreatePlayerCommand command) {
        Team team =
                teamRepository.findById(id)
                        .orElseThrow(() -> new TeamNotFoundException("team not found"));
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition(), team);
        playerRepository.save(player);

        return modelMapper.map(team, TeamDTO.class);
    }

}
