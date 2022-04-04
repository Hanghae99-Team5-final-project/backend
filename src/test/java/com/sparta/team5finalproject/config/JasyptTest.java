package com.sparta.team5finalproject.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class JasyptTest {


    @Test
    public void jasyt_test() {
        String plaintText = "https://hooks.slack.com/services/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";
        String key = "hanghae99";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key);
//        config.setPassword(System.getenv("JASYPT_PASSWORD"));
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        String encryptText = encryptor.encrypt(plaintText);
        System.out.println(encryptText);
        String decryptText = encryptor.decrypt(encryptText);
        System.out.println(decryptText);
        assertThat(plaintText).isEqualTo(decryptText);

    }

}