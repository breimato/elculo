package com.breixo.culo.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Estado de una ronda dentro de la partida.
 * Una ronda termina cuando todos los demás jugadores pasan en cadena.
 */
@Getter
public class Round {

    /** Cantidad de cartas obligatoria (0 = aún no fijada, primer turno de la ronda). */
    @Setter
    private int requirement = 0;

    /** Rango de la última jugada (null = ronda recién abierta). */
    @Setter
    private CardRank lastRank = null;

    /** Número exacto de la última jugada (para detectar plin). */
    @Setter
    private int lastCardNumber = 0;

    /** ID del jugador que realizó la última jugada. */
    @Setter
    private String lastPlayerId = null;

    /** Pases consecutivos desde la última jugada real. */
    private int consecutivePasses = 0;

    public void registerPlay(final Play play, final String playerId) {
        this.requirement = play.size();
        this.lastRank = play.rank();
        this.lastCardNumber = play.cardNumber();
        this.lastPlayerId = playerId;
        this.consecutivePasses = 0;
    }

    public void registerPass() {
        this.consecutivePasses++;
    }

    public void reset() {
        this.requirement = 0;
        this.lastRank = null;
        this.lastCardNumber = 0;
        this.lastPlayerId = null;
        this.consecutivePasses = 0;
    }

    public boolean isOpen() {
        return this.lastRank == null;
    }
}
