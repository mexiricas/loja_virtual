/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.negocio;

import br.com.beans.Cidades;
import br.com.beans.Estados;
import br.com.persistencia.CidadesDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author HJ-Sistemas
 */

@ManagedBean
public class CidadesCtrl implements Serializable {
    

    private Cidades cidade;
    private Estados estado;
    private List<Cidades> cidades;
    private List<Estados> estados;
    
    
    
    public List<Cidades> getlistagemCidades() {
        return CidadesDao.listagemCidades(null);
    }
    
    public List<Estados> getlistagemEstados() {
        return CidadesDao.listagemSiglaEstados(null);
    }
    
    // getter e setter

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
    
    
    
    
    
    
    
    
    
    
    

}
