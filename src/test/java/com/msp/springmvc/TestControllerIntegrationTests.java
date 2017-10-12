package com.msp.springmvc;

import com.msp.springmvc.config.MymvcConfig;
import com.msp.springmvc.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by zhao on 2017/2/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MymvcConfig.class})
@WebAppConfiguration("src/main/resource")//声明加载的ApplicationContext是一个WebApplicationContext,
                                        // 属性指定的是web资源的位置,默认为src/main/webapp,现在修改为src/main/resources
public class TestControllerIntegrationTests {

    private MockMvc mockMvc;//MockMvc-模拟MVC对象,通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化

    @Autowired
    private DemoService demoService;

    @Autowired
    private WebApplicationContext wac;//可注入WebApplicationContext

    @Autowired
    private MockHttpSession session;//可注入模拟的http session,此处仅作演示

    @Autowired
    private MockHttpServletRequest request;//可注入模拟的http request

    @Before//在测试开始前进行初始化工作
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testNormalController() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/normal"))//模拟向/normal进行get请求
                .andExpect(MockMvcResultMatchers.status().isOk())//预期控制返回状态为200
                .andExpect(MockMvcResultMatchers.view().name("page"))//预期view的名称为page
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/classes/views/page.jsp"))//预期页面转向的真正路径为/WEB-INF/classes/views/page.jsp
                .andExpect(MockMvcResultMatchers.model().attribute("msg",demoService.sayHello()));//预期model里的值是demoService.sayHello()返回值hello
    }

    @Test
    public void testRestController() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/testRest"))//模拟向/testRest进行get请求
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))//预期返回值的媒体类型为text/plain;charset=UTF-8
        .andExpect(MockMvcResultMatchers.content().string(demoService.sayHello()));//预期返回值是demoService.sayHello()返回值hello
    }
}
