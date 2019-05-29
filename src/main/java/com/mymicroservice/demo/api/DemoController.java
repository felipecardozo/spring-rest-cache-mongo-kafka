package com.mymicroservice.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymicroservice.demo.domain.JobPosition;
import com.mymicroservice.demo.service.JobService;

@RestController
public class DemoController {
	
	private JobService jobService;
	
	@Autowired
	public DemoController(JobService jobService) {
		this.jobService = jobService;
	}
	
	/** http://localhost:8080/positions/java **/
	@GetMapping("/positions/{keyWord}")
	public List<JobPosition>  getPositions(@PathVariable String keyWord) {
		return jobService.retrieveJobPositions(keyWord);
	}
	
	@GetMapping("/positions/all")
	public List<JobPosition> getAllPositions(){
		return jobService.retrieveAllJobPositions();
	}
	
	/** http://localhost:8080/save/java **/
	@GetMapping("/save/{keyWord}")
	public List<JobPosition> savePositions(@PathVariable String keyWord) {
		return jobService.saveJobPositions(keyWord);
	}
	
	/** http://localhost:8080/produce **/
	@GetMapping("/produce")
	public String sendKafkaPositions() {
		return jobService.produceKafkaMessages();
	}
	
	@PostMapping("/add/job")
	public JobPosition createPosition(@RequestBody JobPosition job){
		return jobService.createPosition(job);
	}
	
	@PutMapping("/updated/job")
	public JobPosition updatePosition( @RequestBody JobPosition job ) {
		return null;
	}

}
