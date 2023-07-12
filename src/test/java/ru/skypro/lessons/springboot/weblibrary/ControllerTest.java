package ru.skypro.lessons.springboot.weblibrary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    void getAllTest() throws Exception{
        mockMvc.perform(get("/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }
    @Test
    void deleteTest() throws Exception{
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","test_name");
        jsonObject1.put("salary",1);
        jsonObject1.put("position",1);
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject1.toString()))
                .andExpect(status().isOk());
        long id = 1;
        mockMvc.perform(delete("/delete/{id}", id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
    @Test
    void addEmployeeTest() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","test_name");
        jsonObject.put("salary",1);
        jsonObject.put("position",1);
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("test_name"));
    }


    @Test
    void getEmployeesByIdTest() throws Exception{
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name","test_name");
        jsonObject2.put("salary",1);
        jsonObject2.put("position",1);
        mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject2.toString()))
                .andExpect(status().isOk());
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("name","test_name2");
        jsonObject3.put("salary",1);
        jsonObject3.put("position",1);
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject3.toString()))
                .andExpect(status().isOk());
        long id = 2;
        mockMvc.perform(get("/{id}",id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("test_name2"));
    }
    @Test
    void getHighestSalary() throws Exception{
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("name","test_name");
        jsonObject4.put("salary",3);
        jsonObject4.put("position",1);
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject4.toString()))
                .andExpect(status().isOk());
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("name","test_name2");
        jsonObject5.put("salary",1);
        jsonObject5.put("position",1);
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject5.toString()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/withHighestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").value(3));
    }
}
