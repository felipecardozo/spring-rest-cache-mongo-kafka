package com.mymicroservice.demo.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymicroservice.demo.domain.JobPosition;
import com.mymicroservice.demo.service.JobService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private JobService jobService;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void shouldCreateAJobPosition() throws JsonProcessingException, Exception {
		
		JobPosition jobExpected = new JobPosition();
		jobExpected.setCompany("Company Mock");
		jobExpected.setCompanyUrl("www.somenicecompany.com");
		jobExpected.setType("Java");
		jobExpected.setTitle("Senior Software Engineer");
		jobExpected.setDescription("Java Senior Developer familiar with spring framework");
		
        when(jobService.createPosition(any(JobPosition.class))).thenReturn(jobExpected);

        mockMvc.perform(post("/add/job")
                .content(objectMapper.writeValueAsString(jobExpected))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company", is("Company Mock")))
                .andExpect(jsonPath("$.type", is("Java")))
                .andExpect(jsonPath("$.companyUrl", is("www.somenicecompany.com")))
                .andExpect(jsonPath("$.description", is("Java Senior Developer familiar with spring framework")))
                .andExpect(jsonPath("$.title", is("Senior Software Engineer")));

        verify(jobService, times(1)).createPosition(any(JobPosition.class));
		
		
	}
	
    

}
