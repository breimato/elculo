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

const getFanStyle = (idx: number, total: number): React.CSSProperties => {
  const center = (total - 1) / 2;
  const maxDistance = Math.max(center, 1);
  const distanceFromCenter = Math.abs(idx - center);
  const normalizedFromCenter = total <= 1 ? 0 : (idx - center) / maxDistance;
  const edgeDistance = total <= 1 ? 0 : distanceFromCenter / maxDistance;
  const centerLift = (1 - edgeDistance * edgeDistance) * 42;

  return {
    '--idx': idx,
    '--total': total,
    '--fan-rotate': `${normalizedFromCenter * 6}deg`,
    '--fan-lift': `-${centerLift}px`,
  } as React.CSSProperties;
};

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
          style={getFanStyle(idx, visibleCards.length)}
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
