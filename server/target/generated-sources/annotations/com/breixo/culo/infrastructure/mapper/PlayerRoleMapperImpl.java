package com.breixo.culo.infrastructure.mapper;

import com.breixo.culo.domain.PlayerRole;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.PlayerRoleDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-18T10:35:23+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PlayerRoleMapperImpl implements PlayerRoleMapper {

    @Override
    public PlayerRoleDto toPlayerRoleDto(PlayerRole playerRole) {
        if ( playerRole == null ) {
            return null;
        }

        PlayerRoleDto playerRoleDto;

        switch ( playerRole ) {
            case NONE: playerRoleDto = PlayerRoleDto.NONE;
            break;
            case GANADOR: playerRoleDto = PlayerRoleDto.GANADOR;
            break;
            case SUBCAMPEON: playerRoleDto = PlayerRoleDto.SUBCAMPEON;
            break;
            case PENULTIMO: playerRoleDto = PlayerRoleDto.PENULTIMO;
            break;
            case CULO: playerRoleDto = PlayerRoleDto.CULO;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + playerRole );
        }

        return playerRoleDto;
    }

    @Override
    public PlayerRole toPlayerRole(PlayerRoleDto playerRoleDto) {
        if ( playerRoleDto == null ) {
            return null;
        }

        PlayerRole playerRole;

        switch ( playerRoleDto ) {
            case NONE: playerRole = PlayerRole.NONE;
            break;
            case GANADOR: playerRole = PlayerRole.GANADOR;
            break;
            case SUBCAMPEON: playerRole = PlayerRole.SUBCAMPEON;
            break;
            case PENULTIMO: playerRole = PlayerRole.PENULTIMO;
            break;
            case CULO: playerRole = PlayerRole.CULO;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + playerRoleDto );
        }

        return playerRole;
    }
}
