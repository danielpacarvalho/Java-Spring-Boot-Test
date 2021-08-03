package daniel.carvalho.cadastro.cliente;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Configuration
@EnableWebMvc
public class ClienteConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClienteRepository repository){
        return args -> {
          Cliente thais =  new Cliente(
                    "Thais",
                    "900.035.603-25",
                    1
            );
            Cliente daniel =  new Cliente(
                    "Daniel",
                    "920.931.593-68",
                    1
            );

            repository.saveAll(List.of(thais,daniel));
        };
    }
}
