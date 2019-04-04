package com.mymicroservice.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mymicroservice.demo.domain.JobPosition;

public interface JobPositionRepository extends MongoRepository<JobPosition, String> {

}
