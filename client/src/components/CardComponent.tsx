import React from 'react';
import type { Card, Suit } from '../types/game';
import './CardComponent.css';

interface CardComponentProps {
  card: Card;
  selected?: boolean;
  disabled?: boolean;
  faceDown?: boolean;
  onClick?: () => void;
}

const SUIT_SYMBOL: Record<Suit, string> = {
  OROS: '⊙',
  COPAS: '⚱',
  ESPADAS: '⚔',
  BASTOS: '🌿',
};

const SUIT_COLOR: Record<Suit, string> = {
  OROS: '#c9a227',
  COPAS: '#8b1a1a',
  ESPADAS: '#1a1a3e',
  BASTOS: '#2d5a27',
};

const NUMBER_DISPLAY: Record<number, string> = {
  1: 'A',
  2: '2',
  3: '3',
  4: '4',
  5: '5',
  6: '6',
  7: '7',
  10: 'S',
  11: 'C',
  12: 'R',
};

const CardComponent: React.FC<CardComponentProps> = ({
  card,
  selected = false,
  disabled = false,
  faceDown = false,
  onClick,
}) => {
  if (faceDown) {
    return (
      <div className="card card--back">
        <div className="card__back-pattern" />
      </div>
    );
  }

  const color = SUIT_COLOR[card.suit];
  const symbol = SUIT_SYMBOL[card.suit];
  const label = NUMBER_DISPLAY[card.number] ?? String(card.number);

  return (
    <div
      className={`card${selected ? ' card--selected' : ''}${disabled ? ' card--disabled' : ''}`}
      style={{ '--card-color': color } as React.CSSProperties}
      onClick={!disabled ? onClick : undefined}
      role={onClick ? 'button' : undefined}
      aria-pressed={selected}
    >
      <span className="card__corner card__corner--top">{label}</span>
      <span className="card__suit">{symbol}</span>
      <span className="card__corner card__corner--bottom">{label}</span>
    </div>
  );
};

export default CardComponent;
