package org.example.services;

import jakarta.transaction.Transactional;
import org.example.entities.LikeCount;
import org.example.repositories.LikeCountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CounterService {
    private final LikeCountRepository repository;

    public CounterService(LikeCountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void increment(String contentId) {
        repository.increment(contentId);
    }

    public Optional<LikeCount> findByContentId(String contentId) {
        return repository.findById(contentId);
    }


}
