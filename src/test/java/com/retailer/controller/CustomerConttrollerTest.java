package com.retailer.controller;

import com.retailer.model.Customer;
import com.retailer.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = CustomerController.class, secure = false)
public class CustomerConttrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private final Customer mockCustomer = new Customer("A", "B");

    @Test
    public void createCustomerTest() throws Exception {

        // given + when
        Mockito.when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(mockCustomer);

        String marshalledCustomer = "{\"firstName\": \"A\",\"lastName\": \"B\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/customer")
                .accept(MediaType.APPLICATION_JSON).content(marshalledCustomer)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }
}
