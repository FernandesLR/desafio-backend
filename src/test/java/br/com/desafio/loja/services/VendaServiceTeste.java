package br.com.desafio.loja.services;

import br.com.desafio.loja.model.*;
import br.com.desafio.loja.repository.ProdutoRepository;
import br.com.desafio.loja.repository.VendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VendaServiceTeste {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private VendaServices vendaServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRealizarCompraComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setNome("Lucas");
        usuario.setEmail("lucas@email.com");
        usuario.setSenha("senha123");

        Produto produto = new Produto(1L, "Camiseta", 50.0, 10);

        ItemVenda item = new ItemVenda(produto, 2);

        when(produtoRepository.buscarPorId(1L)).thenReturn(produto);

        Venda venda = vendaServices.realizarCompra(usuario, List.of(item));

        assertNotNull(venda);
        assertEquals(1, venda.getItens().size());
        assertEquals(8, produto.getQuantidade()); // estoque reduzido

        verify(produtoRepository, times(1)).cadastrar(produto);
        verify(vendaRepository, times(1)).cadastrar(any(Venda.class));
    }

    @Test
    void retornaErroAoRealizarCompraSeProdutoNaoExiste() {
        Usuario usuario = new Usuario();
        usuario.setNome("Lucas");
        usuario.setEmail("lucas@email.com");
        usuario.setSenha("senha123");

        Produto produto = new Produto(999L, "Produto Inexistente", 50.0, 10);
        ItemVenda item = new ItemVenda(produto, 1);

        when(produtoRepository.buscarPorId(999L)).thenReturn(null);

        Exception exception = assertThrows(IllegalStateException.class, () ->
                vendaServices.realizarCompra(usuario, List.of(item))
        );

        assertEquals("Nenhum item válido para compra", exception.getMessage());
        verify(vendaRepository, never()).cadastrar(any());
    }
}