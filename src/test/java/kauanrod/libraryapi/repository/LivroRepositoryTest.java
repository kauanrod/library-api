package kauanrod.libraryapi.repository;

import kauanrod.libraryapi.model.Autor;
import kauanrod.libraryapi.model.GeneroLivro;
import kauanrod.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
        livro.setGenero(GeneroLivro.CIENCIA);
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

    @Test
    public void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("b684c539-bb63-4245-afe3-736f35085373");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("7b872bac-7f56-45fb-97c3-54382dc59afa");
        Autor novoAutor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(novoAutor);
        repository.save(livroParaAtualizar);
    }

    @Test
    public void deletar() {
        UUID id = UUID.fromString("1aa1e09a-ed8b-4d00-815e-b3858cb7fc44");
        repository.deleteById(id);
    }

    @Test
    public void deletarCascade() {
        UUID id = UUID.fromString("6bd570e5-9185-45eb-b8c1-46d71b9ca13e");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    public void buscarLivroTest() {
        UUID id = UUID.fromString("b684c539-bb63-4245-afe3-736f35085373");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println(livro.getTitulo());
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    public void pesquisaPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("Senhor dos Anéis");
        lista.forEach(System.out::println);
    }

    @Test
    public void listarTodosOsLivros() {
        var resultado = repository.listarTodos();
        resultado.forEach(System.out::println);
//        System.out.println(resultado);
    }

    @Test
    public void listarAutorPorLivro() {
        var resultado = repository.listarAutoresPorLivro();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarLivrosPorGenero() {
        var resultado = repository.findByGenero(GeneroLivro.FANTASIA);
        resultado.forEach(System.out::println);
    }

    @Test
    public void listarLivrosPorGeneroPositionalParameters() {
        var resultado = repository.findByGeneroPositionalParameters(GeneroLivro.FANTASIA);
        resultado.forEach(System.out::println);
    }

    @Test
    public void deleteByGenero() {
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    public void updateDataPublicacao() {
        repository.updateDataPublicacao(LocalDate.of(2000,1,1));
    }
}