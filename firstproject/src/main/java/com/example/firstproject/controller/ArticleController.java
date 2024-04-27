package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //System.out.println(form.toString());
        log.info(form.toString());;

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString()); // DTO가 엔티티로 잘 변환되는지 확인 출력
        log.info(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);  // article이 db에 잘 저장되는지 확인출력
       // System.out.println(saved.toString());
        log.info(saved.toString());
        return"redirect:/articles/"+ saved.getId();
    }

    @GetMapping("articles/{id}")
    public String show(@PathVariable Long id , Model model){
        log.info("id = " + id); // 잘받았는지 확인하는 로그 찍기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity); ///model.addAttribute(String name, Object value); 이런형식이다.

        return "articles/show"; //목록으로 돌아가기 링크를 넣을 뷰 파일 확인

    }

    @GetMapping("/articles")
    public String index(Model model){  /// model 객체 받아 오기

        //1.모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //2.모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList); // articleEntityList등록
        //3.뷰 페이지 설정하기
        return"articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        //수정 할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터를 등록하기
        model.addAttribute("article",articleEntity);  //articleEntity를 article로 등록
        //뷰 페이지 설정하기
        return "articles/edit";
    }
}
