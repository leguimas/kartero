package br.com.leguimas.kartero.cep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.leguimas.kartero.KarteroException;
import br.com.leguimas.kartero.database.PoolDeConexoes;

public class CEPRepository {

	public CEP consultaCEP(String CEP) {
		Connection conexao = PoolDeConexoes.obtemConexao();
		PreparedStatement query = null;
		ResultSet cepsEncontrados = null;

		CEP cepEncontrado = new CEP(CEP);
		boolean encontrouCep = false;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT log_logradouro.log_tipo_logradouro as tipo_logradouro,");
		sql.append("       log_logradouro.log_no as logradouro,");
		sql.append("       log_bairro.bai_no as bairro,");
		sql.append("       log_localidade.loc_no as cidade,");
		sql.append("       log_localidade.ufe_sg as uf,");
		sql.append("       log_logradouro.cep as cep ");
		sql.append("FROM   log_logradouro,");
		sql.append("       log_localidade,");
		sql.append("       log_bairro ");
		sql.append("WHERE  log_logradouro.loc_nu_sequencial = log_localidade.loc_nu_sequencial");
		sql.append("  AND  log_logradouro.bai_nu_sequencial_ini = log_bairro.bai_nu_sequencial");
		sql.append("  AND  log_logradouro.cep = ?");

		try {
			query = conexao.prepareStatement(sql.toString());
			query.setString(1, cepEncontrado.obtemCEP());

			cepsEncontrados = query.executeQuery();
			while (cepsEncontrados.next()) {
				encontrouCep = true;
				cepEncontrado.comLogradouro(cepsEncontrados.getString("tipo_logradouro"),
						cepsEncontrados.getString("logradouro"));
				cepEncontrado.comBairro(cepsEncontrados.getString("bairro"));
				cepEncontrado.comCidade(cepsEncontrados.getString("cidade"));
				cepEncontrado.comUf(cepsEncontrados.getString("uf"));
			}

		} catch (SQLException e) {
			throw new KarteroException(e, "Problemas ao realizar consulta do CEP: " + e.getMessage());
		} finally {
			this.liberaRecursosDeAcessoAoBanco(conexao, query, cepsEncontrados);
		}

		return encontrouCep ? cepEncontrado : null;
	}

	private void liberaRecursosDeAcessoAoBanco(Connection conexao, PreparedStatement query, ResultSet cepsEncontrados) {
		Exception excecao = null;

		if (cepsEncontrados != null) {
			try {
				cepsEncontrados.close();
			} catch (SQLException e) {
				excecao = e;
			}
		}

		if (query != null) {
			try {
				query.close();
			} catch (SQLException e) {
				excecao = e;
			}
		}

		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				excecao = e;
			}
		}

		if (excecao != null) {
			throw new KarteroException(excecao, "Problemas ao liberar recursos de acesso ao banco de dados: "
					+ excecao.getMessage());
		}
	}

}
