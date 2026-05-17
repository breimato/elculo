import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import type { RoomState, WsError } from '../types/game';

interface GameStore {
  clientId: string;
  nick: string;
  playerId: string | null;
  roomCode: string | null;
  roomState: RoomState | null;
  lastError: WsError | null;
  setNick: (nick: string) => void;
  setJoined: (roomCode: string, playerId: string) => void;
  setRoomState: (roomState: RoomState) => void;
  setError: (wsError: WsError | null) => void;
  reset: () => void;
}

const generateClientId = (): string => crypto.randomUUID();

export const useGameStore = create<GameStore>()(
  persist(
    (set) => ({
      clientId: generateClientId(),
      nick: '',
      playerId: null,
      roomCode: null,
      roomState: null,
      lastError: null,
      setNick: (nick) => set({ nick }),
      setJoined: (roomCode, playerId) => set({ roomCode, playerId }),
      setRoomState: (roomState) => set({ roomState }),
      setError: (lastError) => set({ lastError }),
      reset: () =>
        set({
          playerId: null,
          roomCode: null,
          roomState: null,
          lastError: null,
        }),
    }),
    {
      name: 'culo-game',
      partialize: (state) => ({
        clientId: state.clientId,
        nick: state.nick,
      }),
    },
  ),
);
