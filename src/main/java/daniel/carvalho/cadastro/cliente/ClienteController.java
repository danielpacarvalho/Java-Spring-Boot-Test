package daniel.carvalho.cadastro.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }


    @GetMapping
    public List<Cliente> getCliente(){
        return clienteService.getCliente();
    }

    @PostMapping
    public void cadastroCliente(@RequestBody Cliente cliente){
        clienteService.addNewCliente(cliente);
    }

    @DeleteMapping(path = "{clienteMatricula")
    public void deleteCliente(@PathVariable("matricula") Long matricula){
        /* TODO */
    }
}
