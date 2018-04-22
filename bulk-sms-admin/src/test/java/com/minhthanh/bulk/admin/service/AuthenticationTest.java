package com.minhthanh.bulk.admin.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContext;

import com.minhthanh.bulk.admin.config.WebSecurityConfigurationAware;


public class AuthenticationTest extends WebSecurityConfigurationAware {

  
    @Test
    public void requiresAuthentication() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(redirectedUrl("http://localhost/signin"));
    }

    @Test
    public void userAuthenticates() throws Exception {
        final String username = "admin@gmail.com";
        mockMvc.perform(post("/authenticate").param("username",username ).param("password", "admin"))
                .andExpect(redirectedUrl("/"))
                .andExpect(r -> Assert.assertEquals(((SecurityContext) r.getRequest().getSession().getAttribute(SEC_CONTEXT_ATTR)).getAuthentication().getName(), username));

    }

    @Test
    public void userAuthenticationFails() throws Exception {
        final String username = "user";
        mockMvc.perform(post("/authenticate").param("username", username).param("password", "invalid"))
                .andExpect(redirectedUrl("/signin?error=1"))
                .andExpect(r -> Assert.assertNull(r.getRequest().getSession().getAttribute(SEC_CONTEXT_ATTR)));
    }
    
}
