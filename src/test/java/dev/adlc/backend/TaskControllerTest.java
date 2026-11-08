package dev.adlc.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired MockMvc mvc;

    @Test void taskCanBeCreatedAndCompleted() throws Exception {
        String body = mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Review PR\",\"priority\":\"HIGH\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.status").value("PENDING"))
            .andReturn().getResponse().getContentAsString();
        long id = new com.fasterxml.jackson.databind.ObjectMapper().readTree(body).get("id").asLong();
        mvc.perform(post("/api/tasks/" + id + "/complete"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("COMPLETED"));
    }
    @Test void rejectsBlankTitle() throws Exception {
        mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"\"}"))
            .andExpect(status().isBadRequest());
    }
    @Test void accepts200CharacterTitle() throws Exception {
        String title = "a".repeat(200);
        String json = "{\"title\":\"" + title + "\",\"priority\":\"HIGH\"}";
        mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value(title));
    }
    @Test void rejects201CharacterTitle() throws Exception {
        String title = "a".repeat(201);
        String json = "{\"title\":\"" + title + "\"}";
        mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isBadRequest());
    }
    @Test void healthIsAvailable() throws Exception {
        mvc.perform(get("/healthz")).andExpect(status().isOk());
    }
}
