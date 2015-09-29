package br.org.ovelha.util;

import java.util.Map;
import java.util.Random;

import javax.faces.context.FacesContext;

import br.org.ovelha.constant.MESES;
import br.org.ovelha.domain.Casal;

public final class StringU {
	
	public static String getPrimeiroNome(String nomeCompleto){
        String primeiroNome = "";  
        int i = 0;  
        boolean fim = false;  
          
        while ((i < nomeCompleto.length()) && !fim) {  
           if (nomeCompleto.charAt(i) != ' ') {  
               primeiroNome += nomeCompleto.charAt(i);  
               i++;  
           } else {  
               fim = true;  
           }  
        }   
        return primeiroNome; 
	}
	
	public static String getNomeCasal(Casal c){
		String marido = StringU.getPrimeiroNome(c.getMarido().getNome());
		String esposa = StringU.getPrimeiroNome(c.getEsposa().getNome());
		return marido+" & "+esposa;
	}
	
	public static String getMes(int value){
		switch (value) {
		case 1:return MESES.JANEIRO.getDescricao();
		case 2:return MESES.FEVEREIRO.getDescricao();
		case 3:return MESES.MARCO.getDescricao();
		case 4:return MESES.ABRIL.getDescricao();
		case 5:return MESES.MAIO.getDescricao();
		case 6:return MESES.JUNHO.getDescricao();
		case 7:return MESES.JULHO.getDescricao();
		case 8:return MESES.AGOSTO.getDescricao();
		case 9:return MESES.SETEMBRO.getDescricao();
		case 10:return MESES.OUTUBRO.getDescricao();
		case 11:return MESES.NOVEMBRO.getDescricao();
		case 12:return MESES.DEZEMBRO.getDescricao();			
		default:return "";
		}
	}
	
	public static String getParamPage(String nomeParam){
		Map<String,String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap(); 				
			return params.get(nomeParam);
	}
	
	public static String getSenhaAleatoria(int tamanho){  
		char[] chart ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};  
		  
		char[] senha= new char[tamanho];  
		  
		int chartLenght = chart.length;  
		Random rdm = new Random();  
		  
		for (int x=0; x<tamanho; x++)  
			senha[x] = chart[rdm.nextInt(chartLenght)];  		  
		return new String(senha);  
		}
	
	
}
