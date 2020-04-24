package com.amarsoft.app.amps.asms;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.app.ems.system.util.UserHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ASMSApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp (){
        GlobalShareContextHolder.setUserId("AutoTest");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    

    @Test
    public void TestUserHelper() throws Exception {
        List<String> roles = UserHelper.getUserRoles("hzhang23","org_0");
    }

}
