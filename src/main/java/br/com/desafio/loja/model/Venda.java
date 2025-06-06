package br.com.desafio.loja.model;

import java.util.List;

public class Venda {
    private Long id;
    private Usuario usuario;
    private List<ItemVenda> itens;


    public Venda() {}

    public Venda(Usuario usuario, List<ItemVenda> itens) {
        this.usuario = usuario;
        this.itens = itens;
    }

    public Venda(Long id, Usuario usuario, List<ItemVenda> itens) {
        this.id = id;
        this.usuario = usuario;
        this.itens = itens;
    }

    // Getters e Setters


    public double calcularTotal() {
        if (itens == null) {
            return 0.0;
        }
        return itens.stream()
                .mapToDouble(item -> item != null ? item.getSubtotal() : 0.0)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venda realizada por: ").append(usuario.getNome()).append("\n");
        sb.append("Itens:\n");
        for (ItemVenda item : itens) {
            sb.append("- ").append(item.getProduto().getNome())
                    .append(" | Quantidade: ").append(item.getQuantidade())
                    .append(" | Subtotal: R$").append(item.getSubtotal())
                    .append("\n");
        }
        sb.append("Total: R$").append(calcularTotal());
        return sb.toString();
    }

    public Long getId() {
        return this.id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public List<ItemVenda> getItens() {
        return this.itens;
    }
}
