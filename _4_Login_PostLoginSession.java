package com.epam.ess.essels.controllers;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class _4_Login_PostLoginSession {
    private MockMvc mockMvc;

    public void testLogin() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login")
                                                                      .param("emailAddress", "admin@exmaple.com")
                                                                      .param("password", "password");
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    public void testPostLogin() throws Exception {
        MockHttpSession session = new MockHttpSession();

        User user = new User();
        user.setEmailAddress("admin@example.com");
        user.setPassword("password");
        // user = userService.loginUser(user);

        session.setAttribute("user", user);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/app").session(session);
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());

    }

    private class User {
        private String emailAddress;
        private String password;

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}