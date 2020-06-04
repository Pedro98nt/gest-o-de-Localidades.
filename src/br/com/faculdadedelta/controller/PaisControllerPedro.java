package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.PaisDaoPedro;
import br.com.faculdadedelta.modelo.PaisPedro;

@ManagedBean
@SessionScoped
public class PaisControllerPedro {
	
	private PaisPedro pais = new PaisPedro();
	private PaisDaoPedro dao = new PaisDaoPedro();

	public PaisPedro getPais() {
		return pais;
	}

	public void setPais(PaisPedro pais) {
		this.pais = pais;
	}
	
	public void limparCampo() {
		pais = new PaisPedro();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		if(pais.getId()==null) {
				dao.incluir(pais);
				limparCampo();
				exibirMensagem("Inclusao realizada com sucesso.");
			}else {
				dao.alterar(pais);
				limparCampo();
				exibirMensagem("Alteracao realizada com sucesso.");
				
			}
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroPais.xhtml";
		
	}
	
	public String editar() {
		return "CadastroPais.xhtml";
		
	}
	
	public String excluir() {
		try {
			dao.excluir(pais);
			limparCampo();
			exibirMensagem("Exclusao realizada com sucesso.");		
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "ListaPais.xhtml";
		
	}
	
	public List<PaisPedro> getLista(){
		List<PaisPedro>listaRetorno = new ArrayList<PaisPedro>();
		
		try {
			listaRetorno= dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
		
	}
	
	

}
