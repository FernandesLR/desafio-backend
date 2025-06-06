package br.com.desafio.loja.model;

public class ItemVenda {
    private Produto produto;
    private int quantidade;

    public ItemVenda() {}

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Getters e Setters

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        if (produto == null) {
            return 0.0;
        }
        return produto.getPreco() * quantidade;
    }
}