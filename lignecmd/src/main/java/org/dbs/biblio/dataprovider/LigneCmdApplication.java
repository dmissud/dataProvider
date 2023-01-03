package org.dbs.biblio.dataprovider;

import lombok.extern.slf4j.Slf4j;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;


@SpringBootApplication
@Slf4j
@EnableCaching
public class LigneCmdApplication {

    public static void main(String[] args)  {
        SpringApplication application = new SpringApplication(LigneCmdApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("my-shell:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}