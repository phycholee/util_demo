package com.llf.demo.module.example.controller;

import com.llf.demo.DemoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/18 11:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HateControllerTest {

    @Autowired
    private WebApplicationContext webApp;
    private MockMvc mockMvc;

    @Before
    public void init() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApp).build();
    }

    @Test
    public void get() {
    }
}