package org.yt.exter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "search_results")
@Data
public class SearchResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "video_id", unique = true)
    private String videoId;

    @Column(name = "title")
    private String title;

    @Column(name = "watch_url")
    private String watchUrl;

    @Column(name = "embed_url")
    private String embedUrl;

    @Column(name = "author")
    private String author;

    @Column(name = "length")
    private String length;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

}
