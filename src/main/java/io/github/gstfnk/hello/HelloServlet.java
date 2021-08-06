package io.github.gstfnk.hello;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;


@RestController
class HelloServlet extends HttpServlet {

    private final HelloService service;

    HelloServlet(HelloService service) {
        this.service = service;
    }

    @GetMapping(value = "/api")
    @ResponseBody
    String createGreeting(@RequestParam(defaultValue = "World") String name,
                          @RequestParam(defaultValue = "1") Integer lang) {
        return service.prepareGreeting(name, lang);
    }
}
