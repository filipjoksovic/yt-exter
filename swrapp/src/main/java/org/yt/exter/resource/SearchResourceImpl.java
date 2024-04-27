package org.yt.exter.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.yt.exter.client.SearchClient;
import org.yt.exter.api.SearchResource;
import org.yt.exter.dao.SearchResultDao;
import org.yt.exter.entity.SearchResultEntity;
import org.yt.exter.mapper.SearchResultToSearchResultDtoMapper;
import org.yt.exter.model.ResourceDetails;
import org.yt.exter.model.SearchResult;
import org.yt.exter.model.dto.SearchResultDto;

import java.util.List;

@Path("/")
@Slf4j
public class SearchResourceImpl implements SearchResource {

    @Inject
    @RestClient
    SearchClient searchClient;

    @Inject
    SearchResultToSearchResultDtoMapper searchResultToSearchResultDtoMapper;

    @Inject
    SearchResultDao searchResultDao;

    @Override
    public List<SearchResultDto> search(String query) {
        log.info("Searching for: {}", query);
//        List<SearchResultDto> resultsFromDb = searchResultDao.findByTitle(query);
        List<SearchResult> searchResults = searchClient.search(query);
        searchResultDao.saveAllAsync(searchResults);
        log.info("Search results: {}", searchResults);
        return searchResultToSearchResultDtoMapper.toDtos(searchClient.search(query));
    }

    @Override
    public List<SearchResult> next(String query) {
        return List.of();
    }

    @Override
    public ResourceDetails details(String id) {
        return null;
    }
}
