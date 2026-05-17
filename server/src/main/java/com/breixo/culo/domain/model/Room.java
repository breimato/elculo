package com.breixo.culo.domain.model;

import com.breixo.culo.domain.GamePhase;
import com.breixo.culo.domain.exception.RoomException;
import com.breixo.culo.domain.exception.constants.RoomExceptionConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class Room {

    private static final int MAX_PLAYERS = 10;

    @NotBlank
    private final String code;

    @NotBlank
    private final String hostPlayerId;

    private final List<Player> players = new ArrayList<>();

    @Setter
    @NotNull
    private GamePhase phase = GamePhase.LOBBY;

    @Setter
    private Instant lastActivity = Instant.now();

    public void addPlayer(@NotNull final Player player) {
        if (this.players.size() >= MAX_PLAYERS) {
            throw new RoomException(RoomExceptionConstants.ROOM_FULL);
        }
        this.players.add(player);
        this.touch();
    }

    public Optional<Player> findPlayerByClientId(@NotBlank final String clientId) {
        return this.players.stream()
                .filter(player -> player.getClientId().equals(clientId))
                .findFirst();
    }

    public Optional<Player> findPlayerById(@NotBlank final String playerId) {
        return this.players.stream()
                .filter(player -> player.getId().equals(playerId))
                .findFirst();
    }

    public boolean isHost(@NotBlank final String playerId) {
        return this.hostPlayerId.equals(playerId);
    }

    public boolean isFull() {
        return this.players.size() >= MAX_PLAYERS;
    }

    public void touch() {
        this.lastActivity = Instant.now();
    }
}
