package kauanrod.libraryapi.service;

import kauanrod.libraryapi.model.Autor;
import kauanrod.libraryapi.model.GeneroLivro;
import kauanrod.libraryapi.model.Livro;
import kauanrod.libraryapi.repository.AutorRepository;
import kauanrod.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {
    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository.findById(UUID.fromString("440cd4eb-9208-4731-8e78-77fb10f142a1")).orElse(null);
        livro.setDataPublicacao(LocalDate.of(2025,7, 26));
//        livroRepository.save(livro); // Desnecessario

    }

    @Transactional
    public void executar() {
        Autor autor = new Autor();
        autor.setNome("J.K. Rowling");
        autor.setNacionalidade("Britânica");
        autor.setDataNascimento(LocalDate.of(1965, 7, 31));
        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Harry Potter");
        livro.setDataPublicacao(LocalDate.of(1997, 6, 26));
        livro.setAutor(autor);
        livroRepository.save(livro);

        if (!autor.getNome().equals("J.K. Rowling")) {
            throw new RuntimeException("Nome rollback");
        }
    }

}
