package com.surjomukhi.taskmanagement.utils;


import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Configuration
public class CustomCodeGenerator {

	private static String name = "PLAYER";

	public String getGeneratedUuid() {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMDDMMSS");
		Date date = new Date();
		Random rand = new Random();
		int value = rand.nextInt(999999);
		String today = formatter.format(date);
		String ids = name + '-' + today + '-' + value;
		return ids;
	}
	

}
