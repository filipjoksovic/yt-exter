package org.yt.exter.dao;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.yt.exter.entity.SearchResultEntity;
import org.yt.exter.mapper.SearchResultToSearchResultDtoMapper;
import org.yt.exter.model.SearchResult;
import org.yt.exter.model.dto.SearchResultDto;

import java.util.List;

@Slf4j
@RequestScoped
public class SearchResultDao {
    @Inject
    EntityManager entityManager;

    @Inject
    SearchResultToSearchResultDtoMapper searchResultToSearchResultDtoMapper;

    @Transactional
    public SearchResultEntity save(SearchResult searchResult) {
        log.info("Saving search result: {}", searchResult);
        try {
            SearchResultEntity searchResultEntity = searchResultToSearchResultDtoMapper.toEntity(searchResult);
            entityManager.persist(searchResultEntity);
            log.info("Search result saved: {}", searchResult);
            return searchResultEntity;
        } catch (Exception e) {
            log.error("Error saving search result: {}", searchResult, e);
        }
        return null;
    }

    @Transactional
    public void saveAsync(SearchResult searchResult) {
        log.info("Saving search result: {}", searchResult);
        Uni<SearchResultEntity> saved = Uni.createFrom().item(() -> save(searchResult));
        saved.subscribe().with(
                result -> log.info("Search result {} saved", searchResult.getVideo_id()),
                failure -> log.error("Error saving search result: {}", searchResult.getVideo_id(), failure));
    }

    @Transactional
    public List<SearchResultEntity> saveAll(List<SearchResult> searchResults) {

        return searchResults.stream().map(this::save).toList();
    }

    public void saveAllAsync(List<SearchResult> searchResults) {
        Uni.createFrom().item(() -> saveAll(searchResults)).subscribe().with(
                result -> log.info("Search results saved"),
                failure -> log.error("Error saving search results", failure));
    }

    public List<SearchResultEntity> findAll() {
        return entityManager.createQuery("SELECT s FROM SearchResultEntity s", SearchResultEntity.class).getResultList();
    }

    public SearchResultEntity findById(String id) {
        return entityManager.find(SearchResultEntity.class, id);
    }

    public List<SearchResultDto> findByTitle(String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<SearchResultEntity> cq = cb.createQuery(SearchResultEntity.class);
        Root<SearchResultEntity> from = cq.from(SearchResultEntity.class);

        cq.where(cb.like(from.get("title"), title));
        TypedQuery<SearchResultEntity> q = entityManager.createQuery(cq);
        List<SearchResultEntity> allitems = q.getResultList();

        return searchResultToSearchResultDtoMapper.fromEntities(allitems);
    }
}
