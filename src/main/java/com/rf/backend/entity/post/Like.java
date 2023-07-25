package com.rf.backend.entity.post;
import com.fasterxml.jackson.annotation.JsonView;
import com.rf.backend.Views.Views;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "PostLike")
@Entity

public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @JsonView(Views.Base.class)
    private int count=0;

    @ManyToOne
    Share share;
}
