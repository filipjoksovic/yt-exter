package org.yt.exter.model;

import lombok.Data;

import java.util.List;

@Data
public class Thumbnail {
    private List<ThumbnailDetail> thumbnails;
}
