package com.mymicroservice.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Document(collection = "job_position")
public class JobPosition{
	
	@Id
	private String id;
	private String type;
	private String url;
	@JsonAlias("created_at")
	private String createdAt;
	private String company;
	@JsonAlias("company_url")
	private String companyUrl;
	private String location;
	private String title;
	private String description;

}
