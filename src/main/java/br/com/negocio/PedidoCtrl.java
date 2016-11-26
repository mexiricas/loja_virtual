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
    private int qdtItens = 0;
    private int qdtTotal = 0;
    private float subtotal = 0;
    private String msg = "";
    private float somaDosProdutos = 0;

    public String actionIntemsInserir(Produto p) {
        if (lsprod.isEmpty()) {
            this.qdtTotal = this.qdtTotal + 1;
            this.qdtItens = this.qdtItens + 1;
            somaDosProdutos = somaDosProdutos + p.getPreco();
            lsprod.add(p);
        } else if (contem(p) == true) {
            if(p.getNome().equalsIgnoreCase(prod.getNome()))
            this.qdtTotal = this.qdtTotal + 1;
            this.qdtItens = this.qdtItens + 1;
            somaDosProdutos = somaDosProdutos + p.getPreco();
        } else {
            this.qdtTotal = this.qdtTotal + 1;
            somaDosProdutos = somaDosProdutos + p.getPreco();
            lsprod.add(p);
        }
        this.subtotal = somaDosProdutos;
        prod = p;

        return "/public/lista_compra?faces-redirect=true";

    }

    public boolean contem(Produto p) {
        for (Produto prod : lsprod) {
            if (p.getNome().equalsIgnoreCase(prod.getNome())) {
                return true;
            }
        }
        return false;
    }

    public String actionIntemsRemover(Produto p) {
        prod = p;
        this.qdtTotal = this.qdtTotal - 1;
        this.subtotal = subtotal - p.getPreco();;
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

    public int getQdtItens() {
        return qdtItens;
    }

    public void setQdtItens(int qdtItens) {
        this.qdtItens = qdtItens;
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

    public float getSomaDosProdutos() {
        return somaDosProdutos;
    }

    public void setSomaDosProdutos(float somaDosProdutos) {
        this.somaDosProdutos = somaDosProdutos;
    }

    public int getQdtTotal() {
        return qdtTotal;
    }

    public void setQdtTotal(int qdtTotal) {
        this.qdtTotal = qdtTotal;
    }

}
