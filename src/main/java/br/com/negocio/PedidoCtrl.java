package br.com.negocio;

import br.com.beans.Cidades;
import br.com.beans.Estados;
import br.com.beans.Fone;
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
import br.com.persistencia.CidadesDao;
import br.com.persistencia.FormaPgtoDAO;
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
    private Pessoa pessoa = new Pessoa();
    private Estados estado;
    private List<Produto> lsprod = new ArrayList<>();
    private List<Produto> lsprodtela = new ArrayList<>();
    private Pedidos ped = new Pedidos();
    private Produto prod = new Produto();
    private List<FormaPgto> forpgt;
    private FormaPgto formaPgto;
    private Cidades cidade;
    private List<Integer> lsint = new ArrayList<>();
    private List<Cidades> cidades;
    private List<Estados> estados;
    private String img_nome = "";
    private int qdtItens = 0;
    private int qdtTotal = 0;
    private int validade = 0;
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
        this.subtotal = subtotal - p.getPreco();
        lsprod.remove(p);
        if (!contem(p)) {
            lsprodtela.remove(p);
        }
        return "/public/lista_compra?faces-redirect=true";

    }

    public boolean contem(Produto p) {
        for (Produto pr : lsprod) {
            if (p.getNome().equalsIgnoreCase(pr.getNome())) {
                return true;
            }
        }
        return false;
    }

    public String actionPedido() {

        Date data = new Date(System.currentTimeMillis());
        Pessoa pes = PessoaDao.pesqUsuario(getUsuarioLogado());
        ped.setFormaPgto(formaPgto);
        ped.setPessoa(pes);
        ped.setPed_dataAutorizacao(data);
        ped.setPed_dataEmissao(data);
        ped.setPed_status("ABERTO");
        ped.setPed_total(subtotal);
        PedidoDao.inserir(ped);
        PessoaDao.alterar(pessoa);

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
        ped = new Pedidos();
        cidade = new Cidades();
        pessoa = new Pessoa();
        iten = new ItensPedidos();
        lsprod = new ArrayList<>();
        lsprodtela = new ArrayList<>();
        return "/cliente/cadastrar_pedido?faces-redirect=true";
    }

    public String actionClienteCadastrado() {
        forpgt = FormaPgtoDAO.listagem(null);
        estados = CidadesDao.listar("est_nome");
        cidades = new ArrayList<Cidades>();
        Pessoa pes = PessoaDao.pesqUsuario(getUsuarioLogado());
        pessoa = pes;
        return null;

    }

    public String actionTipodePgt() {
        if (formaPgto.getDescricao().contains("BOLETO")) {
            msg = "Ao final da compra você será apresentado ao boleto "
                    + "de pagamento. "
                    + "Imprima-o e efetue o pagamento "
                    + "em qualquer banco para seu pedido ser aprovado.";

            img_nome = "codbarras222";
            actionQtdParcelas();
        } else {
            msg = "";
            img_nome = "";
            actionQtdParcelas();
        }
        return "/public/form_cliente?faces-redirect=true";
    }

    public List<Integer> actionQtdParcelas() {
        lsint = new ArrayList<>();
        int num = formaPgto.getNumMaxParc();
        for (int i = 0; i < num; i++) {
            lsint.add(i);
        }
        return lsint;
    }

    public String actionInserirFoneCliente() {
        Fone fone = new Fone();
        fone.setPessoa(this.pessoa);
        this.pessoa.getFones().add(fone);
        return "/cliente/form_cliente?faces-redirect=true";
    }

    public String actionExcluirFoneCliente(Fone fone) {
        fone.setPessoa(pessoa);
        this.pessoa.getFones().remove(fone);
        PessoaDao.excluirFone(fone);
        return "/cliente/form_cliente?faces-redirect=true";
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<FormaPgto> getForpgt() {
        return forpgt;
    }

    public void setForpgt(List<FormaPgto> forpgt) {
        this.forpgt = forpgt;
    }

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }

    public List<Integer> getLsint() {
        return lsint;
    }

    public void setLsint(List<Integer> lsint) {
        this.lsint = lsint;
    }

    public String getImg_nome() {
        return img_nome;
    }

    public void setImg_nome(String img_nome) {
        this.img_nome = img_nome;
    }

    public List<Cidades> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidades> cidades) {
        this.cidades = cidades;
    }

    public List<Estados> getEstados() {
        return estados;
    }

    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Cidades getCidade() {
        return cidade;
    }

    public void setCidade(Cidades cidade) {
        this.cidade = cidade;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

}
