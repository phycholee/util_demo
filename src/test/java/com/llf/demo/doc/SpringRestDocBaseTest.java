package com.llf.demo.doc;

import com.llf.demo.DemoApplication;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

/**
 * @author: Oliver.li
 * @Description: SpringRestDoc测试基类
 * @date: 2018/5/22 16:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringRestDocBaseTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void init() {
        Snippet[] defaultSnippets = new Snippet[]{CliDocumentation.curlRequest(), CliDocumentation.httpieRequest(), HttpDocumentation.httpRequest(), HttpDocumentation.httpResponse(), PayloadDocumentation.requestBody(), PayloadDocumentation.responseBody()};
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)
//                        .snippets().withTemplateFormat(TemplateFormats.asciidoctor()).withEncoding("UTF-8")
//                        .withDefaults(defaultSnippets)
//                        .and()
                        .uris().withScheme("http").withHost("localhost").withPort(8080)
                        .and()
                )
                .build();
    }
}
