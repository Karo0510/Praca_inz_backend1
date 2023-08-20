package com.example.bhp.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        try
        {
            System.out.println(authentication.isAuthenticated());
        }catch(Exception ex)
        {
            System.out.println("obsluzony wyjatek");
        }


        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Logged out successfully");
        response.getWriter().flush();
    }
}
