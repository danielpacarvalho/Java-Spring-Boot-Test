package daniel.carvalho.cadastro.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> getCliente(){
        return clienteRepository.findAll();

    }

    public void addNewCliente(@org.jetbrains.annotations.NotNull Cliente cliente) {
    Optional<Cliente> clienteByNome = clienteRepository.findClienteByNome(cliente.getNome());
    if (clienteByNome.isPresent()){
        throw new IllegalStateException("TODO - (nome jah existe)");
    }

    //Check - identificador informado
    if (cliente.getIdentificador().isEmpty()){
        throw new IllegalStateException("Identificador não informado");
    }

    //Eliminar espaços em branco e simbolos
        String id = cliente.getIdentificador().trim();
        id = id.replace(".","");
        id = id.replace("/","");
        id = id.replace("-","");


        // Check - é um identificador válido
    if (id.length() != 12 && id.length() != 14){
        throw new IllegalStateException("Identificador inválido");
    }

    //Check - identificador é um CPF ou CNPJ valido
        if (cliente.getIdentificador().length() == 12){
            if(checkCPF(id)){
                cliente.setIdentificador(id);
                cliente.setTipoIdentificador(1);
            } else {
                throw new IllegalStateException("CPF inválido");
            }

        }
        if (cliente.getIdentificador().length() == 14){
            if (checkCNPJ(id)) {
                cliente.setIdentificador(id);
                cliente.setTipoIdentificador(2);
            }else {
                throw new IllegalStateException("CNPJ inválido");
            }
        }

    // Sava - Novo cliente
    clienteRepository.save(cliente);
    }

    private boolean checkCNPJ(String cnpj) {
        final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};


        Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());

    }

    // checkCPF testa se o cpf informado é valido
    private boolean checkCPF(String cpf) {
        final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    private static String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }
}
