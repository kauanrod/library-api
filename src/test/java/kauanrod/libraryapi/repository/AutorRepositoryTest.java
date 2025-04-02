package kauanrod.libraryapi.repository;

import kauanrod.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Júlia");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2003, 7, 19));
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
}
