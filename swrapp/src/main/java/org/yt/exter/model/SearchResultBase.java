package org.yt.exter.model;

import jakarta.annotation.security.DenyAll;
import lombok.Data;

@Data
public class SearchResultBase {
    private String video_id;
    private String _title;
    private String watch_url;
    private String embed_url;
}
