package br.com.desafio.loja.model;

import java.util.List;

public class Venda {
    private Long id;
    private Usuario usuario;
    private List<ItemVenda> itens;

    public Venda() {}

    public Venda(Long id, Usuario usuario, List<ItemVenda> itens) {
        this.id = id;
        this.usuario = usuario;
        this.itens = itens;
    }

    // Getters e Setters
}
