package com.mymicroservice.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mymicroservice.demo.domain.JobPosition;
import com.mymicroservice.demo.repository.JobPositionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceTest {

	@MockBean
	private JobPositionRepository repository;
	
	@Autowired
	private JobService jobService;
	
	@Test
	public void shouldRetrieveJobPosition() {
		
		JobPosition jobExpected = new JobPosition();
		jobExpected.setCompany("Company Mock");
		jobExpected.setCompanyUrl("www.somenicecompany.com");
		jobExpected.setType("Java");
		jobExpected.setTitle("Senior Software Engineer");
		jobExpected.setDescription("Java Senior Developer familiar with spring framework");
		
		given(repository.save(any(JobPosition.class))).willReturn(jobExpected);
		
		JobPosition saved = jobService.createPosition(jobExpected);
		
		assertNotNull(saved);
		assertThat(saved.getCompany()).isEqualTo("Company Mock");
		assertThat(saved.getCompanyUrl()).isEqualTo("www.somenicecompany.com");
		assertThat(saved.getType()).isEqualTo("Java");
		assertThat(saved.getTitle()).isEqualTo("Senior Software Engineer");
		assertThat(saved.getDescription()).isEqualTo("Java Senior Developer familiar with spring framework");
		
	}
	
}
