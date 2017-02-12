package ru.levry.topword.web.controllers;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.levry.topword.Toper;

/**
 * @author levry
 */
@RestController
@RequestMapping("/words")
public class WordsController {

    private final Toper toper;

    @Autowired
    public WordsController(Toper toper) {
        this.toper = toper;
    }

    @RequestMapping("/most")
    public List<String> most(@RequestParam String prefix,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(prefix)) {
            return Collections.emptyList();
        }
        return toper.most(prefix, size);
    }
}
