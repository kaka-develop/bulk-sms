package com.minhthanh.bulk.admin.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by phuongtm on 10/5/16.
 */
public class BundleControllerTest extends AbstractMockMVCTest {

    @Test
    public void getIndex() throws Exception {
        mockMvc.perform(get("/abc")).andExpect(status().isOk());
    }
}
