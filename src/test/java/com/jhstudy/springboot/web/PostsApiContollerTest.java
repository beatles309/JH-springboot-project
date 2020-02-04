package com.jhstudy.springboot.web;

import com.jhstudy.springboot.domain.posts.Posts;
import com.jhstudy.springboot.domain.posts.PostsRepository;
import com.jhstudy.springboot.web.dto.PostsSaveRequestDto;
import com.jhstudy.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiContollerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void savePostsTest() throws Exception {
        String title = "타이틀";
        String content = "내용";
        String author = "쥐냥쥐냥";
        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        String url ="http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, postsSaveRequestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void updatePostsTest() throws Exception {
        String title = "바뀐 타이틀";
        String content = "바뀐 내용";
        String author = "쥐냥쥐냥";
        Posts posts = postsRepository.save(Posts.builder()
                .title("타이틀")
                .content("내용")
                .author("쥐냥이")
                .build());

        String url ="http://localhost:" + port + "/api/v1/posts/" + posts.getId();

        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder().title(title).content(content).build();

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(postsUpdateRequestDto), Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }
}
