package ru.levry.topword.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import ru.levry.topword.TopWord;
import ru.levry.topword.Toper;
import ru.levry.topword.support.WordReader;

import java.util.Collection;

/**
 * @author levry
 */
@Configuration
public class TopwordConfig {

    @Value("${topword.filename}")
    private Resource filename;

    @Bean
    public Toper toper() throws Exception {
        Collection<TopWord> words = readWords();
        return new Toper(words);
    }

    private Collection<TopWord> readWords() throws Exception {
        WordReader reader = new WordReader(filename.getFile());
        return reader.read();
    }
}
