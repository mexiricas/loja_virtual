package br.com.negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import br.com.beans.FormaPgto;
import br.com.persistencia.FormaPgtoDAO;
import java.util.ArrayList;
import java.util.Iterator;

@ManagedBean
@SessionScoped
public class FormaPgtoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private FormaPgto formaPgto = new FormaPgto();
    private String filtro = "";
    private List<Integer> lsint = new ArrayList<>();
    private String msg = "";
    private String img_nome = "";

    public List<FormaPgto> getListagem() {
        return FormaPgtoDAO.listagem(filtro);
    }

    public String actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (formaPgto.getId() == 0) {
            FormaPgtoDAO.inserir(formaPgto);
            context.addMessage(null, new FacesMessage("Sucesso",
                    "Inserido com sucesso!"));
        } else {
            FormaPgtoDAO.alterar(formaPgto);
            context.addMessage(null, new FacesMessage("Sucesso",
                    "Alterado com sucesso!"));
        }
        return "/admin/lista_formaPgto?faces-redirect=true";
    }

    public String actionInserir() {
        formaPgto = new FormaPgto();
        return "/admin/lista_formaPgto?faces-redirect=true";
    }

    public String actionTipodePgt() {
        if (formaPgto.getDescricao().contains("BOLETO")) {
            msg = "Ao final da compra você será apresentado ao boleto "
                    + "de pagamento. "
                    + "Imprima-o e efetue o pagamento "
                    + "em qualquer banco para seu pedido ser aprovado.";

            img_nome = "codbarras222";
        } else {
            msg="";
            img_nome= "";
            actionQtdParcelas();
        }
        return "/public/form_cliente?faces-redirect=true";
    }

    public List<Integer> actionQtdParcelas() {
        lsint = new ArrayList<>();
        int num = formaPgto.getNumMaxParc();
        for (int i = 0; i < num; i++) {
            lsint.add(i);
        }
        return lsint;
    }

    public String actionExcluir() {
        FormaPgtoDAO.excluir(formaPgto);
        return "/admin/lista_formaPgto?faces-redirect=true";
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Forma de Pagamento Selecionada",
                String.valueOf(((FormaPgto) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Integer> getLsint() {
        return lsint;
    }

    public void setLsint(List<Integer> lsint) {
        this.lsint = lsint;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg_nome() {
        return img_nome;
    }

    public void setImg_nome(String img_nome) {
        this.img_nome = img_nome;
    }

}
