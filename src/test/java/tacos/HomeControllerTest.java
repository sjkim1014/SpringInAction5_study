package tacos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc; // 테스트를 위한 모의 매커니즘

	@Test
	public void testHomePage() throws Exception {
		
		mockMvc.perform(get("/")).andExpect(status().isOk())
								 .andExpect(view().name("home"))
								 .andExpect(content().string(containsString("Welcome to...")));
	}

}
