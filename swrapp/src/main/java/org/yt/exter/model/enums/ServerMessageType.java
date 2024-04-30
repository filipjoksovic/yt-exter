package org.yt.exter.model.enums;

import lombok.experimental.FieldNameConstants;

/**
 * Enum for the different types of messages that can come from the server.
 */
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum ServerMessageType {
    @FieldNameConstants.Include PLAY,
    @FieldNameConstants.Include PLAY_CONFIRMED;
}
