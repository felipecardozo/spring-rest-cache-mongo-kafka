package com.mymicroservice.demo.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class JobPosition{
	
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
