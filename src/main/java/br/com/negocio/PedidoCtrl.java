package br.com.negocio;

import br.com.beans.FormaPgto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.beans.ItensPedidos;
import br.com.beans.Pedidos;
import br.com.beans.Pessoa;
import br.com.beans.Produto;
import br.com.persistencia.ItensPedidosDao;
import br.com.persistencia.PedidoDao;
import br.com.persistencia.PessoaDao;
import br.com.persistencia.ProdutoDAO;
import java.util.Date;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@ManagedBean
@SessionScoped
public class PedidoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;

    private ItensPedidos iten = new ItensPedidos();
    private FormaPgto fmpg = new FormaPgto();
    private List<Produto> lsprod = new ArrayList<>();
    private List<Produto> lsprodtela = new ArrayList<>();
    private Pedidos ped = new Pedidos();
    private Produto prod = new Produto();
    private int qdtItens = 0;
    private int qdtTotal = 0;
    private float subtotal = 0;
    private String msg = "";
    private float somaDosProdutos = 0;

    public String actionIntemsInserir(Produto p) {

//        ped.getItens().add(iten); 
        lsprodtela = getListatela(p);
        somaDosProdutos = somaDosProdutos + p.getPreco();
        lsprod.add(p);
        this.qdtTotal = lsprod.size();
        this.subtotal = somaDosProdutos;

        prod = p;

        return "/public/lista_compra?faces-redirect=true";

    }

    public List<Produto> getListatela(Produto p) {
        if (lsprod.isEmpty()) {
            lsprodtela.add(p);
        } else if (!p.getNome().equalsIgnoreCase(prod.getNome())) {
            lsprodtela.add(p);
        }
        return lsprodtela;
    }

    public String actionIntemsRemover(Produto p) {
        prod = p;
        this.qdtTotal = this.qdtTotal - 1;
        this.subtotal = subtotal - p.getPreco();;
        lsprod.remove(p);
        return "/public/lista_compra?faces-redirect=true";

    }

    public String actionPedido() {
        Date data = new Date(System.currentTimeMillis());
        Pessoa pes = PessoaDao.pesqUsuario(getUsuarioLogado());
        ped.setFormaPgto(fmpg); 
        ped.setPessoa(pes); 
        ped.setPed_dataAutorizacao(data);
        ped.setPed_dataEmissao(data);
        ped.setPed_status("ABERTO");
        ped.setPed_total(subtotal);
        PedidoDao.inserir(ped);

        for (Produto prod : lsprod) {
            iten = new ItensPedidos();
            iten.setProd(prod);
            iten.setIpe_qtde(qdtTotal);
            iten.setIpe_subtotal(subtotal);
            iten.setIpe_valorUnit(prod.getPreco());
            iten.setProd(prod);
            iten.setPed(ped);
            ItensPedidosDao.inserir(iten);
        }

        return "/cliente/cadastrar_pedido?faces-redirect=true";
    }

    public String getUsuarioLogado() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    public ItensPedidos getIten() {
        return iten;
    }

    // GETTERS SETTER
    public void setIten(ItensPedidos iten) {
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

    public List<Produto> getLsprodtela() {
        return lsprodtela;
    }

    public void setLsprodtela(List<Produto> lsprodtela) {
        this.lsprodtela = lsprodtela;
    }

    public FormaPgto getFmpg() {
        return fmpg;
    }

    public void setFmpg(FormaPgto fmpg) {
        this.fmpg = fmpg;
    }

}
