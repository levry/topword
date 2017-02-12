package ru.levry.topword.web.controllers;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.levry.topword.TopWord;
import ru.levry.topword.Toper;

/**
 * @author levry
 */
public class WordsControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        Toper toper = new Toper(getWords());
        mockMvc = MockMvcBuilders.standaloneSetup(new WordsController(toper)).build();
    }

    private Collection<TopWord> getWords() {
        return Arrays.asList(
                new TopWord("linear", 2),
                new TopWord("lingerie", 3),
                new TopWord("lock", 4)
        );
    }

    @Test
    public void shouldBeReturnTopWords() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/words/most?prefix=l"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("lock"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("lingerie"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value("linear"));
    }

    @Test
    public void shouldBeReturnEmptyWordsIfPrefixIsBlank() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/words/most?prefix="))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    public void shouldBeRangeWordsBySize() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/words/most?prefix=l&size=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }
}