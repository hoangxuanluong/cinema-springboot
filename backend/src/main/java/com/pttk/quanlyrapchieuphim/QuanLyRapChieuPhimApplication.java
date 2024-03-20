package com.pttk.quanlyrapchieuphim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuanLyRapChieuPhimApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanLyRapChieuPhimApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner commandLineRunner(
//            AuthenticationService service
//    ) {
//        return args -> {
//            var admin = RegisterRequest.builder()
////                    .firstname("Admin")
////                    .lastname("Admin")
//                    .email("admin@gmail.com")
//                    .password("admin")
//                    .role(ADMIN)
//                    .build();
//            System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//            var manager = RegisterRequest.builder()
////                    .firstname("Admin")
////                    .lastname("Admin")
//                    .email("manager@gmail.com")
//                    .password("manager")
//                    .role(MANAGER)
//                    .build();
//            System.out.println("Manager token: " + service.register(manager).getAccessToken());
//
//        };
//    }
}
