package br.com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItensPedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipe_id")
    private int ipe_id;
    @Column(name = "ped_id")
    private int ped_id;
    @Column(name = "pro_id")
    private int pro_id;
    @Column(name = "ipe_qtde")
    private float ipe_qtde;
    @Column(name = "ipe_valorUnit")
    private float ipe_valorUnit;
    @Column(name = "ipe_subtotal")
    private float ipe_subtotal;

    public int getIpe_id() {
        return ipe_id;
    }

    public void setIpe_id(int ipe_id) {
        this.ipe_id = ipe_id;
    }

    public int getPed_id() {
        return ped_id;
    }

    public void setPed_id(int ped_id) {
        this.ped_id = ped_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public float getIpe_qtde() {
        return ipe_qtde;
    }

    public void setIpe_qtde(float ipe_qtde) {
        this.ipe_qtde = ipe_qtde;
    }

    public float getIpe_valorUnit() {
        return ipe_valorUnit;
    }

    public void setIpe_valorUnit(float ipe_valorUnit) {
        this.ipe_valorUnit = ipe_valorUnit;
    }

    public float getIpe_subtotal() {
        return ipe_subtotal;
    }

    public void setIpe_subtotal(float ipe_subtotal) {
        this.ipe_subtotal = ipe_subtotal;
    }

    @ManyToOne
    @JoinColumn(name = "ped_id")
    private Pedidos ped;

    public Pedidos getPed() {
        return ped;
    }

    public void setPed(Pedidos ped) {
        this.ped = ped;
    }

}
