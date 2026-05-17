package com.breixo.culo.domain;

import com.breixo.culo.domain.model.Card;
import com.breixo.culo.domain.model.CardRank;
import com.breixo.culo.domain.model.Play;
import com.breixo.culo.domain.model.Round;
import com.breixo.culo.domain.model.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The Class Rule Engine Test. */
class RuleEngineTest {

    RuleEngine ruleEngine;

    @BeforeEach
    void setUp() {
        this.ruleEngine = new RuleEngine();
    }

    // ─── Ronda abierta (primer turno) ───────────────────────────────────────

    /** Test isLegal when round is open then any single is legal. */
    @Test
    void testIsLegal_whenRoundIsOpen_thenAnySingleIsLegal() {
        final var round = new Round();
        final var play = makeSingle(Suit.OROS, 4);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when round is open then any pair is legal. */
    @Test
    void testIsLegal_whenRoundIsOpen_thenAnyPairIsLegal() {
        final var round = new Round();
        final var play = makePair(3);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when round is open then any trio is legal. */
    @Test
    void testIsLegal_whenRoundIsOpen_thenAnyTrioIsLegal() {
        final var round = new Round();
        final var play = makeTrio(7);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    // ─── Singles ─────────────────────────────────────────────────────────────

    /** Test isLegal when single higher rank then legal. */
    @Test
    void testIsLegal_whenSingleHigherRank_thenLegal() {
        final var round = roundWithLastPlay(makeSingle(Suit.COPAS, 5));
        final var play = makeSingle(Suit.OROS, 7);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when single same rank then legal. */
    @Test
    void testIsLegal_whenSingleSameRank_thenLegal() {
        final var round = roundWithLastPlay(makeSingle(Suit.COPAS, 6));
        final var play = makeSingle(Suit.ESPADAS, 6);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when single lower rank then illegal. */
    @Test
    void testIsLegal_whenSingleLowerRank_thenIllegal() {
        final var round = roundWithLastPlay(makeSingle(Suit.COPAS, 7));
        final var play = makeSingle(Suit.OROS, 5);

        assertFalse(this.ruleEngine.isLegal(play, round));
    }

    // ─── Pares ───────────────────────────────────────────────────────────────

    /** Test isLegal when pair after pair higher rank then legal. */
    @Test
    void testIsLegal_whenPairAfterPairHigherRank_thenLegal() {
        final var round = roundWithLastPlay(makePair(4));
        final var play = makePair(6);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when single after pair then illegal. */
    @Test
    void testIsLegal_whenSingleAfterPair_thenIllegal() {
        final var round = roundWithLastPlay(makePair(5));
        final var play = makeSingle(Suit.OROS, 12);

        assertFalse(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when pair after single then illegal. */
    @Test
    void testIsLegal_whenPairAfterSingle_thenIllegal() {
        final var round = roundWithLastPlay(makeSingle(Suit.OROS, 2));
        final var play = makePair(3);

        assertFalse(this.ruleEngine.isLegal(play, round));
    }

    // ─── Trios ───────────────────────────────────────────────────────────────

    /** Test isLegal when trio after trio same rank then legal. */
    @Test
    void testIsLegal_whenTrioAfterTrioSameRank_thenLegal() {
        final var round = roundWithLastPlay(makeTrio(10));
        final var play = makeTrio(10);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when trio lower rank then illegal. */
    @Test
    void testIsLegal_whenTrioLowerRank_thenIllegal() {
        final var round = roundWithLastPlay(makeTrio(12));
        final var play = makeTrio(10);

        assertFalse(this.ruleEngine.isLegal(play, round));
    }

    // ─── AS de oros ──────────────────────────────────────────────────────────

    /** Test isLegal when as oros against trio then always legal. */
    @Test
    void testIsLegal_whenAsOrosAgainstTrio_thenAlwaysLegal() {
        final var round = roundWithLastPlay(makeTrio(12));
        final var play = makeSingle(Suit.OROS, 1);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when as oros against pair then always legal. */
    @Test
    void testIsLegal_whenAsOrosAgainstPair_thenAlwaysLegal() {
        final var round = roundWithLastPlay(makePair(3));
        final var play = makeSingle(Suit.OROS, 1);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when as otro then must match requirement. */
    @Test
    void testIsLegal_whenAsOtro_thenMustMatchRequirement() {
        final var round = roundWithLastPlay(makePair(7));
        final var play = makeSingle(Suit.COPAS, 1);

        assertFalse(this.ruleEngine.isLegal(play, round));
    }

    // ─── Rango 3 y Ases ──────────────────────────────────────────────────────

    /** Test isLegal when tres after rey then legal. */
    @Test
    void testIsLegal_whenTresAfterRey_thenLegal() {
        final var round = roundWithLastPlay(makeSingle(Suit.COPAS, 12));
        final var play = makeSingle(Suit.OROS, 3);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    /** Test isLegal when as otro after tres then legal. */
    @Test
    void testIsLegal_whenAsOtroAfterTres_thenLegal() {
        final var round = roundWithLastPlay(makeSingle(Suit.COPAS, 3));
        final var play = makeSingle(Suit.COPAS, 1);

        assertTrue(this.ruleEngine.isLegal(play, round));
    }

    // ─── Plin ────────────────────────────────────────────────────────────────

    /** Test isPlin when same number then plin. */
    @Test
    void testIsPlin_whenSameNumber_thenPlin() {
        final var round = roundWithLastPlay(makeSingle(Suit.COPAS, 7));
        final var play = makeSingle(Suit.ESPADAS, 7);

        assertTrue(this.ruleEngine.isPlin(play, round));
    }

    /** Test isPlin when different number then no plin. */
    @Test
    void testIsPlin_whenDifferentNumber_thenNoPlin() {
        final var round = roundWithLastPlay(makeSingle(Suit.COPAS, 6));
        final var play = makeSingle(Suit.ESPADAS, 7);

        assertFalse(this.ruleEngine.isPlin(play, round));
    }

    /** Test isPlin when round is open then no plin. */
    @Test
    void testIsPlin_whenRoundIsOpen_thenNoPlin() {
        final var round = new Round();
        final var play = makeSingle(Suit.OROS, 7);

        assertFalse(this.ruleEngine.isPlin(play, round));
    }

    // ─── Fin de ronda ────────────────────────────────────────────────────────

    /** Test isRoundOver when all others passed then round over. */
    @Test
    void testIsRoundOver_whenAllOthersPassed_thenRoundOver() {
        final var round = roundWithLastPlay(makeSingle(Suit.OROS, 5));
        round.registerPass();
        round.registerPass();
        round.registerPass();

        assertTrue(this.ruleEngine.isRoundOver(round, 4));
    }

    /** Test isRoundOver when not all passed then not over. */
    @Test
    void testIsRoundOver_whenNotAllPassed_thenNotOver() {
        final var round = roundWithLastPlay(makeSingle(Suit.OROS, 5));
        round.registerPass();

        assertFalse(this.ruleEngine.isRoundOver(round, 4));
    }

    /** Test isRoundOver when round is open then not over. */
    @Test
    void testIsRoundOver_whenRoundIsOpen_thenNotOver() {
        final var round = new Round();
        round.registerPass();
        round.registerPass();

        assertFalse(this.ruleEngine.isRoundOver(round, 3));
    }

    // ─── CardRank ─────────────────────────────────────────────────────────────

    /** Test cardRank when as oros then highest. */
    @Test
    void testCardRank_whenAsOros_thenHighest() {
        final var asOros = CardRank.of(Card.builder().suit(Suit.OROS).number(1).build());
        final var asOtro = CardRank.of(Card.builder().suit(Suit.COPAS).number(1).build());

        assertTrue(asOros.isHigherOrEqualThan(asOtro));
        assertFalse(asOtro.isHigherOrEqualThan(asOros));
    }

    /** Test cardRank when dos then lowest. */
    @Test
    void testCardRank_whenDos_thenLowest() {
        final var dos = CardRank.of(Card.builder().suit(Suit.OROS).number(2).build());
        final var cuatro = CardRank.of(Card.builder().suit(Suit.OROS).number(4).build());

        assertFalse(dos.isHigherOrEqualThan(cuatro));
        assertTrue(cuatro.isHigherOrEqualThan(dos));
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private Play makeSingle(final Suit suit, final int number) {
        return Play.builder()
                .cards(List.of(Card.builder().suit(suit).number(number).build()))
                .build();
    }

    private Play makePair(final int number) {
        return Play.builder()
                .cards(List.of(
                        Card.builder().suit(Suit.OROS).number(number).build(),
                        Card.builder().suit(Suit.COPAS).number(number).build()))
                .build();
    }

    private Play makeTrio(final int number) {
        return Play.builder()
                .cards(List.of(
                        Card.builder().suit(Suit.OROS).number(number).build(),
                        Card.builder().suit(Suit.COPAS).number(number).build(),
                        Card.builder().suit(Suit.ESPADAS).number(number).build()))
                .build();
    }

    private Round roundWithLastPlay(final Play lastPlay) {
        final var round = new Round();
        round.registerPlay(lastPlay, "player-1");
        return round;
    }
}
