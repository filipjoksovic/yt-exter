package org.yt.exter.model;

import lombok.Data;

@Data
public class SearchResult {
    private Object _js;
    private Object _js_url;
    private Object _vid_info;
    private Object _watch_html;
    private Object _embed_html;
    private Object _player_config_args;
    private Object _age_restricted;
    private Object _fmt_streams;
    private Object _initial_data;
    private Object _metadata;
    private String video_id;
    private String watch_url;
    private String embed_url;
    private String thumbnail_url;
    private StreamMonostate stream_monostate;
    private String _author;
    private String _title;
    private Object _publish_date;
    private boolean use_oauth;
    private boolean allow_oauth_cache;
    private String length;


    @Data
    class StreamMonostate {
        private Object on_progress;
        private Object on_complete;
        private Object title;
        private Object duration;
    }
}
