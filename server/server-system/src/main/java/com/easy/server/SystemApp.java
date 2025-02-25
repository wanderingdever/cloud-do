package com.easy.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@Slf4j
public class SystemApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SystemApp.class);
        ConfigurableApplicationContext run = app.run(args);
        Environment env = run.getEnvironment();
        String severPort = env.getProperty("server.port");
        String logo = """
                ███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗
                ██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║
                ███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║
                ╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║
                ███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║
                ╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝
                PROFILE: %s
                SERVER PORT: %s""";
        log.error("\n{}", String.format(logo, Arrays.toString(env.getActiveProfiles()), severPort));
    }
}