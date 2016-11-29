/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.persistencia;

import br.com.beans.ItensPedidos;
import static br.com.beans.ItensPedidos_.ped;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Denis
 */
public class ItensPedidosDao implements Serializable{
    
   
        public static void inserir(ItensPedidos iten) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.save(iten);
        t.commit();
        sessao.close();
    }
}
