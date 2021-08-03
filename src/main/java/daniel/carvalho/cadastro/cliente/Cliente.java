package daniel.carvalho.cadastro.cliente;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table
public class Cliente {
    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    @Id
    @SequenceGenerator(
            name = "cliente_sequence",
            sequenceName = "cliente_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cliente_sequence"
    )

    private  Long matricula;
    private  String nome;
    private  String identificador;

    //Identifica o modelo de cadastro nacional,
    // 1 - CPF
    // 2 - CNPJ
    private  int tipoIdentificador;


    public Cliente(String nome, String identificador, int tipoIdentificador) {
        this.nome = nome;
        this.identificador = identificador;
        this.tipoIdentificador = tipoIdentificador;
    }

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getTipoIdentificador() {
        return tipoIdentificador;
    }

    public void setTipoIdentificador(int tipoIdentificador) {
        this.tipoIdentificador = tipoIdentificador;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", identificador='" + identificador + '\'' +
                ", tipoIdentificador=" + tipoIdentificador +
                '}';
    }
}
