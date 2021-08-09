package org.training360.finalexam.dto.player;

import lombok.Data;
import org.training360.finalexam.repository.player.PositionType;

import java.time.LocalDate;

@Data
public class PlayerDTO {

    private long id;

    private String name;

    private LocalDate birthDate;

    private PositionType position;

}
