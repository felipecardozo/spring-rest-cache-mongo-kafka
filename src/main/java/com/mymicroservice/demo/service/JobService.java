package com.mymicroservice.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymicroservice.demo.domain.JobPosition;
import com.mymicroservice.demo.repository.JobPositionRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class JobService {
	
	private RestTemplate restTemplate;
	private JobPositionRepository jobPositionRepository;
	private KafkaTemplate<String, String> kafkaTemplate;
	public static final String URL_SEARCH = "https://jobs.github.com/positions.json?search=";
	public ObjectMapper mapper = new ObjectMapper();
	
	@Value(value = "${job.positions.topic}")
	private String topic;
	
	@Autowired
	public JobService (JobPositionRepository jobPositionRepository, KafkaTemplate<String, String> kafkatemplate) {
		restTemplate = new RestTemplate();
		this.jobPositionRepository = jobPositionRepository;
		this.kafkaTemplate = kafkatemplate;
	}
	
	@Cacheable("positions")
	public List<JobPosition> retrieveJobPositions(String keyWord){
		JobPosition[] jobPositions = restTemplate.getForObject(JobService.URL_SEARCH+keyWord, JobPosition[].class);
		return Arrays.asList(jobPositions);
	}
	
	public List<JobPosition> saveJobPositions(String keyWord) {
		List<JobPosition> jobPositions = retrieveJobPositions(keyWord);
		return jobPositionRepository.saveAll(jobPositions);
	}
	
	public String produceKafkaMessages() {
		List<JobPosition> jobPositions = jobPositionRepository.findAll();
		jobPositions.forEach( position -> {
			try {
				kafkaTemplate.send(topic, mapper.writeValueAsString(position));
			} catch (JsonProcessingException e) {
				log.error(e.getMessage());
			}
		} );
		return "done";
	}
	
	public JobPosition createPosition(final String type, final String company, final String url) {
		JobPosition newJob = new JobPosition();
		newJob.setType(type);
		newJob.setCompany(company);
		newJob.setUrl(url);
		JobPosition saved = jobPositionRepository.save(newJob);
		return saved;
	}

	public List<JobPosition> retrieveAllJobPositions() {
		return jobPositionRepository.findAll();
	}

}
