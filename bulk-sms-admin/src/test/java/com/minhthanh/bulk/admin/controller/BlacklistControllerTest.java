package com.minhthanh.bulk.admin.controller;

import com.minhthanh.bulk.admin.config.WebSecurityConfigurationAware;
import com.minhthanh.bulk.admin.manager.PathManager;
import com.minhthanh.bulk.admin.service.BlacklistNumberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by youngkaka on 24/10/2016.
 */
public class BlacklistControllerTest extends WebSecurityConfigurationAware {

    @Autowired
    private BlacklistNumberService numberService;

    @Test
    public void testNumbersView() throws Exception {
        this.mockMvc.perform(get("/blacklist/number").with(admin())).andExpect(status().isOk())
                .andExpect(view().name(PathManager.BLACKLIST_NUMBER_VIEW))
                .andExpect(model().attributeExists("numbers"));
    }

    @Test
    public void testNewNumberView() throws Exception {
        this.mockMvc.perform(get("/blacklist/number/new").with(admin())).andExpect(status().isOk())
                .andExpect(view().name(PathManager.BLACKLIST_NEW_NUMBER_VIEW));
    }

    @Test
    public void testNewNumberPost() throws Exception {
        String number = "0123456789";
        this.mockMvc.perform(post("/blacklist/number/new").with(admin()).param("number",number))
                .andExpect(view().name("redirect:/blacklist/number"));
    }
}
