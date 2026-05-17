import { Client } from '@stomp/stompjs';
import type { JoinedRoom, RoomState, WsError } from '../types/game';

const WS_URL = import.meta.env.VITE_WS_URL ?? 'http://localhost:8080/ws';

let stompClient: Client | null = null;

async function buildClient(): Promise<Client> {
  const { default: SockJS } = await import('sockjs-client');
  return new Client({
    webSocketFactory: () => new SockJS(WS_URL),
    reconnectDelay: 5000,
    heartbeatIncoming: 10000,
    heartbeatOutgoing: 10000,
    debug: import.meta.env.DEV
      ? (msg) => console.debug('[STOMP]', msg)
      : undefined,
  });
}

/**
 * Guarantees the STOMP client is connected before resolving.
 * Calls onConnected once the underlying WS + STOMP handshake succeeds.
 */
export async function connectStomp(
  onConnected: () => void | Promise<void>,
): Promise<void> {
  if (!stompClient) {
    stompClient = await buildClient();
  }
  const client = stompClient;

  if (client.connected) {
    await onConnected();
    return;
  }

  return new Promise((resolve, reject) => {
    client.onConnect = async () => {
      try {
        await onConnected();
      } catch (err) {
        reject(err);
      }
      resolve();
    };
    client.onStompError = (frame) => {
      reject(new Error(frame.headers['message'] ?? 'STOMP error'));
    };
    if (!client.active) {
      client.activate();
    }
  });
}

export function disconnectStomp(): void {
  if (stompClient?.active) {
    stompClient.deactivate();
    stompClient = null;
  }
}

/** Subscribe to client-specific topics. Must be called inside connectStomp's onConnected. */
export function subscribeClientTopics(
  clientId: string,
  handlers: {
    onJoined: (joinedRoom: JoinedRoom) => void;
    onError: (wsError: WsError) => void;
  },
): () => void {
  const client = stompClient!;
  const joinedSub = client.subscribe(
    `/topic/client/${clientId}/joinedRoom`,
    (msg) => handlers.onJoined(JSON.parse(msg.body) as JoinedRoom),
  );
  const errorSub = client.subscribe(
    `/topic/client/${clientId}/error`,
    (msg) => handlers.onError(JSON.parse(msg.body) as WsError),
  );
  return () => {
    joinedSub.unsubscribe();
    errorSub.unsubscribe();
  };
}

/** Subscribe to room state broadcasts. Must be called inside connectStomp's onConnected. */
export function subscribeRoomTopic(
  roomCode: string,
  onRoomState: (roomState: RoomState) => void,
): () => void {
  const client = stompClient!;
  const sub = client.subscribe(
    `/topic/room/${roomCode}/roomState`,
    (msg) => onRoomState(JSON.parse(msg.body) as RoomState),
  );
  return () => sub.unsubscribe();
}

export function sendCreateRoom(clientId: string, nick: string): void {
  stompClient!.publish({
    destination: '/app/room.create',
    body: JSON.stringify({ clientId, nick }),
  });
}

export function sendJoinRoom(clientId: string, roomCode: string, nick: string): void {
  stompClient!.publish({
    destination: '/app/room.join',
    body: JSON.stringify({ clientId, roomCode: roomCode.toUpperCase(), nick }),
  });
}

export function sendStartGame(clientId: string, roomCode: string): void {
  stompClient!.publish({
    destination: '/app/room.start',
    body: JSON.stringify({ clientId, roomCode }),
  });
}
