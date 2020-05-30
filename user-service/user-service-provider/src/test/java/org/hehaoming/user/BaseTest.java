package org.hehaoming.user;

import com.alibaba.fastjson.JSONObject;
import org.hehaoming.user.constant.Constant;
import org.hehaoming.user.domain.query.AddUser;
import org.hehaoming.user.domain.vo.FindLikeUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class BaseTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void addUser() throws Exception {
        String url = "/user/addUser";
        AddUser addUser = new AddUser("13100000004","test2","test");
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(addUser).getBytes())
        )
                .andExpect(jsonPath("$.code", is("1")))
                .andExpect(jsonPath("$.payload", is(Constant.SUCCESS)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    public void findLikeUser() throws Exception {
        String url = "/user/findLikeUser";
        FindLikeUser findLikeUser = new FindLikeUser(1,5,null,"13","",null);
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(findLikeUser).getBytes())
        )
                .andExpect(jsonPath("$.payload.rows[0].name", is("hhm")))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
/*
Body = {"code":"1","payload":{"pageNum":1,"pageSize":5,"pages":4,"rows":[{"gmtCreate":1588491138000,"gmtModified":1588491482000,"id":1,"isDeleted":true,"name":"hhm","phone":"13133333333","pwd":"123"},{"gmtCreate":1588491542000,"gmtModified":1588493879000,"id":2,"isDeleted":true,"name":"sdfa","phone":"13122222222","pwd":"string"},{"gmtCreate":1588573632000,"gmtModified":1588573780000,"id":4,"isDeleted":true,"name":"string","phone":"13122222222","pwd":"string"},{"gmtCreate":1588573785000,"gmtModified":1588573834000,"id":5,"isDeleted":true,"name":"string","phone":"13122222222","pwd":"string"},{"gmtCreate":1588573851000,"gmtModified":1588575374000,"id":6,"isDeleted":false,"name":"string","phone":"13111111211","pwd":"string"}],"total":18},"success":true}
 */
    }




}
