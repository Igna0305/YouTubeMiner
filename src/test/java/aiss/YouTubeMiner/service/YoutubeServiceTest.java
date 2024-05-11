package aiss.YouTubeMiner.service;

import aiss.YouTubeMiner.model.youtube.caption.CaptionSearch;
import aiss.YouTubeMiner.model.youtube.channel.ChannelSearch;
import aiss.YouTubeMiner.model.youtube.comment.CommentSearch;
import aiss.YouTubeMiner.model.youtube.videoSnippet.VideoSnippetSearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class YoutubeServiceTest {
    @Autowired
    YouTubeService service;

    @Test
    @DisplayName("Get channels with id")
    void getChannelsWithId() {
        String token = "AIzaSyC1Q7p1wV3YzryZZnFjp7Nr_cypZ2oLwDg";
        String part = "snippet,contentDetails,statistics";
        String id = "UCnQC_XGCCI__qrxwgZS27-A";
        ChannelSearch channel = service.getChannelWithId(token,part,id);
        assertNotNull(channel,"The channel is null");
        System.out.println(channel);
    }

    @Test
    @DisplayName("Get videos")
    void getVideos() {
        String token = "AIzaSyC1Q7p1wV3YzryZZnFjp7Nr_cypZ2oLwDg";
        String part = "snippet";
        String channelId = "UCnQC_XGCCI__qrxwgZS27-A";
        String maxResults = "25";
        VideoSnippetSearch video = service.getVideos(token,part,channelId,maxResults);
        assertNotNull(video,"The video is null");
        System.out.println(video);
    }

    @Test
    @DisplayName("Get captions")
    void getCaptions() {
        String token = "AIzaSyC1Q7p1wV3YzryZZnFjp7Nr_cypZ2oLwDg";
        String part = "snippet";
        String playListId = "UgzDE2tasfmrYLyNkGt4AaABAg";
        CaptionSearch caption = service.getCaptions(token,part,playListId);
        assertNotNull(caption,"The caption is null");
        System.out.println(caption);
    }

    @Test
    @DisplayName("Get comments")
    void getComments() {
        String token = "AIzaSyC1Q7p1wV3YzryZZnFjp7Nr_cypZ2oLwDg";
        String part = "snippet";
        String playListId = "UgzDE2tasfmrYLyNkGt4AaABAg";
        CommentSearch comment = service.getComments(token,part,playListId,"3");
        assertNotNull(comment,"The comment is null");
        System.out.println(comment);
    }

}
