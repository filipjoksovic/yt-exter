package org.yt.exter.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.yt.exter.client.SearchClient;
import org.yt.exter.api.SearchResource;
import org.yt.exter.dao.SearchResultDao;
import org.yt.exter.mapper.SearchResultMapper;
import org.yt.exter.model.ResourceDetails;
import org.yt.exter.model.SearchResult;
import org.yt.exter.model.VideoDetails;
import org.yt.exter.model.VideoDetailsRequest;
import org.yt.exter.model.dto.SearchResultBaseDto;
import org.yt.exter.model.dto.SearchResultDto;

import java.util.List;

@Path("/")
@Slf4j
public class SearchResourceImpl implements SearchResource {

    @Inject
    @RestClient
    SearchClient searchClient;

    @Inject
    SearchResultMapper searchResultMapper;

    @Inject
    SearchResultDao searchResultDao;

    @Override
    public List<SearchResultDto> search(String query) {
        log.info("Searching for: {}", query);
//        List<SearchResultDto> resultsFromDb = searchResultDao.findByTitle(query);
        List<SearchResult> searchResults = searchClient.search(query);
        searchResultDao.saveAllAsync(searchResults);
        log.info("Search results: {}", searchResults);
        return searchResultMapper.toDtos(searchClient.search(query));
    }

    @Override
    public List<SearchResultBaseDto> searchMinified(String query) {
        return searchResultMapper.toBaseDtos(searchClient.searchMinified(query));
    }

    @Override
    public List<SearchResult> next(String query) {
        return List.of();
    }

    //TODO convert to DTO at some point
    @Override
    public VideoDetails details(VideoDetailsRequest request) {
        return searchClient.details(request);
    }
}
