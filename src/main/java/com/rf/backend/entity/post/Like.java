package com.rf.backend.entity.post;

import com.rf.backend.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Table(name = "PostLike")
@Entity

public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int count=0;

    @ManyToOne
    Share share;
}
