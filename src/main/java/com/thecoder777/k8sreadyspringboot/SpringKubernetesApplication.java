package com.thecoder777.k8sreadyspringboot;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKubernetesApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringKubernetesApplication.class);

    @Value("${value.depending.on.environment}")
    private String valueDependingOnEnvironment;
    // do stuff with the environment dependent value, delete it or define more... e.g.:

    public static void main(String[] args) {
        SpringApplication.run(SpringKubernetesApplication.class, args);
    }

    @PostConstruct
    public void doSomething() {
        logger.info("Property value.depending.on.environment value is {}", valueDependingOnEnvironment);
    }
}
