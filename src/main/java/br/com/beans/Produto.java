package br.com.beans;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private int id;
    @Column(name = "pro_nome", length = 60, nullable = true)
    private String nome;
    @Column(name = "pro_preco", nullable = true)
    private float preco;
    @Column(name = "pro_foto_nome", length = 80, nullable = true)
    private String pro_foto_nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getPro_foto_nome() {
        return pro_foto_nome;
    }

    public void setPro_foto_nome(String pro_foto_nome) {
        this.pro_foto_nome = pro_foto_nome;
    }
}
