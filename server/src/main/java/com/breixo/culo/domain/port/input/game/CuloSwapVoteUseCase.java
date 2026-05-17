package com.breixo.culo.domain.port.input.game;

import com.breixo.culo.domain.command.game.CuloSwapVoteCommand;
import com.breixo.culo.domain.model.Room;

public interface CuloSwapVoteUseCase {

    Room execute(CuloSwapVoteCommand culoSwapVoteCommand);
}
