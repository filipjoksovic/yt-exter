package org.yt.exter.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yt.exter.api.NowPlayingResource;
import org.yt.exter.model.PlayerMessage;
import org.yt.exter.model.enums.MessageType;
import org.yt.exter.resource.NowPlayingResourceImpl;

import java.util.Map;

@ServerEndpoint("/player/{username}")
@ApplicationScoped
public class PlayerSocket {

    private static final Logger log = LoggerFactory.getLogger(PlayerSocket.class);
    Map<String, Session> sessions = new java.util.concurrent.ConcurrentHashMap<>();

    @Inject
    NowPlayingResource nowPlayingResource;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
//        broadcast("New user connected: " + session.getId());
        sessions.put(username, session);
    }

    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }

    private void broadcast(PlayerMessage message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }

    @OnError
    public void onError(Session session, @PathParam("username") String username, Throwable throwable) {
        sessions.remove(username);
//        broadcast("User " + username + " left on error: " + throwable);
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(username);
//        broadcast("User " + username + " left");
    }

    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {
        ObjectMapper mapper = new ObjectMapper();
        log.info("Received message: {} from {}", message, username);
        PlayerMessage parsedMessage;
        try {
            parsedMessage = mapper.readValue(message, PlayerMessage.class);
//            sendMessage(parsedMessage, sessions.getOrDefault(parsedMessage.getTo(), sessions.get("client")));
            handleMessage(username, parsedMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleMessage(String username, PlayerMessage message) {
        switch (message.getType()) {
            case MessageType.Fields.PLAY:
                // play
                notifyPlayer(message);
                break;
            case MessageType.Fields.PAUSE:
                // pause
                break;
            case MessageType.Fields.NEXT:
                // next
                break;
            case MessageType.Fields.GET_NOW_PLAYING:
                // get now playing
                handleGetNowPlaying(username);
                break;
            default:
                throw new IllegalArgumentException("Unknown message type: " + message.getType());
        }
    }

    private void handleGetNowPlaying(String username) {
    }

    private void notifyPlayer(PlayerMessage message) {
        if (!sessions.containsKey("client")) {
            log.error("Player client not online");
            return;
        }

        sendMessage(message, sessions.get("client"));
        nowPlayingResource.nowPlaying(message.getContent());
    }


    private void sendMessage(PlayerMessage message, Session client) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(message);
            client.getAsyncRemote().sendObject(json, result -> {
                if (result.getException() != null) {
                    log.error("Unable to send message: " + result.getException());
                } else {
                    log.info("Message sent successfully");
                }
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
