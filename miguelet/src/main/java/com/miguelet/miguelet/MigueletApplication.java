package com.miguelet.miguelet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.scheduling.annotation.*;

@SpringBootApplication(scanBasePackages={"com.miguelet.miguelet.*"})
@EntityScan(basePackages = {"com.miguelet.miguelet.persistence.*"})
@EnableJpaRepositories(basePackages = {"com.miguelet.miguelet.persistence.*"})
public class MigueletApplication {

	public static void main(String[] args) {
		SpringApplication.run(MigueletApplication.class, args);
	}


}
