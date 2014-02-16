package br.ufs.livraria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.mb.CarrinhoMB;
import br.ufs.livraria.mb.MensagensMB;

@Named
public class JSFPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarrinhoMB carrinhoMb;
	
	@Inject
	private MensagensMB mensagensMb;

	@Override
	public void afterPhase(PhaseEvent event) {
		if (event.getPhaseId().equals(PhaseId.PROCESS_VALIDATIONS)) {
			// Verifica se acabou o estoque de algum dos livros adicionados ao carrinho
			carrinhoMb.verificarDisponibilidadeDosLivros();
		}
		if (event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
			// Esvazia a fila de mensagens após renderizar a resposta
			mensagensMb.esvaziar();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}