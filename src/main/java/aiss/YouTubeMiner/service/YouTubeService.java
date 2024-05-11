package aiss.YouTubeMiner.service;

import aiss.YouTubeMiner.model.youtube.caption.CaptionSearch;
import aiss.YouTubeMiner.model.youtube.channel.ChannelSearch;
import aiss.YouTubeMiner.model.youtube.comment.CommentSearch;
import aiss.YouTubeMiner.model.youtube.videoSnippet.VideoSnippetSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class YouTubeService {

    @Autowired
    RestTemplate restTemplate;

    public ChannelSearch getChannelWithId(String token,String part, String id) {
        String uri = String.format("https://www.googleapis.com/youtube/v3/channels?key=%s&part=%s&id=%s",token,part,id);
        ResponseEntity<ChannelSearch> entity = restTemplate.getForEntity(uri, ChannelSearch.class);
        ChannelSearch res = entity.getBody();
        assert res != null;
        res.getItems().forEach(channel -> channel.setVideos(getVideos(token, "snippet", channel.getId(), "2").getItems()));
        return res;
    }

    public VideoSnippetSearch getVideos(String token, String part, String channelId, String maxResults) {
        String uri = String.format("https://youtube.googleapis.com/youtube/v3/search?part=%s&maxResults=%s&q=fun&type=video&key=%s&channelId=%s", part.replace("%2C", ","), maxResults,token, channelId);
        ResponseEntity<VideoSnippetSearch> entity = restTemplate.exchange(uri, HttpMethod.GET,null, VideoSnippetSearch.class);
        VideoSnippetSearch res = entity.getBody();
        assert res != null;
        res.getItems().forEach(video -> {
            video.setCaptions(getCaptions(token, "snippet", video.getId().getVideoId()).getItems());
            video.setComments(getComments(token, "snippet,replies", video.getId().getVideoId(),"3").getItems());
        });
        return res;
    }

    public CaptionSearch getCaptions(String token, String part, String videoId) {
        String uri = String.format("https://www.googleapis.com/youtube/v3/captions?key=%s&part=%s&videoId=%s", token, part, videoId);
        ResponseEntity<CaptionSearch> entity = restTemplate.getForEntity(uri, CaptionSearch.class);
        return entity.getBody();
    }

    public CommentSearch getComments(String token, String part, String videoId, String maxResults) {
        String uri = String.format("https://www.googleapis.com/youtube/v3/commentThreads?key=%s&part=%s&videoId=%s&maxResults=%s", token, part, videoId, maxResults);
        CommentSearch res = new CommentSearch();
        try{
            ResponseEntity<CommentSearch> entity = restTemplate.exchange(uri,HttpMethod.GET,null, CommentSearch.class);
            if(entity.getBody().getItems()!= null){
                res = entity.getBody();
            }
        }catch (HttpClientErrorException.Forbidden e){
            if(e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
        return res;
    }


}
