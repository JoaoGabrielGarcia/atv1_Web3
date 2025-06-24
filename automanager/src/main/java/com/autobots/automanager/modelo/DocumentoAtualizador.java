package com.autobots.automanager.modelo;

import java.util.List;

import com.autobots.automanager.entidades.Documento;

public class DocumentoAtualizador {
	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Documento documento, Documento atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getTipo())) {
				documento.setTipo(atualizacao.getTipo());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				documento.setNumero(atualizacao.getNumero());
			}
		}
	}

	public void atualizar(List<Documento> documentos, List<Documento> atualizacoes) {
		// Atualizar documentos existentes
		for (Documento atualizacao : atualizacoes) {
			boolean atualizado = false;
			if (atualizacao.getId() != null) {
				for (Documento documento : documentos) {
					if (atualizacao.getId().equals(documento.getId())) {
						atualizar(documento, atualizacao);
						atualizado = true;
						break;
					}
				}
			}
			// Se não foi atualizado, é novo documento
			if (!atualizado) {
				documentos.add(atualizacao);
			}
		}
		documentos.removeIf(documento ->
			documento.getId() != null &&
			atualizacoes.stream().noneMatch(a -> a.getId() != null && a.getId().equals(documento.getId()))
		);
	}
}
