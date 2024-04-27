package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {

   // private String id;   /// ID가 아니고 key 라고 이름을 지었더니 자동으로 연결이 되지 않아서 오류가 떴다.

    private String info;
    private String password;



    public Member toEntity() {
        return new Member(null,info,password);
    }
}
