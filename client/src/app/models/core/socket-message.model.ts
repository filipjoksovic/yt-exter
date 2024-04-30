export enum SocketMessageType {
  PLAY = "PLAY",
  PAUSE = "PAUSE",
  NOW_PLAYING = "NOW_PLAYING",
  PLAY_CONFIRM = "PLAY_CONFIRM",
  GET_NOW_PLAYING = "GET_NOW_PLAYING",
  PLAY_CONFIRMED = "PLAY_CONFIRMED"
}

export type SocketMessage = {
  type: SocketMessageType;
  content?: string;
  from?: string;
  to?: string;
}
