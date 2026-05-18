import React, { useState } from 'react';
import type { Card, Player, PlayerRole, RoomState } from '../types/game';
import CardComponent from './CardComponent';
import './ExchangePanel.css';

interface ExchangePanelProps {
  roomState: RoomState;
  myPlayer: Player;
  hand: Card[];
  onGive: (cards: Card[]) => void;
}

const ROLE_INSTRUCTION: Partial<Record<PlayerRole, { count: number; receiverRole: PlayerRole; label: string }>> = {
  GANADOR: { count: 2, receiverRole: 'CULO', label: 'Elige 2 cartas para dar al Culo' },
  SUBCAMPEON: { count: 1, receiverRole: 'PENULTIMO', label: 'Elige 1 carta para dar al Penúltimo' },
};

const ExchangePanel: React.FC<ExchangePanelProps> = ({ roomState, myPlayer, hand, onGive }) => {
  const [selected, setSelected] = useState<Card[]>([]);
  const config = ROLE_INSTRUCTION[myPlayer.role];

  if (!config) {
    const ganadorPlayer = roomState.players.find((p) => p.role === 'GANADOR');
    return (
      <div className="exchange-panel exchange-panel--waiting">
        <h2>Fase de Intercambio</h2>
        <p>
          {myPlayer.role === 'CULO'
            ? `${ganadorPlayer?.nick ?? 'El ganador'} elige 2 cartas de su mano para darte…`
            : myPlayer.role === 'PENULTIMO'
            ? `${roomState.players.find((p) => p.role === 'SUBCAMPEON')?.nick ?? 'El subcampeón'} está eligiendo…`
            : 'Esperando al intercambio…'}
        </p>
        <div className="exchange-panel__hand exchange-panel__hand--display">
          {hand.map((card, i) => (
            <CardComponent key={`${card.suit}-${card.number}-${i}`} card={card} />
          ))}
        </div>
      </div>
    );
  }

  const receiver = roomState.players.find((p) => p.role === config.receiverRole);

  const toggleCard = (card: Card) => {
    const isIn = selected.some((s) => s.suit === card.suit && s.number === card.number);
    if (isIn) {
      setSelected(selected.filter((s) => !(s.suit === card.suit && s.number === card.number)));
    } else if (selected.length < config.count) {
      setSelected([...selected, card]);
    }
  };

  const canConfirm = selected.length === config.count;

  return (
    <div className="exchange-panel">
      <h2 className="exchange-panel__title">Intercambio de Cartas</h2>
      <p className="exchange-panel__instruction">
        {config.label} ({receiver?.nick ?? '?'})
      </p>
      <div className="exchange-panel__hand">
        {hand.map((card, i) => (
          <CardComponent
            key={`${card.suit}-${card.number}-${i}`}
            card={card}
            selected={selected.some((s) => s.suit === card.suit && s.number === card.number)}
            onClick={() => toggleCard(card)}
          />
        ))}
      </div>
      <div className="exchange-panel__footer">
        <p className="exchange-panel__hint">
          {selected.length} / {config.count} seleccionadas
        </p>
        <button
          className="exchange-panel__btn"
          disabled={!canConfirm}
          onClick={() => onGive(selected)}
        >
          Dar cartas a {receiver?.nick ?? '?'}
        </button>
      </div>
    </div>
  );
};

export default ExchangePanel;
