package br.com.jonathanluz.forum.config.validation.dto;

public class ErroDeFormularioDto {

	private String campo;
	private String erro;

	public ErroDeFormularioDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}