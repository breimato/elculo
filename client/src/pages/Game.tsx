import { AnimatePresence, motion } from 'framer-motion';
import React, { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import CardComponent from '../components/CardComponent';
import CuloSwapModal from '../components/CuloSwapModal';
import ExchangePanel from '../components/ExchangePanel';
import Hand from '../components/Hand';
import PlayerSlot from '../components/PlayerSlot';
import { useGameStore } from '../store/gameStore';
import type { Card } from '../types/game';
import {
  sendCuloSwapInitiate,
  sendCuloSwapVote,
  sendDealCards,
  sendExchangeGive,
  sendPass,
  sendPlayCards,
  subscribeClientTopics,
  subscribeRoomTopic,
} from '../ws/stompClient';
import './Game.css';

const RANK_LABEL: Record<string, string> = {
  DOS: '2',
  CUATRO: '4',
  CINCO: '5',
  SEIS: '6',
  SIETE: '7',
  SOTA: 'Sota',
  CABALLO: 'Caballo',
  REY: 'Rey',
  TRES: '3',
  AS_OTRO: 'As',
  AS_OROS: 'As de Oros',
};

const REQ_LABEL: Record<number, string> = {
  0: 'Libre',
  1: 'Sueltas',
  2: 'Pares',
  3: 'Tríos',
};

const Game: React.FC = () => {
  const navigate = useNavigate();
  const { clientId, playerId, roomCode, roomState, hand, setRoomState, setHand, setRanking, setError } =
    useGameStore();

  const [selectedCards, setSelectedCards] = useState<Card[]>([]);
  const [notification, setNotification] = useState<string | null>(null);
  const [swapTarget, setSwapTarget] = useState<string | null>(null);

  const cleanupRef = useRef<(() => void)[]>([]);

  const myPlayer = roomState?.players.find((p) => p.id === playerId) ?? null;
  const isMyTurn = roomState?.currentPlayerId === playerId;
  const isCulo = myPlayer?.role === 'CULO';
  const phase = roomState?.phase ?? 'LOBBY';

  const showNotification = (msg: string) => {
    setNotification(msg);
    setTimeout(() => setNotification(null), 3000);
  };

  useEffect(() => {
    if (!roomCode || !playerId || !clientId) {
      navigate('/');
      return;
    }

    const unsubRoom = subscribeRoomTopic(roomCode, {
      onRoomState: (rs) => setRoomState(rs),
      onPlayMade: (pm) => {
        const player = roomState?.players.find((p) => p.id === pm.playerId);
        const nick = player?.nick ?? pm.playerId;
        const suffix = pm.plin ? ' ¡PLIN!' : pm.isAsOros ? ' ¡As de Oros!' : '';
        showNotification(`${nick} jugó ${pm.cards.length} carta(s)${suffix}`);
      },
      onRoundEnded: (re) => {
        const player = roomState?.players.find((p) => p.id === re.winnerPlayerId);
        showNotification(`${player?.nick ?? re.winnerPlayerId} abre nueva ronda`);
      },
      onGameEnded: (ge) => {
        setRanking(ge.ranking);
        showNotification('¡Partida terminada!');
      },
      onCuloSwapRequest: () => {
        showNotification('🍑 ¡Votación de transferencia de culo!');
      },
      onCuloSwapResult: (result) => {
        if (result.accepted) {
          showNotification('✅ Transferencia aceptada');
        } else {
          showNotification('❌ Transferencia rechazada');
        }
      },
    });

    const unsubClient = subscribeClientTopics(clientId, {
      onJoined: () => {},
      onError: (err) => {
        setError(err);
        showNotification(`Error: ${err.message}`);
      },
      onHandUpdate: (hu) => setHand(hu.cards),
    });

    cleanupRef.current = [unsubRoom, unsubClient];

    return () => {
      cleanupRef.current.forEach((fn) => fn());
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [roomCode, playerId, clientId]);

  if (!roomState || !myPlayer) {
    return (
      <div className="game-loading">
        <div className="spinner" />
        <p>Conectando a la partida…</p>
      </div>
    );
  }

  const toggleCard = (card: Card) => {
    const isIn = selectedCards.some((s) => s.suit === card.suit && s.number === card.number);
    if (isIn) {
      setSelectedCards(selectedCards.filter((s) => !(s.suit === card.suit && s.number === card.number)));
    } else {
      setSelectedCards([...selectedCards, card]);
    }
  };

  const handlePlay = () => {
    if (selectedCards.length === 0) return;
    sendPlayCards(clientId, roomCode!, selectedCards);
    setSelectedCards([]);
  };

  const handlePass = () => {
    sendPass(clientId, roomCode!);
    setSelectedCards([]);
  };

  const handleDeal = () => {
    sendDealCards(clientId, roomCode!);
  };

  const handleCuloSwapInitiate = () => {
    if (!swapTarget) return;
    sendCuloSwapInitiate(clientId, roomCode!, swapTarget);
    setSwapTarget(null);
  };

  const handleCuloSwapVote = (accept: boolean) => {
    sendCuloSwapVote(clientId, roomCode!, accept);
  };

  const handleExchangeGive = (cards: Card[]) => {
    sendExchangeGive(clientId, roomCode!, cards);
  };

  const otherPlayers = roomState.players.filter((p) => p.id !== playerId);

  const canPlay =
    isMyTurn &&
    phase === 'PLAYING' &&
    selectedCards.length > 0;

  // ─── EXCHANGE phase ────────────────────────────────────────────────────────
  if (phase === 'EXCHANGE') {
    return (
      <div className="game">
        <CuloSwapModal
          roomState={roomState}
          myPlayerId={playerId!}
          onVote={handleCuloSwapVote}
        />
        <div className="game__exchange">
          <ExchangePanel
            roomState={roomState}
            myPlayer={myPlayer}
            hand={hand}
            onGive={handleExchangeGive}
          />
        </div>
        {notification && (
          <div className="game__notification">{notification}</div>
        )}
      </div>
    );
  }

  // ─── DEALING phase ─────────────────────────────────────────────────────────
  if (phase === 'DEALING') {
    const canDeal = isCulo || (roomState.hostPlayerId === playerId && !roomState.players.some((p) => p.role === 'CULO'));
    const otherPlayersList = roomState.players.filter((p) => p.id !== playerId);

    return (
      <div className="game game--dealing">
        <CuloSwapModal
          roomState={roomState}
          myPlayerId={playerId!}
          onVote={handleCuloSwapVote}
        />
        <h2 className="game__phase-title">Fase de Reparto</h2>
        <div className="game__players-list">
          {roomState.players.map((p) => (
            <PlayerSlot
              key={p.id}
              player={p}
              isCurrentPlayer={false}
              isMe={p.id === playerId}
            />
          ))}
        </div>
        {isCulo && phase === 'DEALING' && (
          <div className="game__swap-section">
            <h3>¿Transferir culo?</h3>
            <select
              value={swapTarget ?? ''}
              onChange={(e) => setSwapTarget(e.target.value || null)}
              className="game__swap-select"
            >
              <option value="">Selecciona objetivo…</option>
              {otherPlayersList.map((p) => (
                <option key={p.id} value={p.id}>
                  {p.nick}
                </option>
              ))}
            </select>
            <button
              className="btn btn--secondary"
              disabled={!swapTarget}
              onClick={handleCuloSwapInitiate}
            >
              Iniciar votación
            </button>
          </div>
        )}
        {canDeal && (
          <button className="btn btn--primary game__deal-btn" onClick={handleDeal}>
            🃏 Repartir cartas
          </button>
        )}
        {!canDeal && (
          <p className="game__waiting">Esperando a que el culo reparta…</p>
        )}
        {notification && <div className="game__notification">{notification}</div>}
      </div>
    );
  }

  // ─── CULO_SWAP_VOTE phase ──────────────────────────────────────────────────
  if (phase === 'CULO_SWAP_VOTE') {
    return (
      <div className="game">
        <CuloSwapModal
          roomState={roomState}
          myPlayerId={playerId!}
          onVote={handleCuloSwapVote}
        />
        {notification && <div className="game__notification">{notification}</div>}
      </div>
    );
  }

  // ─── PLAYING phase ─────────────────────────────────────────────────────────
  return (
    <div className="game">
      {/* Notifications */}
      <AnimatePresence>
        {notification && (
          <motion.div
            className="game__notification"
            initial={{ opacity: 0, y: -20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -20 }}
          >
            {notification}
          </motion.div>
        )}
      </AnimatePresence>

      {/* Top: other players */}
      <div className="game__players-ring">
        {otherPlayers.map((player) => (
          <PlayerSlot
            key={player.id}
            player={player}
            isCurrentPlayer={player.id === roomState.currentPlayerId}
            isMe={false}
          />
        ))}
      </div>

      {/* Center: table */}
      <div className="game__table">
        {/* Round info */}
        <div className="game__round-info">
          <span>{REQ_LABEL[roomState.roundRequirement] ?? 'Libre'}</span>
          {roomState.lastRankName && (
            <span className="game__last-rank">
              Mínimo: {RANK_LABEL[roomState.lastRankName]}
            </span>
          )}
        </div>

        {/* Last played cards */}
        <div className="game__last-play">
          {roomState.lastPlayedCards && roomState.lastPlayedCards.length > 0 ? (
            roomState.lastPlayedCards.map((card, i) => (
              <CardComponent key={`last-${card.suit}-${card.number}-${i}`} card={card} />
            ))
          ) : (
            <p className="game__no-play">Abre la ronda</p>
          )}
        </div>

        {/* My slot (current player indicator) */}
        <PlayerSlot
          player={myPlayer}
          isCurrentPlayer={isMyTurn}
          isMe={true}
        />
      </div>

      {/* Action buttons */}
      <div className="game__actions">
        <button
          className="btn btn--primary"
          disabled={!canPlay}
          onClick={handlePlay}
        >
          ▶ Jugar ({selectedCards.length})
        </button>
        <button
          className="btn btn--secondary"
          disabled={!isMyTurn || phase !== 'PLAYING'}
          onClick={handlePass}
        >
          ⏭ Pasar
        </button>
      </div>

      {/* My hand */}
      <Hand
        cards={hand}
        selectedCards={selectedCards}
        onToggleCard={toggleCard}
        disabled={!isMyTurn}
      />
    </div>
  );
};

export default Game;
