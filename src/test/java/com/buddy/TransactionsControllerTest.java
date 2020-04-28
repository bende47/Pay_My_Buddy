package com.buddy;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionsControllerTest {

	@Autowired
	private MockMvc mvc;	
	
	@Test
	public void versementTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/verser/1/Versement de 10 €/10")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	@Test
	public void retraitTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/retirer/1/Retrait de 10 €/10")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	@Test
	public void virementTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/virement/1/2/virement de 10 €/10.0")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}	
	
	@Test
	public void getCompteUserByIdTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/getCompteUserById/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());

	}	
	
	@Test
	public void versementRetraitTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/versementRetrait/1/10.0/Versement")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());

	}	
	
	@Test
	public void getAllTransactionTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getAllTransaction").param("idcpte", "1").param("page", "0").param("size", "3"))
				.andExpect(status().isOk());
		}
	
	

}
