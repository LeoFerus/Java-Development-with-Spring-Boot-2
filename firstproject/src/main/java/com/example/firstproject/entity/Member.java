package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;   /// key 라고 전에 명명했었는데 @Id가 붙어 있어서 그런지 이름이 id가 아니면 못알아 먹는가보다.
    @Column
    private String info;
    @Column
    private String password;



}
