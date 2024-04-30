package org.yt.exter.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.jboss.logging.annotations.Field;

@Data
@FieldNameConstants(onlyExplicitlyIncluded = true)
public class PlayerMessage {
    @FieldNameConstants.Include
    private String type;
    @FieldNameConstants.Include
    private String content;
    @FieldNameConstants.Include
    private String from;
    @FieldNameConstants.Include
    private String to;
}
