import React from 'react';
import type { Card } from '../types/game';
import CardComponent from './CardComponent';
import './Hand.css';

interface HandProps {
  cards: Card[];
  selectedCards: Card[];
  onToggleCard: (card: Card) => void;
  disabled?: boolean;
}

const cardKey = (card: Card) => `${card.suit}-${card.number}`;

const Hand: React.FC<HandProps> = ({ cards, selectedCards, onToggleCard, disabled = false }) => {
  const isSelected = (card: Card) =>
    selectedCards.some((s) => s.suit === card.suit && s.number === card.number);

  return (
    <div className="hand">
      {cards.map((card, idx) => (
        <div
          key={`${cardKey(card)}-${idx}`}
          className="hand__card-wrapper"
          style={{ '--idx': idx, '--total': cards.length } as React.CSSProperties}
        >
          <CardComponent
            card={card}
            selected={isSelected(card)}
            disabled={disabled}
            onClick={() => onToggleCard(card)}
          />
        </div>
      ))}
    </div>
  );
};

export default Hand;
