package br.com.desafio.loja.services;

import br.com.desafio.loja.model.*;
import br.com.desafio.loja.repository.ProdutoRepository;
import br.com.desafio.loja.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VendaServices {
    private static Long nextVendaId = 1L; // Contador para IDs de venda

    private final ProdutoRepository produtoRepository;
    private final VendaRepository vendaRepository;

    public VendaServices(ProdutoRepository produtoRepository,
                         VendaRepository vendaRepository) {
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
    }

    public Venda realizarCompra(Usuario usuario, List<ItemVenda> itensSolicitados) {


        List<ItemVenda> itensValidados = new ArrayList<>();

        for (ItemVenda item : itensSolicitados) {
            Optional<Produto> produtoOpt = Optional.ofNullable(produtoRepository.buscarPorId(item.getProduto().getId()));

            if (produtoOpt.isEmpty()) {
                System.out.println("Produto não encontrado: ID " + item.getProduto().getId());
                continue;
            }

            Produto produto = produtoOpt.get();

            if (!produto.temEstoque(item.getQuantidade())) {
                System.out.println("Estoque insuficiente para: " + produto.getNome() +
                        " (Quantidade disponível: " + produto.getQuantidade() + ")");
                continue;
            }

            produto.reduzirEstoque(item.getQuantidade());
            produtoRepository.cadastrar(produto);

            // Cria novo ItemVenda com preço atual do produto
            ItemVenda itemValidado = new ItemVenda(produto, item.getQuantidade());
            itensValidados.add(itemValidado);
        }

        if (itensValidados.isEmpty()) {
            throw new IllegalStateException("Nenhum item válido para compra");
        }

        // Cria venda com ID sequencial
        Venda venda = new Venda(nextVendaId++, usuario, itensValidados);
        vendaRepository.cadastrar(venda);
        return venda;
    }

    public void exibirResumoVendas() {
        List<Venda> vendas = vendaRepository.listarTodas();

        if (vendas.isEmpty()) {
            System.out.println("\nNenhuma venda realizada até o momento");
            return;
        }

        System.out.println("\n=== RESUMO DE VENDAS ===");
        System.out.printf("Total de vendas: %d%n", vendas.size());

        vendas.forEach(venda -> {
            System.out.println("\nVenda ID: " + venda.getId());
            System.out.println("Cliente: " + venda.getUsuario().getNome());
            System.out.println("Itens:");

            venda.getItens().forEach(item -> {
                System.out.printf("- %-15s %3d x R$%7.2f = R$%7.2f%n",
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getProduto().getPreco(),
                        item.getSubtotal());
            });

            System.out.printf("%28sR$%7.2f%n", "Total da venda: ", venda.calcularTotal());
            System.out.println("----------------------");
        });
    }
}