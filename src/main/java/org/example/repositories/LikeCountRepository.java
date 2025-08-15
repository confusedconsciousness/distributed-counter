package org.example.repositories;

import jakarta.transaction.Transactional;
import org.example.entities.LikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikeCountRepository extends JpaRepository<LikeCount, String> {

    @Transactional
    @Modifying
    @Query(value = """
                        INSERT INTO like_counts (content_id, count)
                        values (?1, 1)
                        ON DUPLICATE KEY UPDATE count = count + 1
            """, nativeQuery = true)
    void increment(String contentId);
}
