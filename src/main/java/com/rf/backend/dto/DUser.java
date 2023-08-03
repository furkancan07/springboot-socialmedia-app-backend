package com.rf.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DUser {
    private Long id;
    @Column(unique = true)
    private String username;
    @Lob
    private String image;
}
