export enum SocketMessageType {
  PLAY = "PLAY",
  PAUSE = "PAUSE",
  NOW_PLAYING = "NOW_PLAYING",
  PLAY_CONFIRM = "PLAY_CONFIRM"
}

export type SocketMessage = {
  type: SocketMessageType;
  content?: string;
  from?: string;
  to?: string;
}
