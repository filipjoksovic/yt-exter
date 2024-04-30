package org.yt.exter.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.yt.exter.model.dto.NowPlayingDto;

public interface NowPlayingResource {

    @GET
    @Path("/now-playing")
    NowPlayingDto nowPlaying();

    @POST
    @Path("/now-playing")
    void nowPlaying(String nowPlayingDetails);
}
