package br.com.desafio.loja;

import br.com.desafio.loja.model.*;
import br.com.desafio.loja.repository.ProdutoRepository;
import br.com.desafio.loja.services.UsuarioServices;
import br.com.desafio.loja.services.VendaServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Simulacao implements CommandLineRunner {

    private final UsuarioServices usuarioService;
    private final VendaServices vendaService;
    private final ProdutoRepository produtoRepository;

    public Simulacao(UsuarioServices usuarioService,
                     VendaServices vendaService,
                     ProdutoRepository produtoRepository) {
        this.usuarioService = usuarioService;
        this.vendaService = vendaService;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Usuario usuario = criarUsuario(1L, "Maria", "maria@gmail.com", "senha123", "123.456.789-00");
        Usuario usuario2 = criarUsuario(2L, "Roberto", "roberto@hotmail.com", "123456", "243.426.749-00");


        Produto teclado = criarProduto(1L, "Teclado", 22.50, 10);
        Produto mouse = criarProduto(2L, "Mouse", 100.00, 55);


        List<ItemVenda> itensMaria = new ArrayList<>();
        itensMaria.add(new ItemVenda(teclado, 2));
        itensMaria.add(new ItemVenda(mouse, 1));

        List<ItemVenda> itensRoberto = new ArrayList<>();
        itensRoberto.add(new ItemVenda(mouse, 3));

        // Processa vendas
        processarVenda(usuario, itensMaria);
        processarVenda(usuario2, itensRoberto);

        // Exibe resumo
        vendaService.exibirResumoVendas();
    }

    private Usuario criarUsuario(Long id, String nome, String email, String senha, String cpf) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setCpf(cpf);

        try {
            usuarioService.salvarUsuario(usuario);
            System.out.println("Usuário " + nome + " cadastrado com sucesso!");
            return usuario;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar usuário " + nome + ": " + e.getMessage());
            return null;
        }
    }

    private Produto criarProduto(Long id, String nome, double preco, int quantidade) {
        Produto produto = new Produto(id, nome, preco, quantidade);
        produtoRepository.cadastrar(produto);
        return produto;
    }

    private void processarVenda(Usuario usuario, List<ItemVenda> itens) {
        if (usuario == null) return;

        try {
            vendaService.realizarCompra(usuario, itens);
            System.out.println("Venda para " + usuario.getNome() + " processada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro na venda para " + usuario.getNome() + ": " + e.getMessage());
        }
    }
}