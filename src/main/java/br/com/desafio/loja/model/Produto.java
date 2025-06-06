package br.com.desafio.loja.model;

public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto() {}

    public Produto(Long id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }



    public String getNome() {
        return nome;
    }



    public double getPreco() {
        return preco;
    }



    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public boolean temEstoque(int quantidadeDesejada) {
        return this.quantidade >= quantidadeDesejada;
    }

    public void reduzirEstoque(int quantidadeVendida) {
        if (temEstoque(quantidadeVendida)) {
            this.quantidade -= quantidadeVendida;
        } else {
            System.out.println("Estoque insuficiente.");
        }
    }

}
