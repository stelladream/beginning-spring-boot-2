/**
 * 
 */
package com.apress.demo;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Siva
 *
 */
@SpringBootTest(classes = SpringbootDataRestDemoApplication.class,
				webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringbootDataRestDemoApplicationTest
{
	
	private MockMvc mockMvc;

	@Autowired
    private WebApplicationContext webApplicationContext;
	
    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUsers()throws Exception
    {
    	mockMvc.perform(get("/api/users"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.parseMediaType("application/hal+json")))
        .andExpect(jsonPath("$._embedded.users", hasSize(3)))
        ;
    }
}
