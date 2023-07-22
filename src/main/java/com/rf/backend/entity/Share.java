package com.rf.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Size(min = 2,max = 18)
    private String title;
    private String image;
    @NotNull
    @Size(min = 4)
    private String description;



}
