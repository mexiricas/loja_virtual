package br.com.persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.beans.Fone;
import br.com.beans.Pessoa;

public class PessoaDao implements Serializable {

	private static final long serialVersionUID = -1171251332916938584L;
	
	public static void inserir(Pessoa pessoa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(pessoa);
		t.commit();
		sessao.close();
	}
	
	public static void alterar(Pessoa pessoa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(pessoa);
		t.commit();
		sessao.close();
	}
	
	public static void excluir(Pessoa pessoa){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(pessoa);
		t.commit();
		sessao.close();
	}
	
	public static void excluirFone(Fone fone){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(fone);
		t.commit();
		sessao.close();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Pessoa> lsPessoa(String filtro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta; 
		if(filtro.trim().length() == 0){
			consulta = sessao.createQuery("from Pessoa");
		}else{
			consulta = sessao.createQuery("from pessoa where pes_id like: parametro order by pes_nome");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List listaPessoa = consulta.list();
		sessao.close();
		return listaPessoa;
		
	}

}
