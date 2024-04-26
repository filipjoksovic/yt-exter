package org.yt.exter.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.yt.exter.client.SearchClient;
import org.yt.exter.api.SearchResource;
import org.yt.exter.dao.SearchResultDao;
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
        List<SearchResult> searchResults = searchClient.search(query);
        searchResults.forEach(searchResult -> {
            try {
                searchResultDao.save(searchResult);
            } catch (Exception e) {
                log.error("Error saving search result: {}", searchResult, e);
            }
        });

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
