package com.pakskiy.news.service;

import com.pakskiy.news.dto.NewsRequestDto;
import com.pakskiy.news.dto.NewsResponseDto;
import com.pakskiy.news.exception.RecordDeleteException;
import com.pakskiy.news.exception.RecordNotFoundException;
import com.pakskiy.news.exception.RecordSaveException;
import com.pakskiy.news.exception.RecordUpdateException;
import com.pakskiy.news.model.NewsEntity;
import com.pakskiy.news.model.NewsStatus;
import com.pakskiy.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    public List<NewsResponseDto> getListNews() {
        try {
            List<NewsEntity> entityList = newsRepository.findAll();
            return entityList.parallelStream()
                    .map(e -> NewsResponseDto.builder()
                            .id(e.getId())
                            .name(e.getName())
                            .description(e.getDescription())
                            .newsStatus(e.getNewsStatus().name())
                            .build())
                    .toList();
        }catch (Exception e){
            log.error("List news filed");
            return Collections.emptyList();
        }

    }
    public void saveNews(NewsRequestDto request) {
        try {
            NewsEntity news = new NewsEntity();
            news.setName(request.getName());
            news.setDescription(request.getDescription());
            news.setBody(request.getBody());
            news.setImagePath(request.getImagePath());
            news.setNewsStatus(NewsStatus.ACTIVE);
            news.setUpdatedAt(LocalDateTime.now());
            newsRepository.save(news);
        }catch (Exception e){
            log.error("Save news filed");
            throw new RecordSaveException("Error save news");
        }
    }
    public NewsResponseDto getNewsById(Long newsId) {
        NewsEntity news = newsRepository.findById(newsId).orElseThrow(() -> new RecordNotFoundException("News not found"));
        return NewsResponseDto.builder()
                .id(news.getId())
                .name(news.getName())
                .description(news.getDescription())
                .body(news.getBody())
                .imagePath(news.getImagePath())
                .newsStatus(news.getNewsStatus().name())
                .build();
    }
    public void updateNews(NewsRequestDto request) {
        try {
            NewsEntity news = new NewsEntity();
            news.setName(request.getName());
            news.setBody(request.getBody());
            news.setDescription(request.getDescription());
            news.setImagePath(request.getImagePath());
            news.setUpdatedAt(LocalDateTime.now());
            newsRepository.save(news);
        }catch (Exception e){
            log.error("Update news filed");
            throw new RecordUpdateException("Error update news");
        }
    }

    public void deleteNewsById(Long newsId) {
        try {
            NewsEntity news = newsRepository.findById(newsId).orElseThrow(() -> new RecordNotFoundException("News not found"));
            newsRepository.delete(news);
            log.info("News {} deleted", newsId);
        }catch (Exception e){
            log.error("Delete news filed");
            throw new RecordDeleteException("Error delete news");
        }
    }
}
