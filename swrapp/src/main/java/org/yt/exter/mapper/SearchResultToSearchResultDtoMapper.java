package org.yt.exter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.yt.exter.entity.SearchResultEntity;
import org.yt.exter.model.SearchResult;
import org.yt.exter.model.dto.SearchResultDto;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface SearchResultToSearchResultDtoMapper {

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
}
