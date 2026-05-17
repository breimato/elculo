package com.breixo.culo.application.usecase.game;

import com.breixo.culo.domain.GamePhase;
import com.breixo.culo.domain.PlayerRole;
import com.breixo.culo.domain.RuleEngine;
import com.breixo.culo.domain.command.game.PlayCardsCommand;
import com.breixo.culo.domain.exception.GameException;
import com.breixo.culo.domain.exception.RoomException;
import com.breixo.culo.domain.exception.constants.GameExceptionConstants;
import com.breixo.culo.domain.exception.constants.RoomExceptionConstants;
import com.breixo.culo.domain.model.Card;
import com.breixo.culo.domain.model.Play;
import com.breixo.culo.domain.model.Room;
import com.breixo.culo.domain.model.game.PlayResult;
import com.breixo.culo.domain.port.input.game.PlayCardsUseCase;
import com.breixo.culo.domain.port.output.room.RoomPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayCardsUseCaseImpl implements PlayCardsUseCase {

  private static final RuleEngine RULE_ENGINE = new RuleEngine();

  /** The room persistence port. */
  private final RoomPersistencePort roomPersistencePort;

  @Override
  public PlayResult execute(final PlayCardsCommand playCardsCommand) {
    final var room = this.roomPersistencePort.findByCode(playCardsCommand.roomCode())
        .orElseThrow(() -> new RoomException(RoomExceptionConstants.ROOM_NOT_FOUND));
    final var player = room.findPlayerByClientId(playCardsCommand.clientId())
        .orElseThrow(() -> new RoomException(RoomExceptionConstants.PLAYER_NOT_IN_ROOM));
    if (!room.getPhase().equals(GamePhase.PLAYING)) {
      throw new GameException(GameExceptionConstants.WRONG_PHASE);
    }
    if (!player.getId().equals(room.getCurrentPlayerId())) {
      throw new GameException(GameExceptionConstants.NOT_YOUR_TURN);
    }

    final var cards = this.toCards(playCardsCommand, room, player.getId());
    final var play = Play.builder().cards(cards).build();
    final var round = room.getCurrentRound();

    if (!RULE_ENGINE.isLegal(play, round)) {
      throw new GameException(GameExceptionConstants.ILLEGAL_PLAY);
    }

    final var plin = RULE_ENGINE.isPlin(play, round);
    final var isAsOros = play.isAsOros();

    room.getHand(player.getId()).removeAll(cards);

    if (isAsOros) {
      round.reset();
    } else {
      round.registerPlay(play, player.getId());
    }

    final var playerOut = room.getHand(player.getId()).isEmpty();
    boolean gameEnded = false;
    if (playerOut) {
      gameEnded = room.registerPlayerOut(player.getId());
    }

    if (gameEnded) {
      room.setPhase(GamePhase.EXCHANGE);
      this.triggerAutoExchange(room);
    } else {
      room.advanceTurn(plin && !isAsOros);
    }

    final var savedRoom = this.roomPersistencePort.save(room);
    return PlayResult.builder()
        .room(savedRoom)
        .playerId(player.getId())
        .play(play)
        .plin(plin && !isAsOros)
        .roundEnded(isAsOros)
        .gameEnded(gameEnded)
        .build();
  }

  private List<Card> toCards(final PlayCardsCommand command, final Room room, final String playerId) {
    final var hand = room.getHand(playerId);
    final var cards = command.cards().stream()
        .map(input -> Card.builder().suit(input.suit()).number(input.number()).build())
        .toList();
    if (!hand.containsAll(cards)) {
      throw new GameException(GameExceptionConstants.CARDS_NOT_IN_HAND);
    }
    return cards;
  }

  /**
   * Prepara el intercambio automático: las mejores cartas del culo van al ganador
   * y la mejor del penúltimo va al subcampeón. Todo lo automático ocurre aquí;
   * el ganador/subcampeón elegirán qué devolver con ExchangeGiveUseCase.
   */
  private void triggerAutoExchange(final Room room) {
    this.autoTransferBest(room, PlayerRole.CULO, PlayerRole.GANADOR, 2,
        room.getPendingExchangeGanadorToCulo());
    this.autoTransferBest(room, PlayerRole.PENULTIMO, PlayerRole.SUBCAMPEON, 1,
        room.getPendingExchangeSubcampeonToPenultimo());
  }

  private void autoTransferBest(
      final Room room,
      final PlayerRole giver,
      final PlayerRole receiver,
      final int count,
      final List<Card> pendingList) {
    final var giverId = room.getPlayerIdByRole(giver);
    final var receiverId = room.getPlayerIdByRole(receiver);
    if (giverId.isEmpty() || receiverId.isEmpty()) {
      return;
    }
    final var giverHand = room.getHand(giverId.get());
    final var best = giverHand.stream()
        .sorted((a, b) -> Integer.compare(
            b.number() == 1 ? 999 : b.number(),
            a.number() == 1 ? 999 : a.number()))
        .limit(count)
        .toList();
    giverHand.removeAll(best);
    room.getHand(receiverId.get()).addAll(best);
    pendingList.addAll(best);
  }
}
