package com.thecoder777.k8sreadyspringboot;

import com.thecoder777.k8sreadyspringboot.controllers.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Profile("test")
class SpringKubernetesApplicationTests {

    @Autowired
    private HelloController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
