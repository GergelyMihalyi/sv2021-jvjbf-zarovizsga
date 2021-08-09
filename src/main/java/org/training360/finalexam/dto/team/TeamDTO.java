package org.training360.finalexam.dto.team;

import lombok.Data;
import org.training360.finalexam.dto.player.PlayerDTO;

import java.util.List;

@Data
public class TeamDTO {
    private long id;
    private String name;
    private List<PlayerDTO> players;
}
