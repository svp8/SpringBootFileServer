package com.vlad.fileserver;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Bot extends ListenerAdapter {
    private String yandex="https://yandex.ru/images/search?source=collections&rpt=imageview&url=https://canon-fan.com/sites/default/files/imagecache/small_image/moon.jpg";
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!event.getAuthor().getName().contains("GoodDayBot")) {
            System.out.println(event.getAuthor().getName());
            if (event.getMessage().getContentDisplay().toUpperCase().contains("Hello".toUpperCase()) || event.getMessage().getContentDisplay().toUpperCase().contains("Hi".toUpperCase())) {
                event.getChannel().sendMessage("Hello, " + event.getAuthor().getName()).queue();
            }
            System.out.println(event.getMessage().getContentDisplay());
        }
    }

    @Autowired
    Env env1;
    public void start() {
        System.out.println(env1.getToken());
        JDA jda = JDABuilder.createDefault(env1.getToken()).setActivity(Activity.playing("Cs:go?")).enableIntents(GatewayIntent.MESSAGE_CONTENT).addEventListeners(new Bot()).build();

        try {
            jda.awaitReady();
            System.out.println(jda.getCategories().get(0).getTextChannels().get(0).getName());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
