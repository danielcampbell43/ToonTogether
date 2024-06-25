//package com.makersacademy.toon_together.handler;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Component
//public class CustomSignUpSuccessHandler implements AuthenticationSuccessHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(CustomSignUpSuccessHandler.class);
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        // Set signup success message in session
//        HttpSession session = request.getSession();
//        session.setAttribute("signupSuccessMessage", "Signup successful! Please log in.");
//
//        // Log success message setting
//        logger.info("Signup success message set in session: Signup successful!");
//
//        // Redirect to the login page
//        response.sendRedirect("/login");
//    }
//}
