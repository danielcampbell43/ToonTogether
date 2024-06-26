package com.makersacademy.toon_together.model;

import com.makersacademy.toon_together.handler.CustomLoginSuccessHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CustomLoginSuccessHandlerTest {

    private CustomLoginSuccessHandler customLoginSuccessHandler;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        customLoginSuccessHandler = new CustomLoginSuccessHandler();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        authentication = mock(Authentication.class);
    }

    @Test
    void testOnAuthenticationSuccess() throws Exception {
        customLoginSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        HttpSession session = request.getSession();
        assertEquals("Happy Tooning!", session.getAttribute("loginMessage"));
        assertEquals("/", response.getRedirectedUrl());
    }
}
