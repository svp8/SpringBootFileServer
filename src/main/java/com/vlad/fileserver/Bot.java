package com.vlad.fileserver;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class Bot extends ListenerAdapter {
    @Autowired
    Env env1;
    private String yandex = "https://yandex.ru/images/search?source=collections&rpt=imageview&url=https://canon-fan.com/sites/default/files/imagecache/small_image/moon.jpg";
    private int gifAmount = 3;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getMessage().getContentDisplay().length() > 19 && event.getMessage().getContentDisplay().substring(0, 8).contains("!similar")) {
            String link = event.getMessage().getContentDisplay().substring(9);
            if (link.length()>18&&link.substring(0, 18).contains("https://tenor.com")) {

                try {
                    Document doc = Jsoup.connect(link).get();
                    Elements gifs = doc.select(".GifList");
                    Elements gifLinks = gifs.select("figure.GifListItem > a").select("a");
                    for (int i = 0; i < gifAmount; i++) {
                        Element el = gifLinks.get(i);
                        System.out.println(el.absUrl("href"));
                        String gif = el.absUrl("href");
                        gif = gif.replace("/ru/", "/");
                        event.getChannel().sendMessage(gif).queue();
                    }
                } catch (HttpStatusException e) {
                    event.getChannel().sendMessage("Bad request").queue();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

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
