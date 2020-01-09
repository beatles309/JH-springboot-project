package com.jhstudy.springboot.web;

import com.jhstudy.springboot.service.PostsService;
import com.jhstudy.springboot.web.dto.PostsResponseDto;
import com.jhstudy.springboot.web.dto.PostsSaveRequestDto;
import com.jhstudy.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id,
                       @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
        return postsService.update(id, postsUpdateRequestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto update(@PathVariable("id") Long id) {
        return postsService.findById(id);
    }
}
