import React from 'react';
import type { Card } from '../types/game';
import CardComponent from './CardComponent';
import './Hand.css';

interface HandProps {
  cards: Card[];
  selectedCards: Card[];
  hiddenCards?: Card[];
  onToggleCard: (card: Card) => void;
  disabled?: boolean;
}

const cardKey = (card: Card) => `${card.suit}-${card.number}`;

const isSameCard = (a: Card, b: Card) => a.suit === b.suit && a.number === b.number;

const Hand: React.FC<HandProps> = ({
  cards,
  selectedCards,
  hiddenCards = [],
  onToggleCard,
  disabled = false,
}) => {
  const hiddenSet = new Set(hiddenCards.map(cardKey));
  const visibleCards = cards.filter((c) => !hiddenSet.has(cardKey(c)));

  const isSelected = (card: Card) => selectedCards.some((s) => isSameCard(s, card));

  return (
    <div className="hand">
      {visibleCards.map((card, idx) => (
        <div
          key={`${cardKey(card)}-${idx}`}
          className="hand__card-wrapper"
          style={
            {
              '--idx': idx,
              '--total': visibleCards.length,
            } as React.CSSProperties
          }
        >
          <CardComponent
            card={card}
            size="hand"
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
