package org.yt.exter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.enterprise.context.Dependent;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.yt.exter.model.PlayerMessage;
import org.yt.exter.model.enums.ClientMessageType;
import org.yt.exter.model.enums.ServerMessageType;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Dependent
public class PlayerSocketHandler {

    private final static String WEB_CLIENT = "app";
    private final static String PLAYER = "client";

    public PlayerMessage parseMessageToObject(String message) {
        ObjectMapper mapper = new ObjectMapper();
        log.info("Trying to parse message: " + message);

        try {
            return mapper.readValue(message, PlayerMessage.class);
        } catch (Exception e) {
            log.error("Error parsing message: " + message, e);
            throw new RuntimeException("Error parsing message: " + message, e);
        }
    }

    public void handleMessage(Map<String, Session> sessions, String username, String message) {
        PlayerMessage playerMessage = parseMessageToObject(message);

        switch (playerMessage.getType()) {
            case ClientMessageType.Fields.PLAY -> {
                log.info("Received PLAY message from {}", username);
                handlePlayMessage(sessions, username, message);
            }
            case ClientMessageType.Fields.PLAY_CONFIRM -> {
                log.info("Received PLAY_CONFIRM message from {}", username);
                handlePlayConfirmMessage(sessions, username, message);
            }
            default -> log.error("Unknown message type: " + playerMessage.getType());

        }
    }

    void handlePlayMessage(Map<String, Session> sessions, String username, String message) {
        log.info("Handling play message from: " + username);
        PlayerMessage playerMessage = parseMessageToObject(message);

        notifyUser(sessions, PLAYER, playerMessage);
    }

    void handlePlayConfirmMessage(Map<String, Session> sessions, String username, String message) {
        log.info("Handling play confirm message from: " + username);
        PlayerMessage playerMessage = parseMessageToObject(message);

        PlayerMessage newMessage = new PlayerMessage();
        newMessage.setContent(playerMessage.getContent());
        newMessage.setFrom(PLAYER);
        newMessage.setTo(WEB_CLIENT);
        newMessage.setType(ServerMessageType.Fields.PLAY_CONFIRMED);


        notifyUser(sessions, WEB_CLIENT, newMessage);
    }

    void notifyUser(Map<String, Session> sessions, String username, PlayerMessage message) {
        log.info("Notifying user: " + username);
        if (!sessions.containsKey(username)) {
            log.error("User not found: " + username);
            return;
        }

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            sessions.get(username).getAsyncRemote().sendObject(ow.writeValueAsString(message));
        } catch (Exception e) {
            log.error("Error sending message to user: " + username, e);
        }
    }

}
