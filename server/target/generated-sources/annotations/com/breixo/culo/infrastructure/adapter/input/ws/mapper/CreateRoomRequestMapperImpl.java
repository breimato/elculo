package com.breixo.culo.infrastructure.adapter.input.ws.mapper;

import com.breixo.culo.domain.command.room.CreateRoomCommand;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.CreateRoomRequestDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-18T00:38:43+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CreateRoomRequestMapperImpl implements CreateRoomRequestMapper {

    @Override
    public CreateRoomCommand toCreateRoomCommand(CreateRoomRequestDto createRoomRequestDto) {
        if ( createRoomRequestDto == null ) {
            return null;
        }

        CreateRoomCommand.CreateRoomCommandBuilder createRoomCommand = CreateRoomCommand.builder();

        createRoomCommand.clientId( createRoomRequestDto.getClientId() );
        createRoomCommand.nick( createRoomRequestDto.getNick() );

        return createRoomCommand.build();
    }
}
