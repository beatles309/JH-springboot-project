package com.jhstudy.springboot.service;

import com.jhstudy.springboot.domain.posts.Posts;
import com.jhstudy.springboot.domain.posts.PostsRepository;
import com.jhstudy.springboot.web.dto.PostsResponseDto;
import com.jhstudy.springboot.web.dto.PostsSaveRequestDto;
import com.jhstudy.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다. id=" + id));
        posts.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다. id=" + id));
        return new PostsResponseDto(posts);
    }
}
