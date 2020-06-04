package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.PaisPedro;
import br.com.faculdadedelta.modelo.UfPedro;
import br.edu.faculdadedelta.util.Conexao;

public class UfDaoPedro {
	
	public void incluir(UfPedro uf) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql= "INSERT INTO ufs(nome_uf,sigla_uf,codigo_uf,id_pais) VALUES(?,?,?,?) ";
	PreparedStatement ps = conn.prepareStatement(sql);
		
		try {
		ps.setString(1,uf.getNome().trim());
		ps.setString(2,uf.getSigla().trim());
		ps.setString(3, uf.getCodigo().trim());
		ps.setLong(4, uf.getPais().getId());
		
		ps.executeUpdate();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(UfPedro uf) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " UPDATE ufs SET nome_uf=?, sigla_uf=?, codigo_uf=?, id_pais=? WHERE id_uf=?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, uf.getNome().trim());
			ps.setString(2, uf.getSigla().trim());
			ps.setString(3, uf.getCodigo().trim());
			ps.setLong(4, uf.getPais().getId());
			ps.setLong(5, uf.getId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
		
	}
	
	public void excluir(UfPedro uf) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM ufs WHERE id_uf=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1,uf.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public List<UfPedro> listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT\r\n" + 
				"u.id_uf AS idUf,\r\n" + 
				"u.nome_uf AS nomeUf,\r\n" + 
				"u.sigla_uf AS siglaUf,\r\n" + 
				"u.codigo_uf AS codigoUf,\r\n" + 
				"p.id_pais AS idPais,\r\n" + 
				"p.nome_pais AS nomePais,\r\n" + 
				"p.codigo_pais AS codigoPais\r\n" + 
				"FROM ufs u\r\n" + 
				"INNER JOIN pais p ON u.id_pais = p.id_pais";
		
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		List<UfPedro>listaRetorno = new ArrayList<UfPedro>();
		try {
			while(rs.next()) {
				
				UfPedro uf = new UfPedro();
				uf.setId(rs.getLong("idUf"));
				uf.setNome(rs.getString("nomeUf").trim());
				uf.setSigla(rs.getString("siglaUf").trim());
				uf.setCodigo(rs.getString("codigoUf").trim());
				
				PaisPedro pais = new PaisPedro();
				pais.setId(rs.getLong("idPais"));
				pais.setNome(rs.getString("nomePais").trim());
				pais.setCodigo(rs.getString("codigoPais").trim());
				
				uf.setPais(pais);
				listaRetorno.add(uf);
				
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		
		
		return listaRetorno;
		
	}
	
	public UfPedro pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM ufs WHERE id_uf = ? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		UfPedro retorno = new UfPedro();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs= ps.executeQuery();
			
			while(rs.next()) {
				retorno.setId(rs.getLong("id_uf"));
				retorno.setNome(rs.getString("nome_uf").trim());
				retorno.setSigla(rs.getString("sigla_uf").trim());
				retorno.setCodigo(rs.getString("codigo_uf").trim());
				retorno.setId(rs.getLong("id_Pais"));
				

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
