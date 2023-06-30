package com.pakskiy.news.controller;

import com.pakskiy.news.dto.NewsRequestDto;
import com.pakskiy.news.dto.NewsResponseDto;
import com.pakskiy.news.model.NewsEntity;
import com.pakskiy.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private NewsService newsService;
    @GetMapping("/list")
    public List<NewsResponseDto> listNews() {
        return newsService.getListNews();
    }

    @PostMapping("/save")
    public ResponseEntity saveNews(@RequestBody NewsRequestDto request) {
        newsService.saveNews(request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/items/{newsId}")
    public ResponseEntity<NewsResponseDto> editNews(@PathVariable Long newsId) {
        return ResponseEntity.ok(newsService.getNewsById(newsId));
    }

    @PutMapping("/items/{newsId}")
    public ResponseEntity updateNews(@PathVariable Long newsId, @RequestBody NewsRequestDto request) {
        newsService.updateNews(request);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/items/{newsId}")
    public ResponseEntity<NewsEntity> deleteNews(@PathVariable Long newsId) {
        newsService.deleteNewsById(newsId);
        return ResponseEntity.ok().build();
    }
}