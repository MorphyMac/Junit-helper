package com.epam.ess.essels.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@AutoConfigureJsonTesters
@ContextConfiguration(/*classes = MyWebConfig.class*/)
public class ControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    private String requestBodyJson() {
        /*
            // This object will be initialized thanks to @AutoConfigureJsonTesters
            @Autowired
            private JacksonTester<SuperHero> jsonSuperHero;   SuperHero - POJO for jsonString
        */
        return "{\"id\":\"" + 1 + "\", \"content\":\"test data\"}";
    }


    @Test
    public void testUserController() throws Exception {
        // request builders
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.head("/endpoint");
        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/trades")
                                                                         // query params
                                                                         .param("id", "4").param("page", "1")
                                                                         // headers
                                                                         .header("User-Agent", "Spring test")
                                                                         .header("Authentication", "---token---");
        MockHttpServletRequestBuilder postJson = MockMvcRequestBuilders.post("/users")
                                                                       .contentType(MediaType.APPLICATION_JSON)
                                                                       .content("{ \"userName\": \"Joe\"}");
        MockHttpServletRequestBuilder postXml = MockMvcRequestBuilders.post("/users")
                                                                      .contentType(MediaType.APPLICATION_XML)
                                                                      .content("<user><userName>Joe</userName></user>");
        MockHttpServletRequestBuilder putRequest = MockMvcRequestBuilders.put("/articles/" + 1)
                                                                         .contentType(MediaType.APPLICATION_JSON)
                                                                         .accept(MediaType.APPLICATION_JSON)
                                                                         .characterEncoding("UTF-8")
                                                                         .content(requestBodyJson());
        // for creating/updating  through params
        MockHttpServletRequestBuilder formEncoded = MockMvcRequestBuilders.put("/articles/" + 1)
                                                                          .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                                                          .accept(MediaType.APPLICATION_FORM_URLENCODED)
                                                                          .characterEncoding("UTF-8")
                                                                          .content("id=" + 1 + "&content=test data");

        long id = 1;
        String content = "new updated content";
        MockHttpServletRequestBuilder patchRequest = MockMvcRequestBuilders.patch("/articles/" + id)
                                                                           .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                                           .accept(MediaType.APPLICATION_JSON)
                                                                           .characterEncoding("UTF-8")
                                                                           .content(requestBodyJson());

        // result matchers
        ResultMatcher statusResultMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher status404 = MockMvcResultMatchers.status().is(404);
        // for produces -  postJson.accept(MediaType.APPLICATION_JSON) / postXml.accept(MediaType.APPLICATION_XML)
        ResultMatcher jsonResponseField = MockMvcResultMatchers.jsonPath("userName").value("Joe");
        ResultMatcher xmlResponseField = MockMvcResultMatchers.xpath("userName").string("Joe");
        // mvc
        ResultMatcher modelHasAttribute = MockMvcResultMatchers.model().attribute("userStringId", "abc");
        ResultHandler printResponse = MockMvcResultHandlers.print();

        this.mockMvc.perform(builder)
                    // can have multiple matcher
                    .andExpect(statusResultMatcher) // any status result matcher
                    .andExpect(jsonResponseField)
                    // simple string response
                    .andExpect(MockMvcResultMatchers.content().string("Article created."))
                    // and Do
                    .andDo(printResponse);

    }
}