package com.epam.ess.essels.controllers;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class _1_Extra {
    private MockMvc mockMvc;

    public void testGet() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/test");
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

    public void testHead() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.head("/test");
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

    public void testOptions() throws Exception {
        ResultMatcher accessHeader = MockMvcResultMatchers.header().string("Allow", "GET,HEAD");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.options("/test");
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(accessHeader)
                    .andDo(MockMvcResultHandlers.print());
    }
}