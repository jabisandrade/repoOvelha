package br.org.ovelha.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class Data {
	
	public static final Locale BRASIL = new Locale("pt","BR");
	public static final String DATA_EXTENSO = "EEE, d MMM yyyy";
	public static final String DATA_HORA_EXTENSO = "EEE, d MMM yyyy HH:mm:ss";	
	public static final String MES_NUMERO = "MM";
	public static final String ANO_NUMERO = "yyyy";
	public static final String DIA_MES_NUMERO = "dd/MM";
	
	public static Date inicioMes() {
    	Calendar inicio = Calendar.getInstance();
        inicio.set(Calendar.DAY_OF_MONTH, 1);
        return inicio.getTime();
	}
	
	public static Date finalMes() {
        Calendar fim = Calendar.getInstance();
        fim.set(Calendar.DAY_OF_MONTH,fim.getActualMaximum(Calendar.DAY_OF_MONTH));
        return fim.getTime();
	}
	
	public static Integer mesAtual() {
		return Integer.parseInt(new SimpleDateFormat(MES_NUMERO).format(new Date()));
	}
	
	public static Integer anoAtual() {
		return Integer.parseInt(new SimpleDateFormat(ANO_NUMERO).format(new Date()));
	}
	
	public static String dataDiaMes(Date data) {
		return new SimpleDateFormat(DIA_MES_NUMERO).format(data);
	}
	
	public static Date dataAtual(){
		return new Date();
	}
	
	public static String dataExtenso(){
		return new SimpleDateFormat(DATA_EXTENSO,BRASIL).format(new Date());
	}
	
	public static String dataHoraExtenso(Date date){
		return new SimpleDateFormat(DATA_HORA_EXTENSO,BRASIL).format(date);
	}
	
	
}