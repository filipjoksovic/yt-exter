package org.yt.exter.ws;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.util.Map;

@ServerEndpoint("/player/{username}")
@ApplicationScoped
public class PlayerSocket {

    Map<String, Session> sessions = new java.util.concurrent.ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        broadcast("New user connected: " + session.getId());
        sessions.put(session.getId(), session);
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
        broadcast("User " + username + " left on error: " + throwable);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {
        broadcast(message);
    }
}
