package org.yt.exter.model;

import lombok.Data;

@Data
public class PlayerMessage {
    private String type;
    private String content;
    private String from;
    private String to;
}
