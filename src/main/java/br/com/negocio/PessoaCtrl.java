package br.com.negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.beans.Fone;
import br.com.beans.Pessoa;
import br.com.persistencia.PessoaDao;

@ManagedBean
@SessionScoped
public class PessoaCtrl implements Serializable {

	private static final long serialVersionUID = 7329427687235074332L;
	private Pessoa pessoa = new Pessoa();
	private String msg = "";
	private String nome ="";

	public String actionGravar() {
		if (pessoa.getPes_id() == 0) {
			PessoaDao pesDao = new PessoaDao();
			if (pesDao.isValid(pessoa)) {
				System.out.println("passou");
				setMsg("Ja cadastrado");
				return "/public/form_pessoa?faces-redirect=true";
			} else {
				PessoaDao.inserir(pessoa);
				this.msg="";
				return actionInserir();
			}
		} else {
			PessoaDao.alterar(pessoa);
			return "/admin/lista_cliente?faces-redirect=true";
		}
	}

	public String actionPessoaNovo() {
		this.pessoa = new Pessoa();
		this.msg="";
		return "/public/form_pessoa?faces-redirect=true";
	}

	public String actionInserir() {
		this.pessoa = new Pessoa();
		return "/public/form_pessoa?faces-redirect=true";
	}

	public String actionListadeCliente(){
		this.msg="";
		this.pessoa = new Pessoa();
		return "/admin/lista_cliente?faces-redirect=true";
	}
	public String actionExcluir() {
		PessoaDao.excluir(pessoa);
		return "/admin/lista_cliente?faces-redirect=true";
	}

	public String actionAlterar(Pessoa p) {
		this.pessoa = p;
		return "/public/form_pessoa?faces-redirect=true";
	}

	public String actionDetalhes(Pessoa p) {
		this.pessoa = p;
		int tam = p.getPes_nome().indexOf(" ");
		 setNome(p.getPes_nome().substring(0, tam));
		return "/admin/detalhes_pessoa?faces-redirect=true";
	}

	public List<Pessoa> getlistagem() {
		return PessoaDao.listagem(null);
	}

	public String actionInserirFone() {
		Fone fone = new Fone();
		fone.setPessoa(pessoa);
		this.pessoa.getFones().add(fone);
		return "/public/form_pessoa?faces-redirect=true";
	}

	public String actionExcluirFone(Fone fone) {
		fone.setPessoa(pessoa);
		this.pessoa.getFones().remove(fone);
		PessoaDao.excluirFone(fone);
		return "/public/form_pessoa?faces-redirect=true";
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

}
