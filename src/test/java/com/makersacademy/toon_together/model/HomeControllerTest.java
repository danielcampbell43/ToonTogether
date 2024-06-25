//package com.makersacademy.toon_together.model;
//
//import com.makersacademy.toon_together.controller.HomeController;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(HomeController.class)
//public class HomeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @org.junit.jupiter.api.Test
//    public void testIndexRedirectsToPlaylists() throws Exception {
//        // Simulates an HTTP GET request to the root URL
//        mockMvc.perform(get("/"))
//                .andExpect(status().is3xxRedirection())
//                // Checks that the redirection URL is /playlists.
//                .andExpect(redirectedUrl("/playlists"));
//    }
//
//    @org.junit.jupiter.api.Test
//    public void testShowTermsAndConditions() throws Exception {
//        mockMvc.perform(get("/termsandconditions"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("termsandconditions"));
//    }
//}