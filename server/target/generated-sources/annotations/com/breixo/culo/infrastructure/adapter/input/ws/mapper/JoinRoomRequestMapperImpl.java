package com.breixo.culo.infrastructure.adapter.input.ws.mapper;

import com.breixo.culo.domain.command.room.JoinRoomCommand;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.JoinRoomRequestDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-18T00:12:03+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class JoinRoomRequestMapperImpl implements JoinRoomRequestMapper {

    @Override
    public JoinRoomCommand toJoinRoomCommand(JoinRoomRequestDto joinRoomRequestDto) {
        if ( joinRoomRequestDto == null ) {
            return null;
        }

        JoinRoomCommand.JoinRoomCommandBuilder joinRoomCommand = JoinRoomCommand.builder();

        joinRoomCommand.clientId( joinRoomRequestDto.getClientId() );
        joinRoomCommand.roomCode( joinRoomRequestDto.getRoomCode() );
        joinRoomCommand.nick( joinRoomRequestDto.getNick() );

        return joinRoomCommand.build();
    }
}
