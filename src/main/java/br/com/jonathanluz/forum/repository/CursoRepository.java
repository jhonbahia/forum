package br.com.jonathanluz.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonathanluz.forum.model.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	public Curso findByNome(String nomeCurso);

}
