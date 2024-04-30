package org.yt.exter.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.yt.exter.api.NowPlayingResource;
import org.yt.exter.api.SearchResource;
import org.yt.exter.model.NowPlayingDetails;
import org.yt.exter.model.dto.NowPlayingDto;

@ApplicationScoped
@Path("/now-playing")
public class NowPlayingResourceImpl implements NowPlayingResource {

    NowPlayingDetails nowPlayingDetails;
    String videoId;

    @Inject
    SearchResource searchResource;

    @Override
    public NowPlayingDto nowPlaying() {
        return NowPlayingDto.builder().content(videoId).build();
    }

    @Override
    public void nowPlaying(String videoId) {
        this.videoId = videoId;
    }
}
