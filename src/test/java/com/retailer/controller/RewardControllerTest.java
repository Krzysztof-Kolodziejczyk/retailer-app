package com.retailer.controller;


import com.retailer.service.RewardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = RewardController.class, secure = false)
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RewardService rewardService;

    private final String SAMPLE_CUSTOMER_FIRST_NAME = "A";
    private final String SAMPLE_CUSTOMER_LAST_NAME = "B";
    private final int SMAPLE_REWARD_VALUE = 100;

    @Test
    public void getMonthlyPointsForValidRequestExpectStatus200Test() throws Exception {

        final int SAMPLE_YEAR = 2000;
        final int VALID_MONTH = 10;

        when(rewardService.calculateMonthlyReward(anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(SMAPLE_REWARD_VALUE);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reward/" + SAMPLE_CUSTOMER_FIRST_NAME + "/" + SAMPLE_CUSTOMER_LAST_NAME + "/monthlyPoints")
                .param("year", String.valueOf(SAMPLE_YEAR))
                .param("month", String.valueOf(VALID_MONTH))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getTotalPointsForValidRequestExpectStatus200Test() throws Exception {

        when(rewardService.calculateTotalReward(anyString(), anyString()))
                .thenReturn(SMAPLE_REWARD_VALUE);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reward/" + SAMPLE_CUSTOMER_FIRST_NAME + "/" + SAMPLE_CUSTOMER_LAST_NAME + "/totalPoints")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
