package org.yt.exter.model.dto;

import lombok.Data;

@Data
public class SearchResultDto {
    private String id;
    private String title;
    private String videoId;
    private String watchUrl;
    private String thumbnailUrl;
    private String embedUrl;
    private String author;
    private String length;
}
