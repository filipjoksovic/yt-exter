package org.yt.exter.model.enums;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum MessageType {
    @FieldNameConstants.Include PLAY,
    @FieldNameConstants.Include PAUSE,
    @FieldNameConstants.Include NEXT,
    @FieldNameConstants.Include GET_NOW_PLAYING;
}
