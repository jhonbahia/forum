package br.com.jonathanluz.forum.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.jonathanluz.forum.model.entity.Topico;
import br.com.jonathanluz.forum.repository.TopicoRepository;

public class AtualizaoTopicoForm {
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Length(min = 10)
	private String mensagem;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id);
		
		topico.setTitulo(titulo);
		topico.setMensagem(mensagem);
	
		return topico;
	}
	
}
