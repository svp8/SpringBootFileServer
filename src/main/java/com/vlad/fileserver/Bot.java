package com.vlad.fileserver;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Bot {
    @Autowired
    Env env1;
    public void start(){
        System.out.println(env1.getToken());
        JDA jda = JDABuilder.createDefault(env1.getToken()).build();
    }
}
