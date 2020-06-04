package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.MunicipioDaoPedro;
import br.com.faculdadedelta.modelo.MunicipioPedro;
import br.com.faculdadedelta.modelo.UfPedro;

@ManagedBean
@SessionScoped
public class MunicipioControllerPedro {

	private MunicipioPedro municipio = new MunicipioPedro();
	private MunicipioDaoPedro dao = new MunicipioDaoPedro();
	private UfPedro ufSelecionado = new UfPedro();

	public MunicipioPedro getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipioPedro municipio) {
		this.municipio = municipio;
	}

	public UfPedro getUfSelecionado() {
		return ufSelecionado;
	}

	public void setUfSelecionado(UfPedro ufSelecionado) {
		this.ufSelecionado = ufSelecionado;
	}

	public void limparCampo() {
		municipio = new MunicipioPedro();
		ufSelecionado = new UfPedro();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {

			if (municipio.getId() == null) {
				municipio.setUf(ufSelecionado);
				dao.incluir(municipio);
				limparCampo();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				municipio.setUf(ufSelecionado);
				dao.alterar(municipio);
				limparCampo();
				exibirMensagem("Alteracao realizada com sucesso.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}

		return "CadastroMunicipio.xhtml";

	}

	public String editar() {
		ufSelecionado = municipio.getUf();
		return "CadastroMunicipio.xhtml";
	}

	public String excluir() {

		try {
			municipio.setUf(ufSelecionado);
			dao.excluir(municipio);
			limparCampo();
			exibirMensagem("Exclusao realizada com sucesso.");		
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaMunicipio.xhtml";

	}

	public List<MunicipioPedro> getLista() {
		List<MunicipioPedro> listaRetorno = new ArrayList<MunicipioPedro>();

		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}

		return listaRetorno;

	}

}
