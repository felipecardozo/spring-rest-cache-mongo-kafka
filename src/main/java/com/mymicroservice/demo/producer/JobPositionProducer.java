package com.mymicroservice.demo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.mymicroservice.demo.domain.JobPosition;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JobPositionProducer {
	/*
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, JobPosition> jobPositionKafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;
    
    public void sendMessage(String message) {
        
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
    
    public void sendJobPositionMessage(JobPosition jobPosition) {
    	jobPositionKafkaTemplate.send(topicName, jobPosition);
    }*/

}
