package com.example.api.webclient;

import com.example.api.model.youtube.Youtube;
import com.example.api.model.youtube.YoutubeItem;
import com.example.api.dto.youtube.YoutubeItemsDto;
import com.example.api.dto.youtube.YoutubeSearchDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class YoutubeClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/";
    private static final String API_KEY = "XXXXXXX";

    public Youtube searchYoutubeByUrl(String url)
    {
        YoutubeSearchDto youtubeSearch = callGetMethod(
                "search?part=snippet&maxResults=500&q={search query}&type=video&key={YOUR_API_KEY}", YoutubeSearchDto.class,
                url, API_KEY);

        return getYoutube(url, youtubeSearch);

    }

    public Youtube searchYoutubeByTitle(String title)
    {
        YoutubeSearchDto youtubeSearch = callGetMethod(
                "search?part=snippet&maxResults=500&q=\"{search query}\"&type=video&key={YOUR_API_KEY}", YoutubeSearchDto.class,
                title, API_KEY);

        return getYoutube(youtubeSearch);

    }

    private Youtube getYoutube(YoutubeSearchDto youtubeSearch)
    {
        List<YoutubeItem> items = new ArrayList<>();
        if(youtubeSearch.getItems() != null)
        {
            for(YoutubeItemsDto item : youtubeSearch.getItems())
            {
                items.add(YoutubeItem.builder()
                        .videoId(item.getId().getVideoId())
                        .publishedAt(item.getSnippet().getPublishedAt())
                        .channelTitle(item.getSnippet().getChannelTitle())
                        .title(item.getSnippet().getTitle())
                        .description(item.getSnippet().getDescription())
                        .thumbnailUrl(item.getSnippet().getThumbnails().getHigh().getUrl())
                        .build());
            }
        }
        return Youtube.builder()
                .nextPageToken(youtubeSearch.getNextPageToken())
                .totalResults(items.size())
                .items(items)
                .build();
    }

    private Youtube getYoutube(String query, YoutubeSearchDto youtubeSearch)
    {
        List<YoutubeItem> items = new ArrayList<>();
        if(youtubeSearch.getItems() != null)
        {
            for(YoutubeItemsDto item : youtubeSearch.getItems())
            {
                String video = callGetMethod(
                        "videos?id={video id}&part=snippet&key={YOUR_API_KEY}", String.class,
                        item.getId().getVideoId(), API_KEY);
                if(video.contains(query))
                {
                    items.add(YoutubeItem.builder()
                            .videoId(item.getId().getVideoId())
                            .publishedAt(item.getSnippet().getPublishedAt())
                            .channelTitle(item.getSnippet().getChannelTitle())
                            .title(item.getSnippet().getTitle())
                            .description(item.getSnippet().getDescription())
                            .thumbnailUrl(item.getSnippet().getThumbnails().getHigh().getUrl())
                            .build());
                }
            }
        }
        return Youtube.builder()
                .nextPageToken(youtubeSearch.getNextPageToken())
                .totalResults(items.size())
                .items(items)
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object...objects)
    {
        return restTemplate.getForObject(YOUTUBE_API_URL + url, responseType, objects);
    }
}
