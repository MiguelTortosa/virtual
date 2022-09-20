package com.miguelet.miguelet;

import lombok.extern.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.test.context.*;

@SpringBootApplication(scanBasePackages={"com.miguelet.miguelet.*"})
@EntityScan(basePackages = {"com.miguelet.miguelet.persistence.*"})
@EnableJpaRepositories(basePackages = {"com.miguelet.miguelet.persistence.*"})
@ActiveProfiles("test")
@Slf4j
public class TestApi {

  public static void main(String[] args) {
    SpringApplication.run(TestApi.class, args);
  }
}

