package br.com.treinaweb.cleancodesolid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.treinaweb.cleancodesolid.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(
        """
        SELECT
            count(*) > 0
        FROM
            Livro l
        WHERE
            l.titulo = :titulo
        """
    )
    Boolean isTituloExists(String titulo);

}
