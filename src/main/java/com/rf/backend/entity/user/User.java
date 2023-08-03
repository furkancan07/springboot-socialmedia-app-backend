package com.rf.backend.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.rf.backend.Views.Views;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name = "KULLANICI")
public class User {
    @NotNull(message = "Kullanici adi boş olamaz")
    @Column(unique = true)

    private String username;

    @NotNull(message = "İkinci ad boş olamaz")
    @Size(min = 5,max = 20)

    private String display;

    //@JsonIgnore // örneğin kullanıcıyı bilgilendirirken şifreyi umursama yani gönderme anlamına gelir
    @NotNull
    @Size(min = 4)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$" ,message = "Lütfen en az bir büyük harf,bir küçüj harf ve sayi kullanin")// password için kural

    private String sifre;
    private String tekrar;

    @Lob
    private String image;

    @Id
    @GeneratedValue

    private int id;

}
