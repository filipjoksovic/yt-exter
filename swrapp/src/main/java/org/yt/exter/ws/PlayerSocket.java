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
import org.yt.exter.PlayerSocketHandler;
import org.yt.exter.api.NowPlayingResource;
import org.yt.exter.model.PlayerMessage;
import org.yt.exter.model.enums.ServerMessageType;

import java.util.Map;

@ServerEndpoint("/player/{username}")
@ApplicationScoped
public class PlayerSocket {

    private static final Logger log = LoggerFactory.getLogger(PlayerSocket.class);
    Map<String, Session> sessions = new java.util.concurrent.ConcurrentHashMap<>();

    @Inject
    NowPlayingResource nowPlayingResource;

    @Inject
    PlayerSocketHandler playerSocketHandler;

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

        playerSocketHandler.handleMessage(sessions, username, message);
    }
}
