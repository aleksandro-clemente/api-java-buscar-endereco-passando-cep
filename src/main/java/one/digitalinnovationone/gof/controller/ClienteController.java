package one.digitalinnovationone.gof.controller;

import one.digitalinnovationone.gof.model.Cliente;
import one.digitalinnovationone.gof.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public Iterable<Cliente> buscarTodos(){
        return service.buscarTodos();
    }
    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable("idCliente") Long id){
          Cliente cliente = service.buscarPorId(id);
          if(cliente != null){
              return ResponseEntity.ok(cliente);
          }
          return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Cliente cliente){
         service.inserir(cliente);
    }
    @PutMapping("/{idCliente}")
    public void atualizar(@PathVariable("idCliente") Long id,@RequestBody Cliente cliente){
        service.atualizar(id,cliente);
    }
    @DeleteMapping("{idCliente}")
    public ResponseEntity<Cliente> excluir(@PathVariable("idCliente") Long id){
            Cliente cliente = service.buscarPorId(id);
            if(cliente != null){
                service.deletar(cliente.getId());
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
    }


}
