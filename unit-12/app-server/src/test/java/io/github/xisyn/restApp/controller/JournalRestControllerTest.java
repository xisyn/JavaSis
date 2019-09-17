package io.github.xisyn.restApp.controller;

import io.github.xisyn.restApp.RestAppApplication;
import io.github.xisyn.restApp.data.JournalRepository;
import io.github.xisyn.restApp.entity.Journal;
import io.github.xisyn.restApp.service.JournalServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestAppApplication.class})
@WebAppConfiguration

/*@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(JournalRestController.class)*/
public class JournalRestControllerTest {

    /*@MockBean
    private JournalRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getJournalEntity() throws Exception {

    }*/


    @Autowired
    WebApplicationContext wac;

    @Autowired
    private JournalRepository journalRepository;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();

        Journal questionJournal = new Journal();
        questionJournal.setId(JournalServiceImpl.QUESTIONS_JOURNAL_ID);
        questionJournal.setName("Вопросы");
        questionJournal.setDefaultPageSize(15L);
        journalRepository.save(questionJournal);
    }

    @Test
    public void getJournalEntity() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.
                    get("api/journal/questions").
                    accept(MediaType.ALL)).
                    andDo(print()).
                    andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getRows() {
    }
}