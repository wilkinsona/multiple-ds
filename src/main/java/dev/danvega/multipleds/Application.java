package dev.danvega.multipleds;

import dev.danvega.multipleds.post.Post;
import dev.danvega.multipleds.post.PostService;
import dev.danvega.multipleds.subscriber.Subscriber;
import dev.danvega.multipleds.subscriber.SubscriberService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostService postService, SubscriberService subscriberService) {
		return args -> {

			List<Post> posts = postService.findAll();
			System.out.println(posts);

			List<Subscriber> subscribers = subscriberService.findAll();
			System.out.println(subscribers);

		};
	}

	@Bean("subscriberJdbcClient")
	JdbcClient jdbcClient(@Qualifier("subscriberDataSource") DataSource dataSource) {
		return JdbcClient.create(dataSource);
	}

}