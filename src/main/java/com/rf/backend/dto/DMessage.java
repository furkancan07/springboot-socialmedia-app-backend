package com.rf.backend.dto;


import lombok.Data;

import java.util.Date;

@Data
public class DMessage {
    private Long id;
    private String content;
    private DUser sender;
    private DUser receiver;
    private Date date= new Date();
}
