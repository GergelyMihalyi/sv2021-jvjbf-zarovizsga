package org.training360.finalexam.controller.player;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.dto.player.CreatePlayerCommand;
import org.training360.finalexam.dto.player.PlayerDTO;
import org.training360.finalexam.service.player.PlayerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@AllArgsConstructor
public class PlayerController {
    PlayerService playerService;

    @GetMapping
    public List<PlayerDTO> listPLayers() {
        return playerService.listPLayers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDTO createCharacter(@Valid @RequestBody CreatePlayerCommand command) {
        return playerService.createPlayer(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter(@PathVariable("id") long id) {
        playerService.deleteCharacter(id);
    }

}
