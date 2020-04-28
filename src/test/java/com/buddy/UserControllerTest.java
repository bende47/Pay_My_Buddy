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
class UserControllerTest {

	@Autowired
	private MockMvc mvc;
	
		
	@Test
	public void addUserTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.post("/addUser").contentType(MediaType.APPLICATION_JSON).content(
				"{\"nom\": \"Bende\",\"prenoms\": \"Kouame Justin\",\"contact\": \"751-458-525\",\"email\": \"bendejustin@gmailcom\",\"password\": \"azerty\"}")
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());

	}	
	
	@Test
	public void loginTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/login/bendejustin@gmail.com/azerty")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());

	}	
	
	@Test
	public void allUserTest() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/allUser")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());

	}	
	
	

}
