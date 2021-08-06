package io.github.gstfnk.hello;

import io.github.gstfnk.lang.Lang;
import io.github.gstfnk.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class HelloService {
    static final String FALLBACK_NAME = "World";
    static final Lang FALLBACK_LANG = new Lang(1, "Hello", "en");

    private final LangRepository repository;

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreeting(String name, Integer langId) {
        langId = Optional.ofNullable(langId).orElse(FALLBACK_LANG.getId());
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = FALLBACK_NAME;
        if (!name.equals("")) nameToWelcome = name;
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
