package com.vlad.fileserver;

import com.vlad.fileserver.models.FileRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class FileserverApplication {

	public static void main(String[] args) {

//		try {
//			BufferedReader reader = new BufferedReader(
//					new FileReader(".//files//lol9.txt"));
//			String text;
//			while((text=reader.readLine())!=null){
//
//				System.out.println(text);
//			}
//
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}

		String name = null;
//		FileRepository fileRepository=new FileRepository();
//		fileRepository.findAll();
//		try {
//			name = reader.readLine();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		File file=new File("files//lol9.txt");
//		try {
//			file.createNewFile();
//			BufferedWriter writer=new BufferedWriter(new FileWriter(file));
//			writer.write("test123");
//			writer.close();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}

		// Printing the read line
		System.out.println(name);
		SpringApplication.run(FileserverApplication.class, args);
//		Bot  bot= new Bot();
//		bot.start();
//		JDA jda = JDABuilder.createDefault(token).build();
	}

}
