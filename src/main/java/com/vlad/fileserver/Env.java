package com.vlad.fileserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:application.properties")
@Configuration
public class Env implements EnvironmentAware {


    private static Environment env;

    public static String getProperty(String key) {
        return env.getProperty(key);
    }

    @Override
    public void setEnvironment(final Environment environment) {
        this.env = environment;
    }
    public static String getToken() {
        System.out.println(env.getProperty("bot.token"));
        return env.getProperty("bot.token");
    }

    private String token;

    public void setToken(String token) {
        this.token = token;
    }
}
