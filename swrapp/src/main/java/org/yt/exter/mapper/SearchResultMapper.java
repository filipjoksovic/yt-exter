package org.yt.exter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.yt.exter.entity.SearchResultEntity;
import org.yt.exter.model.SearchResult;
import org.yt.exter.model.SearchResultBase;
import org.yt.exter.model.dto.SearchResultBaseDto;
import org.yt.exter.model.dto.SearchResultDto;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface SearchResultMapper {

    @Mapping(target = "id", source = "video_id")
    @Mapping(target = "title", source = "_title")
    @Mapping(target = "watchUrl", source = "watch_url")
    @Mapping(target = "embedUrl", source = "embed_url")
    @Mapping(target = "author", source = "_author")
    @Mapping(target = "length", source = "length")
    @Mapping(target = "thumbnailUrl", source = "thumbnail_url")
    SearchResultDto toDto(SearchResult searchResult);

    List<SearchResultDto> toDtos(List<SearchResult> searchResults);

    @Mapping(target = "videoId", source = "video_id")
    @Mapping(target = "title", source = "_title")
    @Mapping(target = "watchUrl", source = "watch_url")
    @Mapping(target = "embedUrl", source = "embed_url")
    @Mapping(target = "author", source = "_author")
    @Mapping(target = "length", source = "length")
    @Mapping(target = "thumbnailUrl", source = "thumbnail_url")
    SearchResultEntity toEntity(SearchResult searchResult);

    List<SearchResultDto> fromEntities(List<SearchResultEntity> searchResultEntity);

    SearchResultDto fromEntity(SearchResultEntity searchResultEntity);

    List<SearchResult> toResult(List<SearchResultEntity> searchResults);


    @Mapping(source = "video_id", target = "videoId")
    @Mapping(source = "_title", target = "title")
    @Mapping(source = "watch_url", target = "watchUrl")
    @Mapping(source = "embed_url", target = "embedUrl")
    SearchResultBaseDto toBaseDto(SearchResultBase searchResult);

    List<SearchResultBaseDto> toBaseDtos(List<SearchResultBase> searchResults);
}
