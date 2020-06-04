package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.BairroDaoPedro;
import br.com.faculdadedelta.modelo.BairroPedro;
import br.com.faculdadedelta.modelo.MunicipioPedro;

@ManagedBean
@SessionScoped
public class BairroControllerPedro {
	
	private BairroPedro bairro = new BairroPedro();
	private BairroDaoPedro dao = new BairroDaoPedro();
	private MunicipioPedro municipioSelecionado = new MunicipioPedro();

	public BairroPedro getBairro() {
		return bairro;
	}

	public void setBairro(BairroPedro bairro) {
		this.bairro = bairro;
	}

	public MunicipioPedro getMunicipioSelecionado() {
		return municipioSelecionado;
	}

	public void setMunicipioSelecionado(MunicipioPedro municipioSelecionado) {
		this.municipioSelecionado = municipioSelecionado;
	}
	
	public void limparCampo() {
		bairro = new BairroPedro();
		municipioSelecionado = new MunicipioPedro();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		
		try {
		if(bairro.getId()==null) {
			bairro.setMunicipio(municipioSelecionado);
				dao.incluir(bairro);
				limparCampo();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				bairro.setMunicipio(municipioSelecionado);
				dao.alterar(bairro);
				limparCampo();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroBairro.xhtml";
		
	}
	public String editar() {
		bairro.setMunicipio(municipioSelecionado);
		return "CadastroBairro.xhtml";
	}
	public String excluir() {
		try {
			bairro.setMunicipio(municipioSelecionado);
			dao.excluir(bairro);
			limparCampo();
			exibirMensagem("Exclusao realizada com sucesso.");		
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "ListaBairro.xhtml";
	}
	
	public List<BairroPedro> getLista(){
		List<BairroPedro> listaRetorno = new ArrayList<BairroPedro>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
		
	}
	
	
}
