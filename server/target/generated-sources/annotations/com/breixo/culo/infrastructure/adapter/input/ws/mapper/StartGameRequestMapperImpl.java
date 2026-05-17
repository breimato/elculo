package com.breixo.culo.infrastructure.adapter.input.ws.mapper;

import com.breixo.culo.domain.command.room.StartGameCommand;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.StartGameRequestDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-18T00:38:43+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class StartGameRequestMapperImpl implements StartGameRequestMapper {

    @Override
    public StartGameCommand toStartGameCommand(StartGameRequestDto startGameRequestDto) {
        if ( startGameRequestDto == null ) {
            return null;
        }

        StartGameCommand.StartGameCommandBuilder startGameCommand = StartGameCommand.builder();

        startGameCommand.clientId( startGameRequestDto.getClientId() );
        startGameCommand.roomCode( startGameRequestDto.getRoomCode() );

        return startGameCommand.build();
    }
}
