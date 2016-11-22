package br.com.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.beans.ItensPedidos;
import br.com.beans.Pedidos;
import br.com.beans.Produto;
import br.com.persistencia.ProdutoDAO;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PedidoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItensPedidos> iten = new ArrayList<>();
    private Pedidos ped = new Pedidos();
    private Produto prod = new Produto();
    private int qdt = 1;
    private float subtotal = 0;
    private String msg = "";

    public String actionIntemsInserir(Produto p) {
        prod = p;
        ItensPedidos it = new ItensPedidos();
        it.setPed(this.ped);
        it.setPro_id(prod.getId());
        it.setIpe_qtde(this.qdt);
        it.setIpe_valorUnit(prod.getPreco());
        it.setIpe_subtotal(prod.getPreco());
        this.ped.getItens().add(it);
        msg =("Adicionado a Lista de Compras "
                        + prod.getNome());
        return "/public/index?faces-redirect=true";

    }
    public void actionPedido(){
    	
    }

    // GETTERS SETTER
    public List<ItensPedidos> getIten() {
        return iten;
    }

    public void setIten(List<ItensPedidos> iten) {
        this.iten = iten;
    }

    public Pedidos getPed() {
        return ped;
    }

    public void setPed(Pedidos ped) {
        this.ped = ped;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    public int getQdt() {
        return qdt;
    }

    public void setQdt(int qdt) {
        this.qdt = qdt;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
