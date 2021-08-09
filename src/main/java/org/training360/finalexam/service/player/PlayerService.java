package org.training360.finalexam.service.player;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.finalexam.dto.player.CreatePlayerCommand;
import org.training360.finalexam.dto.player.PlayerDTO;
import org.training360.finalexam.repository.player.Player;
import org.training360.finalexam.repository.player.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService {
    private ModelMapper modelMapper;
    private PlayerRepository playerRepository;

    public List<PlayerDTO> listPLayers() {
        return playerRepository.findAll().stream()
                .map(e -> modelMapper.map(e, PlayerDTO.class))
                .collect(Collectors.toList());
    }

    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        Player player = new Player(command.getName());
        player.setBirthDate(command.getBirthDate());
        player.setPosition(command.getPosition());
        playerRepository.save(player);
        return modelMapper.map(player, PlayerDTO.class);
    }

    public void deleteCharacter(long id) {
        playerRepository.deleteById(id);
    }

}
