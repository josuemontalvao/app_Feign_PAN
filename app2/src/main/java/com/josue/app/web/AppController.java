package com.josue.app.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
 
import com.josue.app.WorkerFeign;
import com.josue.app.model.Usuario;
 
@RestController
public class AppController {
 
	private final String URI_SUBSTITUIDA_ALL = "/usuarios";
	private final String URI_SUBSTITUIDA_ALL_ID = "/usuarios/{id}";
 
	//COM REST TEMPLATE
	@Value("${api.host.baseurl}")
	private String linkHost;
 
	@Autowired
	private RestTemplate restTemplate;	
 
	@GetMapping("/com/rest/template")
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		Usuario[] usuariosArray = restTemplate.getForObject( linkHost +  URI_SUBSTITUIDA_ALL, Usuario[].class);
		return new ResponseEntity<>(Arrays.asList(usuariosArray), HttpStatus.OK);
	}
	// COM FEIGNS
	@Autowired
	WorkerFeign restTemplateFeign;
 
	@GetMapping("com/feign")
    public  ResponseEntity<List<Usuario>>  getUsersFeign() {
		return ResponseEntity.ok(restTemplateFeign.getAll().getBody());
 
 
 
	}
 
 
//	@GetMapping(URI_USUARIOS_ID)
	@GetMapping("usuarios/{id}")
	public ResponseEntity<Usuario> getById_v1(@PathVariable  long id) {
		Map<String, String> params = new HashMap<>();
		params.put("id", "1");
 
		Usuario usuario = restTemplate.getForObject(linkHost +  URI_SUBSTITUIDA_ALL_ID, Usuario.class, params);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
}
 