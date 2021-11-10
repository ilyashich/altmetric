package com.example.api.webclient.youtube;

import com.example.api.model.youtube.YoutubeDto;
import com.example.api.model.youtube.YoutubeItemDto;
import com.example.api.webclient.youtube.dto.YoutubeItemsDto;
import com.example.api.webclient.youtube.dto.YoutubeSearchDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class YoutubeClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String YOUTUBE_API_URL = "https://youtube.googleapis.com/youtube/v3/";
    private static final String API_KEY = "XXXXXXX";

    public YoutubeDto searchYoutube(String searchQuery)
    {
        YoutubeSearchDto youtubeSearch = callGetMethod(
                "search?part=snippet&maxResults=500&q={search query}&type=video&key={YOUR_API_KEY}", YoutubeSearchDto.class,
                searchQuery, API_KEY);

        List<YoutubeItemDto> items = new ArrayList<>();
        if(youtubeSearch.getItems() != null)
        {
            for(YoutubeItemsDto item : youtubeSearch.getItems())
            {
                items.add(YoutubeItemDto.builder()
                        .videoId(item.getId().getVideoId())
                        .publishedAt(item.getSnippet().getPublishedAt())
                        .channelTitle(item.getSnippet().getChannelTitle())
                        .title(item.getSnippet().getTitle())
                        .description(item.getSnippet().getDescription())
                        .thumbnailUrl(item.getSnippet().getThumbnails().getHigh().getUrl())
                        .build());
            }
        }
        return YoutubeDto.builder()
                .nextPageToken(youtubeSearch.getNextPageToken())
                .totalResults(youtubeSearch.getPageInfo().getTotalResults())
                .items(items)
                .build();

    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object...objects)
    {
        return restTemplate.getForObject(YOUTUBE_API_URL + url, responseType, objects);
    }
}
