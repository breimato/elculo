package com.breixo.culo.infrastructure.adapter.input.ws.mapper;

import com.breixo.culo.domain.model.room.RoomJoinResult;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.JoinedRoomDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-18T10:10:23+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class JoinedRoomDtoMapperImpl implements JoinedRoomDtoMapper {

    @Override
    public JoinedRoomDto toJoinedRoomDto(RoomJoinResult roomJoinResult) {
        if ( roomJoinResult == null ) {
            return null;
        }

        JoinedRoomDto.Builder joinedRoomDto = JoinedRoomDto.builder();

        joinedRoomDto.roomCode( roomJoinResult.roomCode() );
        joinedRoomDto.playerId( roomJoinResult.playerId() );

        return joinedRoomDto.build();
    }
}
