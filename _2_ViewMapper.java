package com.epam.ess.essels.controllers;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class _2_ViewMapper {
    private MockMvc mockMvc;

    private void getMappingView() throws Exception {
        ResultMatcher viewMatcher = MockMvcResultMatchers.view().name("GetMapping-view");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/test");
        this.mockMvc.perform(builder).andExpect(viewMatcher).andExpect(MockMvcResultMatchers.status().isOk());
    }

    public void postMappingView() throws Exception {
        ResultMatcher viewMatcher = MockMvcResultMatchers.view().name("PostMapping-view");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/test");
        this.mockMvc.perform(builder).andExpect(viewMatcher).andExpect(MockMvcResultMatchers.status().isOk());
    }

    public void putMappingView() throws Exception {
        ResultMatcher viewMatcher = MockMvcResultMatchers.view().name("PutMapping-view");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/test");
        this.mockMvc.perform(builder).andExpect(viewMatcher).andExpect(MockMvcResultMatchers.status().isOk());
    }

    public void deleteMappingView() throws Exception {
        ResultMatcher viewMatcher = MockMvcResultMatchers.view().name("DeleteMapping-view");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/test");
        this.mockMvc.perform(builder).andExpect(viewMatcher).andExpect(MockMvcResultMatchers.status().isOk());
    }

    public void patchMappingView() throws Exception {
        ResultMatcher viewMatcher = MockMvcResultMatchers.view().name("PatchMapping-view");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/test");
        this.mockMvc.perform(builder).andExpect(viewMatcher).andExpect(MockMvcResultMatchers.status().isOk());
    }
}