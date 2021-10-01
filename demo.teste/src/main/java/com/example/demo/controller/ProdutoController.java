package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class ProdutoController {

	
	@Autowired
	private ProdutoRepository repository;
	
	
	@GetMapping
	public ResponseEntity<List<Produto>> GetALL (){
		return ResponseEntity.ok(repository.findAll());
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(titulo));
		
	}
	
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
}
