package com.lilium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalculationController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/countusers")
    public Integer countUsers() {
        final List<String> users = restTemplate.exchange(
                "http://userservice/api/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                }).getBody();

        if (users == null) {
            return 0;
        }

        return users.size();
    }
}
