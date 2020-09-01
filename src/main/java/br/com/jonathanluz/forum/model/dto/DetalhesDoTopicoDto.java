package br.com.jonathanluz.forum.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jonathanluz.forum.model.entity.StatusTopico;
import br.com.jonathanluz.forum.model.entity.Topico;

public class DetalhesDoTopicoDto extends TopicoDto {

	private String nomeAutor;
	private StatusTopico status;
	private List<RespostaDto> respostas;
	
	public DetalhesDoTopicoDto(Topico topico) {
		super(topico);
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = new ArrayList<RespostaDto>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDto> getRespostas() {
		return respostas;
	}
	
	

}
