package org.yt.exter.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.yt.exter.model.ResourceDetails;
import org.yt.exter.model.SearchResult;

import java.util.List;

@Path("/")
@RegisterRestClient(baseUri = "http://localhost:8000")
@Produces(MediaType.APPLICATION_JSON)
public interface SearchClient {
    @GET
    @Path("/search")
    List<SearchResult> search(@QueryParam("term") String query);

    @GET
    @Path("/next")
    List<SearchResult> next(String query);

    @GET
    @Path("/details/{id}")
    ResourceDetails details(@PathParam("id") String id);
}

