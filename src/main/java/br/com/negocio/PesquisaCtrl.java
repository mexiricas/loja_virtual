package br.com.negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.beans.Pessoa;
import br.com.persistencia.PessoaDao;

@ManagedBean
@RequestScoped
public class PesquisaCtrl implements Serializable {

	private static final long serialVersionUID = -6772157278075345335L;
	private Pessoa pessoa = new Pessoa();
	private String filtro;

	public List<Pessoa> getListagem() {
		if (filtro == null) {
			pessoa = new Pessoa();
			return PessoaDao.listagem(null);
		} else
			this.pessoa.setPes_nome(this.filtro); 
			return PessoaDao.listagem(filtro);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
