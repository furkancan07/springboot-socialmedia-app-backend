package com.rf.backend.entity.post;

import com.fasterxml.jackson.annotation.JsonView;
import com.rf.backend.Views.Views;
import com.rf.backend.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Data
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotNull(message = "Boş olamaz")
    @Size(min = 2,max = 250)

    private String title;

    private String image;
    @NotNull
    @Size(min = 4)

    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
