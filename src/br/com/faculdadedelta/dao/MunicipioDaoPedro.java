package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.MunicipioPedro;
import br.com.faculdadedelta.modelo.UfPedro;
import br.edu.faculdadedelta.util.Conexao;

public class MunicipioDaoPedro {

	public void incluir(MunicipioPedro municipio) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO municipios (nome_municipio,cnpj_municipio,codigo_municipio,id_uf ) VALUES(?,?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setString(1, municipio.getNome().trim());
			ps.setString(2, municipio.getCnpj().trim());
			ps.setString(3, municipio.getCodigo().trim());
			ps.setLong(4, municipio.getUf().getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void alterar(MunicipioPedro municipio) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE municipios	SET nome_municipio=?, cnpj_municipio=?, codigo_municipio=?, id_uf=?	WHERE id_municipio=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, municipio.getNome().trim());
			ps.setString(2, municipio.getCnpj().trim());
			ps.setString(3, municipio.getCodigo().trim());
			ps.setLong(4, municipio.getUf().getId());
			ps.setLong(5, municipio.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(MunicipioPedro municipio) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " DELETE FROM municipios WHERE id_municipio=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, municipio.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<MunicipioPedro> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT\r\n" + 
				"m.id_municipio AS idMunicipio,\r\n" + 
				"m.nome_municipio AS nomeMunicipio,\r\n" + 
				"m.cnpj_municipio AS cnpjMunicipio,\r\n" + 
				"m.codigo_municipio AS codigoMunicipio,\r\n" + 
				"u.id_uf AS idUf,\r\n" + 
				"u.nome_uf AS nomeUf,\r\n" + 
				"u.sigla_uf AS siglaUF,\r\n" + 
				"u.codigo_uf AS codigoUf\r\n" + 
				"FROM municipios m\r\n" + 
				"INNER JOIN ufs u ON m.id_municipio = u.id_uf";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MunicipioPedro> listaRetorno = new ArrayList<MunicipioPedro>();

		try {
			while (rs.next()) {
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

				municipio.setUf(uf);
				listaRetorno.add(municipio);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return listaRetorno;

	}

	public MunicipioPedro pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_municipio,nome_municipio,cnpj_municipio,codigo_municipio,id_uf FROM municipios WHERE id_municipio=? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		MunicipioPedro retorno = new MunicipioPedro();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				retorno.setId(rs.getLong("id_municipio"));
				retorno.setNome(rs.getString("nome_municipio").trim());
				retorno.setCnpj(rs.getString("cnpj_municipio").trim());
				retorno.setCodigo(rs.getString("codigo_municipio").trim());
				retorno.setId(rs.getLong("id_uf"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return retorno;

	}

}
