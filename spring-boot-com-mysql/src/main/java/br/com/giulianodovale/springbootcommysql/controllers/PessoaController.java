package br.com.giulianodovale.springbootcommysql.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.giulianodovale.springbootcommysql.controllers.dto.PessoaDto;
import br.com.giulianodovale.springbootcommysql.model.Pessoa;
import br.com.giulianodovale.springbootcommysql.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	private final PessoaRepository pessoaRepository;
	
	public PessoaController(PessoaRepository pessoaRepository) {
		super();
		this.pessoaRepository = pessoaRepository;
	}

	@GetMapping("/")
	public List<PessoaDto> findAll() {
		var pessoas = pessoaRepository.findAll();
		return pessoas.stream().map(PessoaDto::converter).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public PessoaDto findById(@PathVariable("id") Long id) {
		var pessoa  = pessoaRepository.getOne(id);
		
		return PessoaDto.converter(pessoa);
	}
	
	@PostMapping("/")
	public void createPerson(@RequestBody PessoaDto pessoa) {
		
		var p  =  new Pessoa();
		p.setNome(pessoa.getNome());
		p.setSobrenome(pessoa.getSobrenome());
		pessoaRepository.save(p);
		
	}
	
	@PutMapping("/{id}")
	public void createPerson(@PathVariable Long id, @RequestBody PessoaDto pessoa) throws Exception {
		
		var p  =  pessoaRepository.findById(id);
		if(p.isPresent()) {
			var pessoaSave = p.get();
			pessoaSave.setNome(pessoa.getNome());
			pessoaSave.setSobrenome(pessoa.getSobrenome());
			pessoaRepository.save(pessoaSave);
		} else {
			throw new Exception("Pessoa não encontrada.");
		}
		
	}
	
}
