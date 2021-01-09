package se.mindstar.artclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableSwagger2
public class ArtClubApplication {

	public static void main(String... args) {
		SpringApplication.run(ArtClubApplication.class, args);
	}

	/*@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.tutorialspoint.swaggerdemo")).build();
	}*/
}
