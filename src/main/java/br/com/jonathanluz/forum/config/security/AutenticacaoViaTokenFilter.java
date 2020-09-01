package br.com.jonathanluz.forum.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.jonathanluz.forum.model.entity.Usuario;
import br.com.jonathanluz.forum.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToke(request);

		boolean valido = tokenService.isTokenValido(token);

		if (valido) {
			autenticarCliente(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		if (usuario.isPresent()) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.get(),
					null, usuario.get().getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String recuperarToke(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7);
	}

}
