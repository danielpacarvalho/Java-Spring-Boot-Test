package daniel.carvalho.cadastro.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    Optional<Cliente> findClienteByNome(String nome);
    Optional<Cliente> findClienteByIdentificador(String identificador);
}
