package br.com.blog.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blog.builder.ProdutoBuilder;
import br.com.blog.model.orm.Produto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("integration-tests")
public class ProdutoServiceTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;
	
	private MockMvc mockMvc;
	private String uri="/service/produto/";
	
	private List<Produto> getProdutos(final Integer qtde) throws Exception{
		return new ProdutoBuilder().add(qtde).buildAll();
	}
	
	private Produto getProdutos() throws Exception{
		return new ProdutoBuilder().add(1).buildOne();
	}	
	
	private String toJson(final Object obj) throws Exception {
		return mapper.writeValueAsString(obj);
	}
	
	private String getProdutoAsJson() throws Exception{
		return toJson(getProdutos());
	}	

	@Test
	public void post() throws Exception {
		int qtdeProdutos = 5;
		
		for (Produto produto : getProdutos(qtdeProdutos)) {
			String json = toJson(produto);
			mockMvc = webAppContextSetup(context).build();
			this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().is2xxSuccessful());
		}

	}
	
	@Test
	public void put() throws Exception{
		String json = getProdutoAsJson();
		this.mockMvc = webAppContextSetup(context).build();
		this.mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getAll() throws Exception {
		mockMvc = webAppContextSetup(context).build();
		final MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	public void getOne() throws Exception {
		mockMvc = webAppContextSetup(context).build();
		final MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get(uri+"1") //.param("id", "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void delete() throws Exception{
		mockMvc = webAppContextSetup(context).build();
		final MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.delete(uri+"1") //.param("id", "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

}
