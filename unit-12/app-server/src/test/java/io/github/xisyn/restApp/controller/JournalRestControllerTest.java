package io.github.xisyn.restApp.controller;

import io.github.xisyn.restApp.RestAppApplication;
import io.github.xisyn.restApp.data.JournalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestAppApplication.class})
@WebAppConfiguration
public class JournalRestControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    private JournalRepository journalRepository;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    public void getJournalEntity() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.
                get("api/journal/questions").
                accept(MediaType.ALL)).
                andDo(print()).
                andExpect(status().isOk());

        /*mockMvc.perform(MockMvcRequestBuilders.
                get("api/journal/questions").
                accept(MediaType.ALL)).
                andDo(print()).
                andExpect((ResultMatcher) new JournalEntityDTO(journalRepository.findById(QUESTIONS_JOURNAL_ID).
                        orElseThrow((() -> new RuntimeException(String.format("There is no journal with id: %s", QUESTIONS_JOURNAL_ID))))));*/

        /*for (String id : journalIds) {
            mockMvc.perform(MockMvcRequestBuilders.
                    get("api/journal/" + id).
                    accept(MediaType.ALL)).
                    andDo(print()).
                    andExpect((ResultMatcher) journalRepository.findById(id).
                            orElseThrow((() -> new RuntimeException(String.format("There is no journal with id: %s", id)))));
        }*/

    }

    @Test
    public void getRows() {
    }
}