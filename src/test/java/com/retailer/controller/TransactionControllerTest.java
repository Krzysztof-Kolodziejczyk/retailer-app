package com.retailer.controller;

import com.retailer.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = TransactionController.class, secure = false)
public class TransactionControllerTest extends BaseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void creatTransactionTest() throws Exception {

        // given + when
        when(transactionService.addTransaction(Mockito.any(Transaction.class))).thenReturn(mockTransaction1);

        String marshalledTransaction = "{\n  \"amount\": 100,\n  \"customer\": {\n    \"firstName\": \"A\",\n" +
                "    \"lastName\": \"B\"\n  },\n  \"localDate\": \"2000-12-12\"\n}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/transaction")
                .accept(MediaType.APPLICATION_JSON)
                .content(marshalledTransaction)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        // then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void updateTransactionIsPresentTest() throws Exception {

        when(transactionService.updateTransaction(Mockito.any(Transaction.class)))
                .thenReturn(mockTransaction1);

        String marshalledToUpdateTransaction = "{\n  \"transactionId\": 1,\n  \"amount\": 100," +
                "\n  \"customer\": {\n    \"firstName\": \"A\",\n    \"lastName\": \"B\"\n  },\n  \"localDate\": \"2000-12-05\"\n}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/transaction")
                .accept(MediaType.APPLICATION_JSON)
                .content(marshalledToUpdateTransaction)
                .contentType(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }


}
