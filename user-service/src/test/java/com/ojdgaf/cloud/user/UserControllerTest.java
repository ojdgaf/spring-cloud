package com.ojdgaf.cloud.user;

import com.ojdgaf.cloud.user.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Nested
    class FindUser {

        @Test
        @DisplayName("When ID is provided and user exists, return the user")
        public void findExistingUser() throws Exception {
            final String id = "id";
            final User user = new User(id, "John", "Doe", "john@doe.com");
            final String userJson = User.WRITER.writeValueAsString(user);

            when(userService.find(id)).thenReturn(user);
            mvc
                .perform(get("/users/" + id).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(userJson));
        }

        @Test
        @DisplayName("When ID is provided and user does not exist, return not found")
        public void findNonExistingUser() throws Exception {
            final String id = "id";

            when(userService.find(anyString())).thenThrow(NotFoundException.class);
            mvc
                .perform(get("/users/" + id).contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("When ID is not provided, return method not allowed")
        public void validateInputId() throws Exception {
            mvc
                .perform(get("/users").contentType(APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
        }
    }

    @Nested
    class CreateUser {

        @Test
        @DisplayName("When body is valid, create and return new user")
        public void createUser() throws Exception {
            final User user = new User(null, "John", "Doe", "john@doe.com");
            final String input = User.WRITER.writeValueAsString(user);
            final String output = User.WRITER.writeValueAsString(user.setId("id"));

            when(userService.create(any(User.class))).thenReturn(user);
            mvc
                .perform(post("/users").contentType(APPLICATION_JSON).content(input))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(output));
        }

        @Test
        @DisplayName("When firstname is missing, return bad request")
        public void validateUserFirstname() throws Exception {
            final User user = new User(null, null, "Doe", "john@doe.com");
            final String input = User.WRITER.writeValueAsString(user);

            final String message = mvc
                .perform(post("/users").contentType(APPLICATION_JSON).content(input))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResolvedException()
                .getMessage();
            assertThat(message).containsIgnoringCase("'firstname' is required");
        }

        @Test
        @DisplayName("When lastname is missing, return bad request")
        public void validateUserLastname() throws Exception {
            final User user = new User(null, "John", null, "john@doe.com");
            final String input = User.WRITER.writeValueAsString(user);

            final String message = mvc
                .perform(post("/users").contentType(APPLICATION_JSON).content(input))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResolvedException()
                .getMessage();
            assertThat(message).containsIgnoringCase("'lastname' is required");
        }

        @Test
        @DisplayName("When email is invalid, return bad request")
        public void validateUserEmail() throws Exception {
            final User user = new User(null, "John", "Doe", "email");
            final String input = User.WRITER.writeValueAsString(user);

            final String message = mvc
                .perform(post("/users").contentType(APPLICATION_JSON).content(input))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResolvedException()
                .getMessage();
            assertThat(message).containsIgnoringCase("must be a well-formed email address");
        }
    }
}
