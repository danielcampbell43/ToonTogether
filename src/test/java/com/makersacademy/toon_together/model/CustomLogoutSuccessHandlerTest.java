package com.makersacademy.toon_together.model;

import com.makersacademy.toon_together.handler.CustomLogoutSuccessHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomLogoutSuccessHandlerTest {

    @InjectMocks
    CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    Authentication authentication;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testOnLogoutSuccess() throws IOException, ServletException {
        customLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);

        // Check that the session attribute was set correctly
        verify(session).setAttribute("logoutMessage", "You have successfully logged out!");

        // Check that the response was redirected to the correct login page with a logout parameter
        verify(response).sendRedirect("/login?logout");
    }
}