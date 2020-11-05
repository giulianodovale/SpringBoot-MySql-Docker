package br.com.giulianodovale.springbootcommysql.controllers.dto;

import br.com.giulianodovale.springbootcommysql.model.Pessoa;

public class PessoaDto {
	
	private long id ;
	private String nome;
	private String sobrenome;
	
	public static PessoaDto converter(Pessoa p) {
		var pessoa = new PessoaDto();
		pessoa.setId(p.getId());
		pessoa.setNome(p.getNome());
		pessoa.setSobrenome(p.getSobrenome());
		return pessoa;	
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	

}
