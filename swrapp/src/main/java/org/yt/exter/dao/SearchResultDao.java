package org.yt.exter.dao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.yt.exter.entity.SearchResultEntity;
import org.yt.exter.mapper.SearchResultToSearchResultDtoMapper;
import org.yt.exter.model.SearchResult;

import java.util.List;

@Slf4j
@RequestScoped
public class SearchResultDao {
    @Inject
    EntityManager entityManager;

    @Inject
    SearchResultToSearchResultDtoMapper searchResultToSearchResultDtoMapper;

    @Transactional
    public void save(SearchResult searchResult) {
        log.info("Saving search result: {}", searchResult);
        try {
            SearchResultEntity searchResultEntity = searchResultToSearchResultDtoMapper.toEntity(searchResult);
            entityManager.persist(searchResultEntity);
            log.info("Search result saved: {}", searchResult);
        } catch (Exception e) {
            log.error("Error saving search result: {}", searchResult, e);
        }
    }
}
