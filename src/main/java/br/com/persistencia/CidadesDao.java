package br.com.persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.beans.Cidades;
import br.com.beans.Estados;

public class CidadesDao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static List<Cidades> listagemCidades(String cid_nome) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Cidades");
		if (cid_nome == null) {
			consulta = sessao.createQuery("from Cidades order by nome");
		} else {
			consulta = sessao
					.createQuery("from Cidades where nome like:parametro order by id");
			consulta.setString("parametro", "%" + cid_nome + "%");
		}
		List lscid = consulta.list();
		sessao.close();

		return lscid;
	}

	public static List<Estados> listagemSiglaEstados(String filtro) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = sessao.createQuery("from Estados");
		if (filtro == null) {
			consulta = sessao.createQuery("from Estados order by sigla");
		} else {
			consulta = sessao
					.createQuery("from Estados where sigla like:parametro order by id");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List lsest = consulta.list();
		sessao.close();

		return lsest;
	}

}
