package br.com.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "pedido")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private int ped_id;
    @Column(name = "pes_id")
    private int pes_id;
    @Column(name = "fpg_id", nullable = true)
    private int fpg_id;
    @Column(name = "ped_dataEmissao", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ped_dataEmissao;
    @Column(name = "ped_status", length = 20, nullable = true)
    private String ped_status;
    @Column(name = "ped_dataAutorizacao", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ped_dataAutorizacao;
    @Column(name = "ped_total", nullable = true)
    private float ped_total;
    @Column(name = "ped_desconto", nullable = true)
    private float ped_desconto;

    public int getPed_id() {
        return ped_id;
    }

    public int getPes_id() {
        return pes_id;
    }

    public void setPes_id(int pes_id) {
        this.pes_id = pes_id;
    }

    public void setPed_id(int ped_id) {
        this.ped_id = ped_id;
    }

    public int getFpg_id() {
        return fpg_id;
    }

    public void setFpg_id(int fpg_id) {
        this.fpg_id = fpg_id;
    }

    public Date getPed_dataEmissao() {
        return ped_dataEmissao;
    }

    public void setPed_dataEmissao(Date ped_dataEmissao) {
        this.ped_dataEmissao = ped_dataEmissao;
    }

    public String getPed_status() {
        return ped_status;
    }

    public void setPed_status(String ped_status) {
        this.ped_status = ped_status;
    }

    public Date getPed_dataAutorizacao() {
        return ped_dataAutorizacao;
    }

    public void setPed_dataAutorizacao(Date ped_dataAutorizacao) {
        this.ped_dataAutorizacao = ped_dataAutorizacao;
    }

    public float getPed_total() {
        return ped_total;
    }

    public void setPed_total(float ped_total) {
        this.ped_total = ped_total;
    }

    public float getPed_desconto() {
        return ped_desconto;
    }

    public void setPed_desconto(float ped_desconto) {
        this.ped_desconto = ped_desconto;
    }

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItensPedidos> itens = new ArrayList<ItensPedidos>();

    public List<ItensPedidos> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedidos> itens) {
        this.itens = itens;
    }

}
