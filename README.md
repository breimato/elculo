# El Culo

Juego de cartas multijugador en tiempo real (baraja española de 40 cartas).

## Estructura

- `server/` — Spring Boot 3, arquitectura hexagonal, WebSocket STOMP, DTOs generados con OpenAPI Generator
- `client/` — React 18 + Vite + TypeScript + Zustand

## Arranque local

### Backend (puerto 8080)

```bash
cd server
mvn spring-boot:run
```

### Frontend (puerto 5173)

```bash
cd client
pnpm install
pnpm dev
```

Abre varias ventanas en `http://localhost:5173`, crea una sala y únete con el código.

## Tests

```bash
cd server
mvn test
```

## WebSocket

- Endpoint SockJS: `http://localhost:8080/ws`
- Cliente → `/app/room.create`, `/app/room.join`, `/app/room.start`
- Servidor → `/topic/room/{code}/roomState`, `/topic/client/{clientId}/joinedRoom`
