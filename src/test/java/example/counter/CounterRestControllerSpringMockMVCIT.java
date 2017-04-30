package example.counter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CounterRestControllerSpringMockMVCIT {

    @Autowired
    MockMvc mvc;

    @Test
    public void testAdd() throws Exception {
        MvcResult mvcResult = 
                mvc.perform(
                        MockMvcRequestBuilders.post(CounterRestController.ADD_URL)
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
    public void testAddOverflow() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post(CounterRestController.ADD_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"int1\":1121121211212, \"int2\":222}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAdd2() throws Exception {
        MvcResult mvcResult = 
                mvc.perform(
                        MockMvcRequestBuilders.post(CounterRestController.ADD_URL)
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