package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.BairroPedro;
import br.com.faculdadedelta.modelo.MunicipioPedro;
import br.com.faculdadedelta.modelo.PaisPedro;
import br.com.faculdadedelta.modelo.UfPedro;
import br.edu.faculdadedelta.util.Conexao;

public class BairroDaoPedro {
	
	public void incluir(BairroPedro bairro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO bairros (nome_bairro,descricao_bairro,id_municipio) VALUES(?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1,bairro.getNome().trim());
			ps.setString(2,bairro.getDescricao().trim());
			ps.setLong(3,bairro.getMunicipio().getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}
	
	public void alterar(BairroPedro bairro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE bairros SET nome_bairro=?, descricao_bairro=?, id_municipio=? WHERE id_bairro=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
			ps.setString(1,bairro.getNome().trim());
			ps.setString(2,bairro.getDescricao().trim());
			ps.setLong(3,bairro.getMunicipio().getId());
			ps.setLong(4, bairro.getId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}
	
	public void excluir(BairroPedro bairro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " DELETE FROM bairros WHERE id_bairro=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1,bairro.getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public List<BairroPedro>  listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT\r\n" + 
				"b.id_bairro AS idBairro,\r\n" + 
				"b.nome_bairro AS nomeBairro,\r\n" + 
				"b.descricao_bairro AS descricaoBairro,\r\n" + 
				"m.id_municipio AS idMunicipio,\r\n" + 
				"m.nome_municipio AS nomeMunicipio,\r\n" + 
				"m.cnpj_municipio AS cnpjMunicipio, \r\n" + 
				"m.codigo_municipio AS codigoMunicipio, \r\n" + 
				"u.id_uf AS idUf,\r\n" + 
				"u.nome_uf AS nomeUf,\r\n" + 
				"u.sigla_uf AS siglaUf,\r\n" + 
				"u.codigo_uf AS codigoUf,\r\n" + 
				"p.id_pais AS idPais,\r\n" + 
				"p.nome_pais AS nomePais,\r\n" + 
				"p.codigo_pais AS codigoPais\r\n" + 
				"FROM bairros b\r\n" + 
				"INNER JOIN municipios m ON b.id_municipio = m.id_municipio \r\n" + 
				"INNER JOIN ufs u ON m.id_uf= u.id_uf \r\n" + 
				"INNER JOIN pais p ON u.id_pais = p.id_pais";
		
		PreparedStatement ps= conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<BairroPedro>listaRetorno = new ArrayList<BairroPedro>();
		try {
			while(rs.next()) {
				BairroPedro bairro = new BairroPedro();
				bairro.setId(rs.getLong("idBairro"));
				bairro.setNome(rs.getString("nomeBairro").trim());
				bairro.setDescricao(rs.getString("descricaoBairro").trim());
				
				
				MunicipioPedro municipio = new MunicipioPedro();
				municipio.setId(rs.getLong("idMunicipio"));
				municipio.setNome(rs.getString("nomeMunicipio").trim());
				municipio.setCnpj(rs.getString("cnpjMunicipio").trim());
				municipio.setCodigo(rs.getString("codigoMunicipio").trim());
				
				UfPedro uf = new UfPedro();
				uf.setId(rs.getLong("idUf"));
				uf.setNome(rs.getString("nomeUf").trim());
				uf.setSigla(rs.getString("siglaUf").trim());
				uf.setCodigo(rs.getString("codigoUf").trim());
				
				PaisPedro pais = new PaisPedro();
				pais.setId(rs.getLong("idPais"));
				pais.setNome(rs.getString("nomePais").trim());
				pais.setCodigo(rs.getString("codigoPais").trim());
				
			
				bairro.setMunicipio(municipio);
				municipio.setUf(uf);
				uf.setPais(pais);
				listaRetorno.add(bairro);
			}
			
			
	} catch (SQLException e) {
		e.printStackTrace();
		throw new Exception(e);
	}finally {
		Conexao.fecharConexao(conn, ps, rs);
	}
		
		return listaRetorno;
		
	}
	
	public BairroPedro pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
	String sql = "SELECT * FROM bairros WHERE id_bairro=? ";
	PreparedStatement ps = null;
	ResultSet rs = null;
	BairroPedro retorno = new BairroPedro();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setLong(1,id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				retorno.setId(rs.getLong(" id_bairro"));
				retorno.setNome(rs.getString("nome_bairro").trim());
				retorno.setDescricao(rs.getString("descricao_bairro").trim());
				retorno.setId(rs.getLong("id_municipio"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
			
		}finally {
			 Conexao.fecharConexao(conn, ps, rs);
		}
		
		return retorno;
		
	}

}
