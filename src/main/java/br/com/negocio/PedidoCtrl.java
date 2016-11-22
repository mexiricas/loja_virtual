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
    private List<Produto> lsprod = new ArrayList<>();
    private Pedidos ped = new Pedidos();
    private Produto prod = new Produto();
    private int qdt = 0;
    private float subtotal = 0;
    private String msg = "";
    private float soma = 0;

    public String actionIntemsInserir(Produto p) {
        this.prod = p;
        this.qdt = this.qdt + 1;
        soma = soma + p.getPreco();
        this.subtotal = soma;
        lsprod.add(p);
        return "/public/lista_compra?faces-redirect=true";

    }

    public String actionIntemsRemover(Produto p) {
        prod = p;
        this.qdt = this.qdt - 1;
        soma = soma - p.getPreco();
        this.subtotal = soma;
        lsprod.remove(p);
        return "/public/lista_compra?faces-redirect=true";

    }

    public void actionPedido() {

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

    public List<Produto> getLsprod() {
        return lsprod;
    }

    public void setLsprod(List<Produto> lsprod) {
        this.lsprod = lsprod;
    }

    public float getSoma() {
        return soma;
    }

    public void setSoma(float soma) {
        this.soma = soma;
    }

}
