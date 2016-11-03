package br.com.negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.beans.Cidades;
import br.com.beans.Estados;
import br.com.persistencia.CidadesDao;

@ManagedBean
@SessionScoped
public class CidadesCtrl implements Serializable {

	private static final long serialVersionUID = -3639276592540284084L;
	private Cidades cid = new Cidades();
	private Estados est = new Estados();
	private String filtro = null;

	public List<Cidades> getListagemCidades() {
		if (filtro == null) {
			this.cid = new Cidades();
			return CidadesDao.listagemCidades(null);
		} else
			return CidadesDao.listagemCidades(filtro);
	}

	
	public List<Estados> getListagemEstados() {
		if (filtro == null) {
			this.cid = new Cidades();
			return CidadesDao.listagemSiglaEstados(null);
		} else
			return CidadesDao.listagemSiglaEstados(filtro);
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Cidades getCid() {
		return cid;
	}

	public void setCid(Cidades cid) {
		this.cid = cid;
	}

	public Estados getEst() {
		return est;
	}

	public void setEst(Estados est) {
		this.est = est;
	}

}
