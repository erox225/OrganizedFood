package back.soa.organizedFood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "back.soa.organizedFood.dao")
@EntityScan(basePackages = "back.soa.organizedFood.entity")
public class OrganizedFoodApplication {



	public static void main(String[] args) {
		System.setProperty("spring.main.lazy-initialization", "true");
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(OrganizedFoodApplication.class, args);
	}

}
