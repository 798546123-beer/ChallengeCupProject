package com.cow.util.general;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
@Getter
@Setter
public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static final String REDIS_HOST;
    private static final String REDIS_PWD;
    private static final String PORT;
    private static final String DOMAIN;
    private static final String CALLBACK;
    private static final String FRONTEND;

    static {
        Properties properties = new Properties();
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("config" +
                ".properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("config.properties File not found!");
        }
        REDIS_HOST = (String) properties.get("redisHost");
        REDIS_PWD = (String) properties.get("redisPwd");
        PORT = (String) properties.get("port");
        DOMAIN = (String) properties.get("domain");
        CALLBACK = (String) properties.get("callback");
        FRONTEND = (String) properties.get("frontend");
    }

//    public static String getRedisHost() {
//        return REDIS_HOST;
//    }

//    public static String getRedisPwd() {
//        return REDIS_PWD;
//    }

//    public static String getPort() {
//        return PORT;
//    }

//    public static String getDomain() {
//        return DOMAIN;
//    }

//    public static String getCallback() {
//        return CALLBACK;
//    }

//    private PropertiesUtil() {
//    }
}
