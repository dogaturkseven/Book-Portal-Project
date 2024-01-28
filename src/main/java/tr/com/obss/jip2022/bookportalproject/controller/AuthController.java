package tr.com.obss.jip2022.bookportalproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping(path = "/basic/auth")
    public AuthenticationBean authenticate() {

        return new AuthenticationBean("You are authenticated");
    }
}