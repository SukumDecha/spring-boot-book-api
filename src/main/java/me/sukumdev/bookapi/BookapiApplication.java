package me.sukumdev.bookapi;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BookapiApplication {

	// implements CommandLineRunner to run the application

	public static void main(String[] args) {
		SpringApplication.run(BookapiApplication.class, args);
	}

	//	@Override
	//	public void run(String... args) throws Exception {
	//		Book newBook = Book.builder()
	//				.title("Spring Boot in Action")
	//				.author("Craig Walls")
	//				.price(35.00)
	//				.build();
	//
	//		bookRepo.save(newBook);
	//	}
}
