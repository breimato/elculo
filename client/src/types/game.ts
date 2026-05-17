export type GamePhase =
  | 'LOBBY'
  | 'DEALING'
  | 'CULO_SWAP_VOTE'
  | 'PLAYING'
  | 'EXCHANGE'
  | 'FINISHED';

export type PlayerRole =
  | 'NONE'
  | 'GANADOR'
  | 'SUBCAMPEON'
  | 'PENULTIMO'
  | 'CULO';

export interface Player {
  id: string;
  nick: string;
  connected: boolean;
  role: PlayerRole;
}

export interface RoomState {
  roomCode: string;
  hostPlayerId: string;
  phase: GamePhase;
  players: Player[];
}

export interface JoinedRoom {
  roomCode: string;
  playerId: string;
}

export interface WsError {
  code: string;
  message: string;
}
