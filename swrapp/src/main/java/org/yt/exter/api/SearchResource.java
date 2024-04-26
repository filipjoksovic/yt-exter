package org.yt.exter.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.yt.exter.model.ResourceDetails;
import org.yt.exter.model.SearchResult;
import org.yt.exter.model.dto.SearchResultDto;

import java.util.List;


public interface SearchResource {
    @GET
    @Path("/search")
    List<SearchResultDto> search(@QueryParam("term") String query);

    @GET
    @Path("/next")
    List<SearchResult> next(String query);

    @GET
    @Path("/details/{id}")
    ResourceDetails details(@PathParam("id") String id);

}