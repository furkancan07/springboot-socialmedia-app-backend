package com.rf.backend.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity // veri tabanını varlığı olduğunu belli eder
@Table(name = "KULLANICI")// hangi adla kaydedeceğini yazar
public class User {
    @NotNull(message = "Kullanici adi boş olamaz") // bean validation paketimizden gelir boş olmayacağı anlamına gelir
    @Size(min = 5,max = 18) // bean paketimizdeki size adı üzerinde
    @Column(unique = true) // username artık sadece bir ad olacak ama kullaniciya gostermek içiz zor bir yontem
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
    @Id
    @GeneratedValue
    private int id;

}
