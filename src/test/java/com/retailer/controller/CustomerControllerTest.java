package com.retailer.controller;

import com.google.common.collect.ImmutableList;
import com.retailer.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = CustomerController.class, secure = false)
public class CustomerControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createCustomerForValidRequestExpectStatus201Test() throws Exception {

        // given + when
        Mockito.when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(mockCustomer1);

        String marshalledCustomer = "{\n  \"firstName\": \"A\",\n  \"lastName\": \"B\"\n}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/customer")
                .accept(MediaType.APPLICATION_JSON)
                .content(marshalledCustomer)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        // then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void createCustomerExpectStatus400Test() throws Exception {

        // given + when
        Mockito.when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(mockCustomer1);

        String INVALID_FIRST_NAME = "n32sdk";
        String INVALID_LAST_NAME = "n231aska";

        String marshalledCustomer = "{\n  \"" + INVALID_FIRST_NAME + "\": \"A\",\n  \"\"" + INVALID_LAST_NAME + "\"\": \"B\"\n}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/customer")
                .accept(MediaType.APPLICATION_JSON)
                .content(marshalledCustomer)
                .contentType(MediaType.APPLICATION_JSON);

        // then

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }


    @Test
    public void findAllCustomersExpectNoErrorTest() throws Exception {

        Mockito.when(customerService.getAllCustomers())
                .thenReturn(ImmutableList.of(mockCustomer1, mockCustomer2));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/customer")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[\n  {\n    \"firstName\": \"A\",\n    \"lastName\": \"B\"\n  },\n  {\n    \"firstName\": \"C\",\n    \"lastName\": \"D\"\n  }\n]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
