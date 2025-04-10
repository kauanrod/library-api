package kauanrod.libraryapi.repository;

import kauanrod.libraryapi.model.Autor;
import kauanrod.libraryapi.model.GeneroLivro;
import kauanrod.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query method

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    // JPQL -> Referencia as entidades e as propriedades
    @Query("select liv from Livro liv order by liv.titulo")
    List<Livro> listarTodos();

    @Query("select aut from Livro liv inner join liv.autor aut")
    List<Autor> listarAutoresPorLivro();

    // Named parameters
    @Query("""
                    select liv 
                    from Livro liv 
                    where liv.genero = :genero
            """)
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro);

    // Positional parameters
    @Query("""
                    select liv 
                    from Livro liv 
                    where liv.genero = ?1
            """)
    List<Livro> findByGeneroPositionalParameters(GeneroLivro generoLivro);
}
