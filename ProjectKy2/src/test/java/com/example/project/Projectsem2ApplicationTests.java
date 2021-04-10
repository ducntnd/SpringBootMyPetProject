package com.example.project;

import com.example.project.model.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Projectsem2ApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		ObjectMapper objectMapper=new ObjectMapper();
		UserDto dto=new UserDto();
		dto.setEmail("ad@a");
		dto.setFullname("ffff");
		System.out.println(objectMapper.writeValueAsString(dto));
	}

}
