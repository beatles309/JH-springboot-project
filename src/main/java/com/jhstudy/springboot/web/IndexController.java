package com.jhstudy.springboot.web;

import com.jhstudy.springboot.domain.posts.Posts;
import com.jhstudy.springboot.service.PostsService;
import com.jhstudy.springboot.web.dto.PostsListResponseDto;
import com.jhstudy.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        List<PostsListResponseDto> postsList = postsService.findAllDesc();
        model.addAttribute("posts", postsList);
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto posts = postsService.findById(id);
        model.addAttribute("posts", posts);
        return "posts-update";
    }
}
