import { motion } from 'framer-motion';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useGameStore } from '../store/gameStore';

const PHASE_LABELS: Record<string, string> = {
  LOBBY: 'Lobby',
  DEALING: 'Repartiendo cartas',
  CULO_SWAP_VOTE: 'Votación transferir culo',
  PLAYING: 'En juego',
  EXCHANGE: 'Intercambio',
  FINISHED: 'Partida terminada',
};

export function Game() {
  const navigate = useNavigate();
  const roomState = useGameStore((state) => state.roomState);
  const roomCode = useGameStore((state) => state.roomCode);

  useEffect(() => {
    if (!roomCode || !roomState) {
      navigate('/');
    }
  }, [roomCode, roomState, navigate]);

  if (!roomState) {
    return null;
  }

  return (
    <motion.div
      className="page game-page"
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
    >
      <motion.div className="card-panel game-placeholder">
        <h1>Mesa de juego</h1>
        <p className="phase">
          Fase: <strong>{PHASE_LABELS[roomState.phase] ?? roomState.phase}</strong>
        </p>
        <p className="hint">
          La mesa completa llegará en la Fase 3. Sala {roomCode} sincronizada.
        </p>
        {roomState.phase === 'LOBBY' && (
          <button type="button" className="btn-secondary" onClick={() => navigate('/lobby')}>
            Volver al lobby
          </button>
        )}
      </motion.div>

      <style>{`
        .game-page {
          display: flex;
          align-items: center;
          justify-content: center;
          min-height: 100vh;
          padding: 1.5rem;
        }
        .game-placeholder {
          max-width: 480px;
          text-align: center;
        }
        .game-placeholder h1 {
          color: var(--gold-bright);
        }
        .phase {
          font-size: 1.1rem;
        }
        .hint {
          color: var(--cream-muted);
        }
      `}</style>
    </motion.div>
  );
}
