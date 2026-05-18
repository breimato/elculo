package com.breixo.culo.infrastructure.mapper;

import com.breixo.culo.domain.GamePhase;
import com.breixo.culo.infrastructure.adapter.input.ws.dto.GamePhaseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-18T12:43:36+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class GamePhaseMapperImpl implements GamePhaseMapper {

    @Override
    public GamePhaseDto toGamePhaseDto(GamePhase gamePhase) {
        if ( gamePhase == null ) {
            return null;
        }

        GamePhaseDto gamePhaseDto;

        switch ( gamePhase ) {
            case LOBBY: gamePhaseDto = GamePhaseDto.LOBBY;
            break;
            case DEALING: gamePhaseDto = GamePhaseDto.DEALING;
            break;
            case CULO_SWAP_VOTE: gamePhaseDto = GamePhaseDto.CULO_SWAP_VOTE;
            break;
            case PLAYING: gamePhaseDto = GamePhaseDto.PLAYING;
            break;
            case EXCHANGE: gamePhaseDto = GamePhaseDto.EXCHANGE;
            break;
            case FINISHED: gamePhaseDto = GamePhaseDto.FINISHED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + gamePhase );
        }

        return gamePhaseDto;
    }

    @Override
    public GamePhase toGamePhase(GamePhaseDto gamePhaseDto) {
        if ( gamePhaseDto == null ) {
            return null;
        }

        GamePhase gamePhase;

        switch ( gamePhaseDto ) {
            case LOBBY: gamePhase = GamePhase.LOBBY;
            break;
            case DEALING: gamePhase = GamePhase.DEALING;
            break;
            case CULO_SWAP_VOTE: gamePhase = GamePhase.CULO_SWAP_VOTE;
            break;
            case PLAYING: gamePhase = GamePhase.PLAYING;
            break;
            case EXCHANGE: gamePhase = GamePhase.EXCHANGE;
            break;
            case FINISHED: gamePhase = GamePhase.FINISHED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + gamePhaseDto );
        }

        return gamePhase;
    }
}
