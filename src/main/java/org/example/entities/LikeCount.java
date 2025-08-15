package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "like_counts")
public class LikeCount {
    @Id
    @Column(name = "content_id", nullable = false, updatable = false)
    private String contentId;

    @Setter
    @Column(name = "count", nullable = false)
    private long count;

}
