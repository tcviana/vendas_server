package br.com.blog.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blog.builder.PedidoBuilder;
import br.com.blog.model.orm.Pedido;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("integration-tests")
public class PedidoServiceTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper mapper;
	
	private MockMvc mockMvc;
	private String uri="/service/pedido/";
	
	private Pedido getPedido() {
		return new PedidoBuilder().add(1).addItem().buildOne();
	}
	
	private String toJson(final Object obj) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}
	
	private String getPedidoAsJson() throws JsonProcessingException {
		return toJson(getPedido());
	}
	
	@Test
	public void post() throws Exception {
		String json = getPedidoAsJson();
		mockMvc = webAppContextSetup(context).build();
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void put() throws Exception{
		String json = getPedidoAsJson();
		mockMvc = webAppContextSetup(context).build();
		mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void getOne() throws Exception{
		mockMvc = webAppContextSetup(context).build();
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri+"1") //.param("id", "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void getAll() throws Exception{
		mockMvc = webAppContextSetup(context).build();
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void delete() throws Exception{
		mockMvc = webAppContextSetup(context).build();
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(uri+"1") //.param("id", "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}
