package com.thecoder777.k8sreadyspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Profile("test")
class HttpRequestTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void helloWorldShouldReturnHelloWorldMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/k8sreadyspringboot/hello/world",
                String.class)).contains("Hello, World!");
    }
}