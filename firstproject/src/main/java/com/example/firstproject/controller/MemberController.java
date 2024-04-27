package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;      /// articleRepository 객체 선언
        @GetMapping("/signup")
        public String newMemberForm(){
            return "members/new";   /// 이거 나중에 다시 확인해보셈
        }
        @PostMapping("/members/create")
        public String createMember(MemberForm form){
            System.out.println(form.toString());

            //1. DTO를 엔티티로 변환
            Member member = form.toEntity();
            System.out.println(member.toString());
            //2. 리파지터리로 엔티티를 db에 저장

            Member saved = memberRepository.save(member); /// member 에티티를 저장해 saved 객체에 반환
            System.out.println(saved.toString());
            return"redirect:/members/" + saved.getId();
        }

        @GetMapping("members/{id}")
    public String show(@PathVariable Long id, Model model){
            log.info("id = " + id);
            Member member = memberRepository.findById(id).orElse(null);
            model.addAttribute("member",member);

            return"members/show";
        }

        @GetMapping("/members")
        public String index(Model model){

            //1.모든 데이터 가져오기
            ArrayList<Member> memberEntityList = memberRepository.findAll();
            //2.모델에 데이터 등록하기
            model.addAttribute("memberList",memberEntityList);
            //3.뷰 페이지 설정하기

            return"members/index";
        }
}
