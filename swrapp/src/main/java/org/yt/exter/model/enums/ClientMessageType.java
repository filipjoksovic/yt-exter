package org.yt.exter.model.enums;

import lombok.experimental.FieldNameConstants;

/**
 * Enum for the different types of messages that can come from the client.
 */
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum ClientMessageType {
    @FieldNameConstants.Include PLAY,
    @FieldNameConstants.Include PLAY_CONFIRM,

}
