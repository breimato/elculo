package com.breixo.culo.infrastructure.adapter.input.ws.mapper;

import com.breixo.culo.domain.model.Room;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.RoomStateDto;
import com.breixo.culo.infrastructure.mapper.GamePhaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GamePhaseMapper.class, PlayerDtoMapper.class})
public interface RoomStateDtoMapper {

  @Mapping(target = "roomCode", source = "code")
  RoomStateDto toRoomStateDto(Room room);
}
