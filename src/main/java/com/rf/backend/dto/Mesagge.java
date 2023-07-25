package com.rf.backend.dto;

import lombok.*;

@Data // otamtik tostring getter setteler oluşturur lombok
//@Getter getterleri get
//@Setter setterleri get
/*@NoArgsConstructor */ // constructouer yok eder
@AllArgsConstructor // OTAMATİK CONSTRUCTOR OLUŞTURUR
public class Mesagge {
    private  String  mesaj;
}
