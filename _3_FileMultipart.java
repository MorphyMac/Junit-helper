package com.epam.ess.essels.controllers;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class _3_FileMultipart {
    private MockMvc mockMvc;

    public void testController() throws Exception {

            /*
                @RequestMapping(value = "/upload", method = RequestMethod.POST)
                @ResponseBody
                public String saveAuto(
                        @RequestPart(value = "json-part") JsonPojo pojo,
                        @RequestParam(value = "some-random") String random,
                        @RequestParam(value = "file-part", required = false) List<MultipartFile> files) {
                    System.out.println(random);
                    System.out.println(pojo.getJson());
                    for (MultipartFile file : files) {
                        System.out.println(file.getOriginalFilename());
                    }
                    return "success";
                }



        MockMultipartFile firstFile = new MockMultipartFile("file-part", "filename.txt", "text/plain", "xml".getBytes
        ());
        MockMultipartFile secondFile = new MockMultipartFile("some-random", "other-file-name.data", "text/plain", "some
        other type".getBytes());
        MockMultipartFile jsonFile = new MockMultipartFile("json-part", "", "application/json", "{\"json\":
        \"someValue\"}".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(firstFile)
                        .file(secondFile)
                        .file(jsonFile)
                        .param("some-random", "4"))
                    .andExpect(status().is(200))
                    .andExpect(content().string("success"));


            Mockito.when(newController.postV1(Mockito.any(Integer.class), Mockito.any(MultipartFile.class)))
            .thenReturn(response);

            */


        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                // NOTE: form-data param name
                "user-file",                    /*form data identifier*/
                "test.txt",                     /*original file name*/
                null,                           /*content type*/
                "test data".getBytes()          /*main content*/);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/upload")
                                                                      // can add multiple file. same ID = user-file
                                                                      .file(mockMultipartFile).file(mockMultipartFile)
                                                                      .file(mockMultipartFile)
                                                                      .characterEncoding("UTF-8");
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }
}