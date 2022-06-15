package one.digitalinnovationone.gof.service.impl;

import one.digitalinnovationone.gof.model.Cliente;
import one.digitalinnovationone.gof.model.ClienteRepository;
import one.digitalinnovationone.gof.model.Endereco;
import one.digitalinnovationone.gof.model.EnderecoRepository;
import one.digitalinnovationone.gof.service.ClienteService;
import one.digitalinnovationone.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCep;
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
         Optional<Cliente> cliente = clienteRepository.findById(id);
         return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }
    @Override
    public void atualizar(Long id,Cliente cliente){
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            System.out.println("Entrou aqui");
            salvarClienteComCep(cliente);
        }
    }
    @Override
    public void deletar(Long id) {
         clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{
            Endereco novoEndereco = viaCep.consultarEndereco(cep);
            System.out.println(novoEndereco.getLogradouro());
            enderecoRepository.save(novoEndereco);
            System.out.println("Entrou aqui!!!");
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
