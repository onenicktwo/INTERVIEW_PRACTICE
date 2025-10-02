package com.sourceallies.interview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourceallies.interview.controller.OrderController;
import com.sourceallies.interview.model.Order;
import com.sourceallies.interview.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    private Order createTestRequestOrder() {
        Order requestOrder = new Order();
        requestOrder.setCustomerName("John Doe");
        requestOrder.setProductName("Table");
        requestOrder.setProductQuantity(5);
        return requestOrder;
    }

    @Test
    public void testCreateOrderSuccess() throws Exception {
        Order requestOrder = createTestRequestOrder();

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setCustomerName("Jane Doe");
        savedOrder.setProductName("Chair");
        savedOrder.setProductQuantity(10);

        when(orderService.createOrder(any(Order.class))).thenReturn(savedOrder);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestOrder)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(savedOrder.getId()))
                .andExpect(jsonPath("$.customerName").value(savedOrder.getCustomerName()))
                .andExpect(jsonPath("$.productName").value(savedOrder.getProductName()))
                .andExpect(jsonPath("$.productQuantity").value(savedOrder.getProductQuantity()));
    }

    @Test
    public void testCreateOrderEmptyCustomerNameFail() throws Exception {
        Order requestOrder = new Order();
        requestOrder.setId(1L);
        requestOrder.setProductName("Chair");
        requestOrder.setProductQuantity(10);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestOrder)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateOrderEmptyProductNameFail() throws Exception {
        Order requestOrder = new Order();
        requestOrder.setId(1L);
        requestOrder.setCustomerName("Jane Doe");
        requestOrder.setProductQuantity(10);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestOrder)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateOrderNegativeProductQuantityFail() throws Exception {
        Order requestOrder = new Order();
        requestOrder.setId(1L);
        requestOrder.setCustomerName("Jane Doe");
        requestOrder.setProductName("Chair");
        requestOrder.setProductQuantity(-1);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestOrder)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateOrderNullProductQuantityFail() throws Exception {
        Order requestOrder = new Order();
        requestOrder.setId(1L);
        requestOrder.setCustomerName("Jane Doe");
        requestOrder.setProductName("Chair");

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestOrder)))
                .andExpect(status().isBadRequest());
    }
}
