package br.org.ovelha.message;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.message.Message;

public interface InfoMessages {

    final Message DELETE_OK = new DefaultMessage("{delete.sucesso}");

    final Message INSERT_OK = new DefaultMessage("{insert.sucesso}");

    final Message UPDATE_OK = new DefaultMessage("{update.sucesso}");

}
