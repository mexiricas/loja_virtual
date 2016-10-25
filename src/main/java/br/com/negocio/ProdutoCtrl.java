package br.com.negocio;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.beans.Produto;
import br.com.persistencia.ProdutoDAO;

@SessionScoped
@ManagedBean
public class ProdutoCtrl implements Serializable {

	private static final long serialVersionUID = 517579823402093421L;

	private Produto produto = new Produto();

	public List<Produto> getListagem() {
		return ProdutoDAO.listagem(null);
	}

	public String actionGravar() {
		if (produto.getId() == 0) {
			ProdutoDAO.inserir(produto);
			return actionInserir();
		} else {
			ProdutoDAO.alterar(produto);
			return "lista_produto";
		}
	}

	public String actionInserir() {
		produto = new Produto();
		return "form_produto";
	}

	public String actionExcluir(Produto p) {
		ProdutoDAO.excluir(p);
		return "lista_produto";
	}

	public String actionAlterar(Produto p) {
		produto = p;
		return "form_produto";
	}

	// getter e settters

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
