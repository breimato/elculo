package com.breixo.culo.application.usecase.game;

import com.breixo.culo.domain.GamePhase;
import com.breixo.culo.domain.command.game.CuloSwapVoteCommand;
import com.breixo.culo.domain.exception.GameException;
import com.breixo.culo.domain.exception.RoomException;
import com.breixo.culo.domain.exception.constants.GameExceptionConstants;
import com.breixo.culo.domain.exception.constants.RoomExceptionConstants;
import com.breixo.culo.domain.model.Room;
import com.breixo.culo.domain.port.input.game.CuloSwapVoteUseCase;
import com.breixo.culo.domain.port.output.room.RoomPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CuloSwapVoteUseCaseImpl implements CuloSwapVoteUseCase {

  /** The room persistence port. */
  private final RoomPersistencePort roomPersistencePort;

  @Override
  public Room execute(final CuloSwapVoteCommand command) {
    final var room = this.roomPersistencePort.findByCode(command.roomCode())
        .orElseThrow(() -> new RoomException(RoomExceptionConstants.ROOM_NOT_FOUND));
    final var player = room.findPlayerByClientId(command.clientId())
        .orElseThrow(() -> new RoomException(RoomExceptionConstants.PLAYER_NOT_IN_ROOM));
    if (!room.getPhase().equals(GamePhase.CULO_SWAP_VOTE)) {
      throw new GameException(GameExceptionConstants.WRONG_PHASE);
    }

    final var allVoted = room.registerCuloSwapVote(player.getId(), command.accept());
    if (allVoted) {
      if (room.isCuloSwapApproved()) {
        room.applyCuloSwap();
      }
      room.clearCuloSwap();
      room.setPhase(GamePhase.DEALING);
    }

    return this.roomPersistencePort.save(room);
  }
}
