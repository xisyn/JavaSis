package io.github.xisyn.restApp.controller;

import io.github.xisyn.restApp.data.JournalRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestAppApplication.class})
@WebAppConfiguration*/

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(JournalRestController.class)
public class JournalRestControllerTest {

    @MockBean
    private JournalRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getJournalEntity() throws Exception {

    }


    /*@Autowired
    WebApplicationContext wac;

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
                andExpect(MockMvcResultMatchers.status().isOk());

    }*/

    @Test
    public void getRows() {
    }
}