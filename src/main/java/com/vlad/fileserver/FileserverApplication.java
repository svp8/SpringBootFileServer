package com.vlad.fileserver;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileserverApplication.class, args);
		Bot  bot= new Bot();
		bot.start();
//		JDA jda = JDABuilder.createDefault(token).build();
	}

}
