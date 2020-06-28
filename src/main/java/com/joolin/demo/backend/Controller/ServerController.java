package com.joolin.demo.backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ServerController {
    @GetMapping("/test/hello")
    public HashMap hello(){
        HashMap result = new HashMap();
        result.put("message", "Hello joolinie");

        return result;
    }
}
