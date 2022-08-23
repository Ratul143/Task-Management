package com.surjomukhi.taskmanagement.utils;


import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ImagePath {
	
	private String basePath = System.getProperty("user.dir") + "/images/";
	

}
