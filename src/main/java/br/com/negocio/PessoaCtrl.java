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

	public String actionGravar() {

		if (pessoa.getPes_id() == 0) {
			PessoaDao.inserir(pessoa);
			return actionInserir();
		} else {
			PessoaDao.alterar(pessoa);
			return "lista_cliente";
		}
	}

	public String actionPessoaNovo() {
		this.pessoa = new Pessoa();
		return "form_pessoa?faces-redirect=true";
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

	public String actionDetalhes(Pessoa p) {
		pessoa = p;
		return "detalhes_pessoa?faces-redirect=true";
	}

	public List<Pessoa> getlistagem() {
		return PessoaDao.lsPessoa("");
	}

	public String actionInserirFone() {
		Fone fone = new Fone();
		fone.setPessoa(pessoa);
		pessoa.getFones().add(fone);
		return "form_pessoa";
	}

	public String actionExcluirFone(Fone fone) {
		fone.setPessoa(pessoa);
		pessoa.getFones().remove(fone);
		PessoaDao.excluirFone(fone);
		return "form_pessoa?faces-redirect=true";
	}

	// getter e setter
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
