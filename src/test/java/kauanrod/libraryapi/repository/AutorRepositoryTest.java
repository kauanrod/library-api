package kauanrod.libraryapi.repository;

import kauanrod.libraryapi.model.Autor;
import kauanrod.libraryapi.model.GeneroLivro;
import kauanrod.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired AutorRepository repository;
    @Autowired LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Jose Robert");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1983, 12, 1));
        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("27780949-4d44-4811-b233-18319267190c");
        Optional<Autor> optionalAutor = repository.findById(id);
        if (optionalAutor.isPresent()) {
            Autor autor = optionalAutor.get();
            System.out.println("Dados do autor: " + autor);

            autor.setNome("Maya");
            autor.setNacionalidade("Brasileira");
            autor.setDataNascimento(LocalDate.of(2012, 7, 26));
            repository.save(autor);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> listaAutor = repository.findAll();
        listaAutor.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deleteByIdTest() {
        var id = UUID.fromString("27780949-4d44-4811-b233-18319267190c");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("ad03d852-a5fa-4d39-84e8-a9ab5879f08c");
        var autor = repository.findById(id).get();
        repository.delete(autor);
    }

    @Test
    public void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Luiza");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2012, 8, 1));

        Livro livro = new Livro();
        livro.setIsbn("98807-29229");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Cosmos");
        livro.setDataPublicacao(LocalDate.of(2016, 2, 28));
        livro.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }
}
