package one.digitalinnovationone.gof.service;

import one.digitalinnovationone.gof.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
    @GetMapping("/{cep}/json")
    Endereco consultarEndereco(@PathVariable("cep") String cep);
}
