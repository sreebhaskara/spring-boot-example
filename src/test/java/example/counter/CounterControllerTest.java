package example.counter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
public class CounterControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(
                new CounterController(new CounterService())).build();
    }

    @Test
    public void testAdd() throws Exception {
        MvcResult mvcResult = 
                mvc.perform(
                        MockMvcRequestBuilders.post("/add")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"int1\":1, \"int2\":2}"))
                    .andExpect(status().isOk())
                    .andExpect(request().asyncStarted())
                    .andReturn();
        mvc.perform(asyncDispatch(mvcResult))
                .andExpect(content().json("{\"value\": 3}", false));
    }
    @Test
    public void testAdd2() throws Exception {
        MvcResult mvcResult = 
                mvc.perform(
                        MockMvcRequestBuilders.post("/add")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"int1\":2, \"int2\":3}"))
                    .andExpect(status().isOk())
                    .andExpect(request().asyncStarted())
                    .andReturn();
        mvc.perform(asyncDispatch(mvcResult))
                .andExpect(jsonPath("value").value(5));
    }
}