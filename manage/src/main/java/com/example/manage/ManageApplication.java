package com.example.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.manage"})
@EnableSwagger2
public class ManageApplication {
    private static final Logger log = LoggerFactory.getLogger(ManageApplication.class);
    private static final String SERVER_PORT = "server.port";

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(ManageApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "SwaggerUI: \thttp://localhost:{}/swagger-ui.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty(SERVER_PORT),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty(SERVER_PORT),
                env.getProperty(SERVER_PORT));
    }
}
