package com.anita.climaxapp;

import com.anita.climaxapp.domain.FileParserResolverInterface;
import com.anita.climaxapp.infra.ExtensionFileResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClimaxAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClimaxAppApplication.class, args);
	}

	@Bean
	FileParserResolverInterface fileParserResolverInterface() {
		return new ExtensionFileResolver();
	}
}
