package org.yt.exter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class VideoDetails {

    private String videoId;
    private String title;
    private String lengthSeconds;
    private String channelId;
    private boolean isOwnerViewing;
    private boolean isCrawlable;
    private Thumbnail thumbnail;
    private boolean allowRatings;
    private String viewCount;
    private String author;
    private boolean isLowLatencyLiveStream;
    private boolean isPrivate;
    private boolean isUnpluggedCorpus;
    private String latencyClass;
    private String musicVideoType;
    private boolean isLiveContent;
}
