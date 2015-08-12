package br.org.ovelha.util;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.org.ovelha.business.AgendaBC;
import br.org.ovelha.business.DiscipuloBC;
import br.org.ovelha.business.EmailBC;
import br.org.ovelha.domain.Agenda;
import br.org.ovelha.domain.MensagemEletronica;
import br.org.ovelha.domain.dto.DatasComemorativas;


@Stateless
public class Agendador {

	@Inject
	GerenciadorEmail correio;
	
	@Inject
	EmailBC bc;
	
	@Inject
	DiscipuloBC discipulosBC;
	
	@Inject
	AgendaBC agendaBC;

	//@Schedule(minute = "*/1", hour = "*", persistent = false)
	//@Schedule(dayOfWeek="Sun, Mon, Tue, Wed, Thu, Fri, Sat", hour="5", minute="30")
	@Schedule(dayOfWeek="Mon", hour="5", minute="30")
	public void enviarNotificacoesEventosSemanal() {

		try{			
			MensagemEletronica email = bc.newMensagemEletronica();
			email.setAssunto("Lembrete dos próximos acontecimentos ("+Data.dataExtenso()+")");
			
			List<DatasComemorativas> datas = discipulosBC.obterDatasComemorativasMes();	
								
			StringBuilder conteudo = new StringBuilder();
			conteudo.append("Prezados(as),\n");
			conteudo.append("\n");
			conteudo.append("Segue os informes dos próximos acontecimentos cadastrados no sistema:\n");
			conteudo.append("\n");				
			conteudo.append("1) Datas comemorativas  \n");
			conteudo.append("\n");
			
			for (DatasComemorativas data:datas){
				conteudo.append("- "+data.getNome()+" comemora em "+data.getDataDiaMes()+" o seu aniversário ("+data.getEvento()+");\n");				
			}
			
			conteudo.append("\n");
			conteudo.append("\n");
			conteudo.append("2) Eventos do Ministerio de Casais  \n");
			conteudo.append("\n");
			
			List<Agenda> eventos= agendaBC.obterAgendaMes();

			for (Agenda agenda:eventos){
				conteudo.append("- "+agenda.getNome()+" previsão de inicio para "+Data.dataHoraExtenso(agenda.getInicio())+" ate "+Data.dataHoraExtenso(agenda.getTermino())+" no(a) "+agenda.getLocal()+";\n");
			}
			
			conteudo.append("\n");
			conteudo.append("\n");
			conteudo.append("Atenciosamente,\n");
			conteudo.append("Sistema Ovelha");
			
			email.setConteudo(conteudo.toString());	
			
			if((datas.size()>0) || (eventos.size()>0)){
				bc.enviarEmail(email);	
			}
			
			
		}catch(Exception e){
			System.out.println("Erro ao enviar email: "+e.getMessage());

		}

	}


}
