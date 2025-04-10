package kauanrod.libraryapi.repository;

import kauanrod.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {
    @Autowired
    TransacaoService service;

    @Test
    public void transacaoSimples() {
        service.executar();
    }

    @Test
    public void transacaoEstadoManaged() {
        service.atualizacaoSemAtualizar();
    }
}
