package com.jhstudy.springboot.web.web.dto;

import com.jhstudy.springboot.web.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

public class HelloResponseDtoTest {

    @Test
    public void lombokTest() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
