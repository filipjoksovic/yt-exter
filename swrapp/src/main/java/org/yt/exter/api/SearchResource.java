package org.yt.exter.api;

import jakarta.ws.rs.*;
import org.yt.exter.model.SearchResult;
import org.yt.exter.model.VideoDetails;
import org.yt.exter.model.VideoDetailsRequest;
import org.yt.exter.model.dto.SearchResultBaseDto;
import org.yt.exter.model.dto.SearchResultDto;

import java.util.List;


public interface SearchResource {
    @GET
    @Path("/search")
    List<SearchResultDto> search(@QueryParam("term") String query);

    @GET
    @Path("/search/minified")
    List<SearchResultBaseDto> searchMinified(@QueryParam("term") String query);

    @GET
    @Path("/next")
    List<SearchResult> next(String query);

    @POST
    @Path("/details")
    VideoDetails details(VideoDetailsRequest request);


}