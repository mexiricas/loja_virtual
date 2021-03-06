package br.com.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.beans.Cidades;
import br.com.beans.Estados;
import br.com.beans.Fone;
import br.com.beans.FormaPgto;
import br.com.beans.Pessoa;
import br.com.persistencia.CidadesDao;
import br.com.persistencia.FormaPgtoDAO;
import br.com.persistencia.PessoaDao;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@ManagedBean
@SessionScoped
public class PessoaCtrl implements Serializable {

    private static final long serialVersionUID = 7329427687235074332L;
    ///  ESTADOS E CIDADE  /////

    private Cidades cidade;
    private Estados estado;
    private List<Cidades> cidades;
    private List<Estados> estados;

    ////////////////////////////
    private Pessoa pessoa = new Pessoa();
    private String msg = "";
    private String nome = "";
    private String filtro = "";

    public String actionGravar() {
        if (pessoa.getPes_id() == 0) {
            PessoaDao pesDao = new PessoaDao();
            if (pesDao.isValid(pessoa)) {
                setMsg("Ja cadastrado");
                return "/admin/form_pessoa?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Gravado com sucesso " + pessoa.getPes_nome()));
                this.pessoa.setNivel("ROLE_ADMINISTRATOR");
                PessoaDao.inserir(pessoa);

                // ESTADO E CIDADE
                estado = new Estados();
                estados = CidadesDao.listagemSiglaEstados(null);
                this.msg = "";
                return actionInserir();
            }
        } else {
            PessoaDao.alterar(pessoa);
            return "/admin/lista_cliente?faces-redirect=true";
        }
    }

    public String actionGravarCliente() {
        if (pessoa.getPes_id() == 0) {
            PessoaDao pesDao = new PessoaDao();
            if (pesDao.isValid(pessoa)) {
                setMsg("Usuario ja cadastrado!");
                return "/public/form_cliente?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage("cad",
                        new FacesMessage("Gravado com sucesso " + pessoa.getPes_nome()));
                this.pessoa.setNivel("ROLE_CLIENTE");
                PessoaDao.inserir(pessoa);

                this.msg = "";
                return "/public/failed?faces-redirect=true";
            }
        } else {
            PessoaDao.alterar(pessoa);
            return "/public/failed?faces-redirect=true";
        }
    }

    public String actionPessoaNovo() {
        pessoa = new Pessoa();
        estados = CidadesDao.listar("est_nome");
        cidades = new ArrayList<Cidades>();
        this.msg = "";
        return "/admin/form_pessoa?faces-redirect=true";
    }

    public String actionClienteLogin() {

        return "/cliente/cadastrar_pedido?faces-redirect=true";
    }

    public String actionClienteNovo() {
        estados = CidadesDao.listar("est_nome");
        cidades = new ArrayList<Cidades>();
        this.msg = "";
        actionInserirFoneCliente();
        return "/public/form_cliente?faces-redirect=true";
    }

    public String actionInserir() {
        this.pessoa = new Pessoa();
        estados = CidadesDao.listar("est_nome");
        cidades = new ArrayList<Cidades>();
        return "/admin/form_pessoa?faces-redirect=true";
    }

    public String actionInserirCliente() {
        actionInserirFoneCliente();
        estados = CidadesDao.listar("est_nome");
        cidades = new ArrayList<Cidades>();
        return "/public/form_cliente?faces-redirect=true";
    }

    public String actionVoltaLogin() {
//        this.pessoa = new Pessoa();
//        estados = CidadesDao.listar("est_nome");
//        cidades = new ArrayList<Cidades>();
        return "/public/failed?faces-redirect=true";
    }
 public String actionVoltaHome() {
        this.pessoa = new Pessoa();
        estados = CidadesDao.listar("est_nome");
        cidades = new ArrayList<Cidades>();
        return "/public/index?faces-redirect=true";
    }
    public String actionListadeCliente() {
        this.msg = "";
        this.pessoa = new Pessoa();
        return "/admin/lista_cliente?faces-redirect=true";
    }

    public String actionExcluir() {
        PessoaDao.excluir(pessoa);
        return "/admin/lista_cliente?faces-redirect=true";
    }

    public String actionAlterar(Pessoa p) {
        if (p.getNivel().equalsIgnoreCase("ROLE_CLIENTE")) {
            this.pessoa = p;
            estados = CidadesDao.listar("est_nome");
            cidades = new ArrayList<Cidades>();
            return "/public/form_cliente?faces-redirect=true";
        }
        this.pessoa = p;
        estados = CidadesDao.listar("est_nome");
        cidades = new ArrayList<Cidades>();
        return "/admin/form_pessoa?faces-redirect=true";
    }

    public String actionDetalhes() {
        int tam = this.pessoa.getPes_nome().indexOf(" ");
        if (tam > 0) {
            setNome(this.pessoa.getPes_nome().substring(0, tam));
        } else {
            setNome(this.pessoa.getPes_nome());
        }
        return "/admin/detalhes_pessoa?faces-redirect=true";
    }

    public List<Pessoa> getlistagem() {
        return PessoaDao.listagem(null);
    }

    public String actionInserirFone() {
        Fone fone = new Fone();
        fone.setPessoa(this.pessoa);
        this.pessoa.getFones().add(fone);
        return "/admin/form_pessoa?faces-redirect=true";
    }

    public String actionInserirFoneCliente() {
        Fone fone = new Fone();
        fone.setPessoa(this.pessoa);
        this.pessoa.getFones().add(fone);
        return "/public/form_cliente?faces-redirect=true";
    }

    public String actionExcluirFone(Fone fone) {
        fone.setPessoa(pessoa);
        this.pessoa.getFones().remove(fone);
        PessoaDao.excluirFone(fone);
        return "/admin/form_pessoa?faces-redirect=true";
    }

    public String actionExcluirFoneCliente(Fone fone) {
        fone.setPessoa(pessoa);
        this.pessoa.getFones().remove(fone);
        PessoaDao.excluirFone(fone);
        return "/cliente/form_cliente?faces-redirect=true";
    }

    public void popular() {
        if (estado != null) {
            CidadesDao cidadesDao = new CidadesDao();
            cidades = cidadesDao.buscaPorCidade(estado.getEst_id());
        } else {
            cidades = new ArrayList<Cidades>();
        }

    }

    public String getUsuarioLogado() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    // getter e setter
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cidades getCidade() {
        return cidade;
    }

    public void setCidade(Cidades cidade) {
        this.cidade = cidade;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
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

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

}
