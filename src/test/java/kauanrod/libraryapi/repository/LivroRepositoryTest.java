package kauanrod.libraryapi.repository;

import kauanrod.libraryapi.model.Autor;
import kauanrod.libraryapi.model.GeneroLivro;
import kauanrod.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Senhor dos Anéis");
        livro.setDataPublicacao(LocalDate.of(1950, 1, 2));

        Autor autor = autorRepository.
                findById(UUID.fromString("27780949-4d44-4811-b233-18319267190c")).
                orElse(null);
        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    public void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Senhor dos Anéis");
        livro.setDataPublicacao(LocalDate.of(1950, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Kauan");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2001, 12, 17));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    public void salvarAutorLivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Harry Potter");
        livro.setDataPublicacao(LocalDate.of(1997, 6, 26));

        Autor autor = new Autor();
        autor.setNome("J.K. Rowling");
        autor.setNacionalidade("Britânica");
        autor.setDataNascimento(LocalDate.of(1965, 7, 31));
        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }
}