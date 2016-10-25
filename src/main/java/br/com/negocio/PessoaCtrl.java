package br.com.negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.beans.Fone;
import br.com.beans.Pessoa;
import br.com.persistencia.PessoaDao;

@ManagedBean(name = "pessoaBeans")
@SessionScoped
public class PessoaCtrl implements Serializable {

	private static final long serialVersionUID = 7329427687235074332L;
	private Pessoa pessoa = new Pessoa();
	private Fone fone = new Fone();

	public String actionGravar() {
		if (pessoa.getPes_id() == 0) {
			PessoaDao.inserir(pessoa);
			return actionInserir();
		} else {
			PessoaDao.alterar(pessoa);
			return "form_pessoa";
		}

	}

	public String actionInserir() {
		pessoa = new Pessoa();
		return "form_pessoa?faces-redirect=true";
	}

	public String actionExcluir() {
		PessoaDao.excluir(pessoa);
		return "form_pessoa";
	}

	public String actionAlterar(Pessoa p) {
		pessoa = p;
		return "form_pessoa?faces-redirect=true";
	}

	public List<Pessoa> getlistagem() {
		return PessoaDao.lsPessoa("");
	}

	public String actionInserirFone() {
		System.out.println("passou");
		Fone fone = new Fone();
		fone.setPessoa(pessoa);
		pessoa.getFones().add(fone);
		return "form_pessoa";
	}

	public String actionExcluirFone(Fone f) {
		/* os alunos ... pesquisar e codificar */
		return "form_pessoa";
	}

	// getter e setter
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Fone getFone() {
		return fone;
	}

	public void setFone(Fone fone) {
		this.fone = fone;
	}

}
