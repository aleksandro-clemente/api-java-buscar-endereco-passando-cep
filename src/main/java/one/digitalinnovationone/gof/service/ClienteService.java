package one.digitalinnovationone.gof.service;

import one.digitalinnovationone.gof.model.Cliente;

import java.util.List;
public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    void inserir(Cliente cliente);
    void deletar(Long id);
    void atualizar(Long id, Cliente cliente);
}
