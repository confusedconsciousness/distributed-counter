package org.example.controllers;

import org.example.entities.LikeCount;
import org.example.models.LikeResponse;
import org.example.services.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final CounterService counterService;

    public LikeController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/{contentId}")
    public LikeResponse getCount(@PathVariable("contentId") String contentId) {
        Optional<LikeCount> likeCount = counterService.findByContentId(contentId);
        long count = likeCount.map(LikeCount::getCount).orElse(0L);
        return new LikeResponse(contentId, count);
    }
}
