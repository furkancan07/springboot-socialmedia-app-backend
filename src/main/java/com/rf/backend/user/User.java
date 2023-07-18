package com.rf.backend.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity // veri tabanına kaydeder
@Table(name = "KULLANICI")// hangi adla kaydedeceğini yazar
public class User {
private String username,display,sifre;
    @Id
    @GeneratedValue
    private int id;



}
