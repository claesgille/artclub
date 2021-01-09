package se.mindstar.artclub;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.mindstar.artclub.model.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        User user = new User(1L, "kalle@kanelbulle.se", false);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new User(1L, "kalle@kanelbulle.se", false));

        this.mockMvc.perform(post("/user", user)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void shouldGetUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new User(1L, "kalle@kanelbulle.se", false));

        this.mockMvc.perform(get("/user/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "kalle@kanelbulle.se", false));
        String json = mapper.writeValueAsString(users);

        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new User(1L, "kalle@kanelbulle.se", false));

        this.mockMvc.perform(delete("/user/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldNotFindUser() throws Exception {
        this.mockMvc.perform(get("/user/2")).andDo(print()).andExpect(status().is(404));
    }

    @Test
    public void shouldBeBadRequest() throws Exception {
        this.mockMvc.perform(get("/user/x")).andDo(print()).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

}