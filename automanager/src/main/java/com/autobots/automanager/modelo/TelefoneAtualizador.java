package com.autobots.automanager.modelo;

import java.util.List;

import com.autobots.automanager.entidades.Telefone;

public class TelefoneAtualizador {
	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Telefone telefone, Telefone atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getDdd())) {
				telefone.setDdd(atualizacao.getDdd());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				telefone.setNumero(atualizacao.getNumero());
			}
		}
	}

	public void atualizar(List<Telefone> telefones, List<Telefone> atualizacoes) {
		// Atualizar telefones existentes
		for (Telefone atualizacao : atualizacoes) {
			boolean atualizado = false;
			if (atualizacao.getId() != null) {
				for (Telefone telefone : telefones) {
					if (atualizacao.getId().equals(telefone.getId())) {
						atualizar(telefone, atualizacao);
						atualizado = true;
						break;
					}
				}
			}
			// Se não foi atualizado, é novo telefone
			if (!atualizado) {
				telefones.add(atualizacao);
			}
		}
		telefones.removeIf(telefone ->
			telefone.getId() != null &&
			atualizacoes.stream().noneMatch(a -> a.getId() != null && a.getId().equals(telefone.getId()))
		);
	}
}