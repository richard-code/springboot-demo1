package com.study;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Bootstrap.class)
@WebAppConfiguration
public class DemoApplicationTests {

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp(){
//		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	private void printAll(MockMvc mvc) throws Exception {
		RequestBuilder request = null;
		request = get("/user/getAll");
		String result = mvc.perform(request)
				.andReturn()
				.getResponse()
				.getContentAsString();
		System.out.println(result);
	}

	@Test
	public void test1() throws Exception {
		RequestBuilder request = null;
		request = get("/user/getAll");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
		MvcResult mvcResult = mvc.perform(request).andReturn();
	}

	@Test
	public void test2() throws Exception {
		RequestBuilder request = null;
		request = post("/user/add")
				.param("id", "1")
				.param("name", "tom")
				.param("age", "10");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));
		printAll(mvc);
	}

	@Test
	public void test3() throws Exception {
		RequestBuilder request = null;
		request = get("/api/user/findByName")
				.param("userName", "zhangsan");
		mvc.perform(request)
				.andExpect(status().isOk());
	}

	@Test
	public void test4() throws Exception {
		RequestBuilder request = null;
		request = post("/api/user/saveUser")
				.param("userName", "tom")
				.param("cityName", "beijing");
		mvc.perform(request)
				.andExpect(status().isOk());
	}

}
