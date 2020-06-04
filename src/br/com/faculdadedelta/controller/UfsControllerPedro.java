package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.UfDaoPedro;
import br.com.faculdadedelta.modelo.PaisPedro;
import br.com.faculdadedelta.modelo.UfPedro;

@ManagedBean
@SessionScoped
public class UfsControllerPedro {

	private UfPedro uf = new UfPedro();
	private UfDaoPedro dao= new UfDaoPedro();
	private PaisPedro paisSelecionado = new PaisPedro();

	public UfPedro getUf() {
		return uf;
	}

	public void setUf(UfPedro uf) {
		this.uf = uf;
	}

	public PaisPedro getPaisSelecionado() {
		return paisSelecionado;
	}

	public void setPaisSelecionado(PaisPedro paisSelecionado) {
		this.paisSelecionado = paisSelecionado;
	}
	
	public void limparCampo() {
		uf = new UfPedro();
		paisSelecionado = new PaisPedro();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		
		if(uf.getId()==null) {
			uf.setPais(paisSelecionado);
				dao.incluir(uf);
				limparCampo();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				uf.setPais(paisSelecionado);
				dao.alterar(uf);
				limparCampo();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroUf.xhtml";
		
	}
	
	public String editar() {
		paisSelecionado = uf.getPais();
		return "CadastroUf.xhtml";	
	}
	public String excluir() {
		try {
			uf.setPais(paisSelecionado);
			dao.excluir(uf);
			limparCampo();
			exibirMensagem("Exclusao realizada com sucesso.");		
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaUf.xhtml";
	}
	
	public List<UfPedro> getLista(){
		List<UfPedro> listaRetorno = new ArrayList<UfPedro>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
		
	}
	
	
}
