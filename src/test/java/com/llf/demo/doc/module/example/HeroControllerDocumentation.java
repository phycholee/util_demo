package com.llf.demo.doc.module.example;

import com.llf.demo.doc.SpringRestDocBaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/5/22 16:15
 */
public class HeroControllerDocumentation extends SpringRestDocBaseTest {

    @Test
    public void list() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "Tornado");

        this.mockMvc.perform(get("/api/hero").params(params).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("hero-list",
                        requestParameters(
                                parameterWithName("name").description("名称")
                        ),
                        relaxedResponseFields(
//                                fieldWithPath("data.id").type("Integer").description("编号"),
                                fieldWithPath("data").type("Object[]").description("用户列表")
                        )
                ));
    }

//    @Test
//    public void get1() {
//    }
//
//    @Test
//    public void update() {
//    }
//
//    @Test
//    public void save() {
//    }
//
//    @Test
//    public void delete() {
//    }
//
//    @Test
//    public void string() {
//    }
}