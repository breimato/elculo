package com.breixo.culo.infrastructure.adapter.input.ws.mapper;

import com.breixo.culo.domain.model.Player;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.PlayerDto;
import com.breixo.culo.infrastructure.mapper.PlayerRoleMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-18T10:35:23+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PlayerDtoMapperImpl implements PlayerDtoMapper {

    @Autowired
    private PlayerRoleMapper playerRoleMapper;

    @Override
    public PlayerDto toPlayerDto(Player player) {
        if ( player == null ) {
            return null;
        }

        PlayerDto.Builder playerDto = PlayerDto.builder();

        playerDto.connected( player.isConnected() );
        playerDto.id( player.getId() );
        playerDto.nick( player.getNick() );
        playerDto.role( playerRoleMapper.toPlayerRoleDto( player.getRole() ) );

        return playerDto.build();
    }

    @Override
    public List<PlayerDto> toPlayerDtoList(List<Player> players) {
        if ( players == null ) {
            return null;
        }

        List<PlayerDto> list = new ArrayList<PlayerDto>( players.size() );
        for ( Player player : players ) {
            list.add( toPlayerDto( player ) );
        }

        return list;
    }
}
