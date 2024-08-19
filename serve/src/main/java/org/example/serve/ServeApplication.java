package org.example.serve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.example.serve.model.User;
import org.example.serve.repositories.UserRepository;


@SpringBootApplication
public class ServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServeApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");

            if (!userRepository.findByEmail(admin.getEmail()).isPresent()) {
                userRepository.save(admin);
            }
        };
    }


}
