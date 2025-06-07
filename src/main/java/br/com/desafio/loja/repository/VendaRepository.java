package br.com.desafio.loja.repository;

import br.com.desafio.loja.model.Venda;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VendaRepository {
    private List<Venda> vendas = new ArrayList<>();

    public void cadastrar(Venda venda) {
        vendas.add(venda);
    }

    public List<Venda> listarTodas() {
        return vendas;
    }
}
