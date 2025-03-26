package leeds.produtos.produtos.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_prod")
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "status")
    private String status;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private String valor;

    public Produtos() {


    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


    public Produtos(UUID id, String status, String nome, String valor) {
        this.id = id;
        this.status = status;
        this.nome = nome;
        this.valor = valor;
    }
}
