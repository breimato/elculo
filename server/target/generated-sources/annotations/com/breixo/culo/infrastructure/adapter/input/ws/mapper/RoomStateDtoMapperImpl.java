package com.breixo.culo.infrastructure.adapter.input.ws.mapper;

import com.breixo.culo.domain.model.Room;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.RoomStateDto;
import com.breixo.culo.infrastructure.mapper.GamePhaseMapper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-18T00:12:03+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class RoomStateDtoMapperImpl implements RoomStateDtoMapper {

    @Autowired
    private GamePhaseMapper gamePhaseMapper;
    @Autowired
    private PlayerDtoMapper playerDtoMapper;

    @Override
    public RoomStateDto toRoomStateDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomStateDto.Builder roomStateDto = RoomStateDto.builder();

        roomStateDto.roomCode( room.getCode() );
        roomStateDto.hostPlayerId( room.getHostPlayerId() );
        roomStateDto.phase( gamePhaseMapper.toGamePhaseDto( room.getPhase() ) );
        roomStateDto.players( playerDtoMapper.toPlayerDtoList( room.getPlayers() ) );

        return roomStateDto.build();
    }
}
