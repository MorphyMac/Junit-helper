package com.epam.ess.essels.controllers;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class _AsyncCall {
    private MockMvc mockMvc;

    public void testController() throws Exception {
            /*  async endpoint
            @RequestMapping("test")
            public @ResponseBody Callable<String> handleTestRequest() {
                System.err.println("handler started");
                Callable<String> callable = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.err.println("async task started");
                        Thread.sleep(2000);
                        System.err.println("async task finished");

                        return "async result";              // --- result check below
                    }
                };

                System.err.println("handler finished");
                return callable;
            }
            */
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/test");
        this.mockMvc.perform(builder)
                    // async
                    .andExpect(MockMvcResultMatchers.request().asyncStarted())
                    .andExpect(MockMvcResultMatchers.request().asyncResult("async result"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }
}