package br.org.ovelha.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.Casal;
import br.org.ovelha.domain.Discipulo;
import br.org.ovelha.domain.Filho;
import br.org.ovelha.domain.Homem;
import br.org.ovelha.domain.Mulher;
import br.org.ovelha.domain.Sexo;
import br.org.ovelha.domain.dto.DatasComemorativas;
import br.org.ovelha.persistence.DiscipuloDAO;
import br.org.ovelha.util.CDIFactory;
import br.org.ovelha.util.Data;
import br.org.ovelha.util.StringU;

@BusinessController
public class DiscipuloBC extends DelegateCrud<Discipulo, Long, DiscipuloDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	HomemBC homemBC;

	@Inject
	MulherBC mulherBC;
	
	@Inject
	FilhoBC filhoBC;

	public void inserirDiscipulos(Casal casal) {
		Date data = Data.dataAtual();

		Homem marido = casal.getMarido();
		marido.setDataRegistro(data);
		marido.setSexo(Sexo.M);
		marido.setIdCasal(casal.getId());

		Mulher mulher = casal.getEsposa();
		mulher.setDataRegistro(data);
		mulher.setSexo(Sexo.F);
		mulher.setEndereco(marido.getEndereco());
		mulher.setTelefoneResidencial(marido.getTelefoneResidencial());
		mulher.setIdCasal(casal.getId());			

		//Insere Marido e Esposa 
		CDIFactory.getHomemDAO().insert(marido);
		CDIFactory.getMulherDAO().insert(mulher);

		//Insere os filhos
		if (casal.getFilhos()!=null && casal.getFilhos().size()>0){
			List<Filho> filhos = (List<Filho>) casal.getFilhos();
			for (Filho filho:filhos){
				filho.setIdCasal(casal.getId());
				filho.setDataRegistro(data);				
			}
			CDIFactory.getFilhoDAO().insert(filhos);
		}
	}


	public void atualizarDiscipulos(Casal bean) {

		homemBC.update(bean);		
		mulherBC.update(bean);
		
		for(Filho filho:bean.getFilhos()){
			if((filho.getId()!=null) && (filho.getId()>0)){
				filho.setDataAtualizacaoRegistro(Data.dataAtual());
				filhoBC.update(filho);
				
			}else{
				filho.setDataRegistro(Data.dataAtual());
				filho.setIdCasal(bean.getIdCasal());
				filhoBC.insert(filho);
			}
		}				
	} 

	public void apagarDiscipulos(Casal bean) {

		homemBC.delete(bean);
		mulherBC.delete(bean);
	}

	public List<DatasComemorativas> obterDatasComemorativasMes(){

		List<Homem> homens = (List<Homem>) CDIFactory.getHomemDAO().obterAniversariantesMes();
		List<Mulher> mulheres = (List<Mulher>) CDIFactory.getMulherDAO().obterAniversariantesMes();
		List<Casal> casais = (List<Casal>) obterAniversariosCasamentoMes();

		List<DatasComemorativas> datas = new ArrayList<DatasComemorativas>();

		if (homens.size()>0){
			for (Homem h:homens){
				DatasComemorativas dataH = new DatasComemorativas();
				dataH.setNome(h.getNome());
				dataH.setData(h.getDataAniversario());
				dataH.setEvento(dataH.getNascimento());
				datas.add(dataH);
			}
		}
		if (mulheres.size()>0){
			for (Mulher m:mulheres){
				DatasComemorativas dataM = new DatasComemorativas();
				dataM.setNome(m.getNome());
				dataM.setData(m.getDataAniversario());
				dataM.setEvento(dataM.getNascimento());
				datas.add(dataM);
			}
		}
		if (casais.size()>0){
			for (Casal c:casais){
				DatasComemorativas dataC = new DatasComemorativas();
				dataC.setNome(StringU.getNomeCasal(c));
				dataC.setData(c.getDataAniversarioCasamento());
				dataC.setEvento(dataC.getCasamento());
				datas.add(dataC);
			}
		}

		return datas;
	}


	private Collection<Casal> obterAniversariosCasamentoMes(){
		Collection<Casal> c = CDIFactory.getCasalDAO().obterAniversariosCasamentoMes();
		Collection<Casal> casais = new ArrayList<Casal>();

		if (c.size()>0){
			for (Casal casal:c){
				Homem h = CDIFactory.getDiscipuloDAO().obterHomen(casal.getIdCasal());
				Mulher m = CDIFactory.getDiscipuloDAO().obterMulher(casal.getIdCasal());
				casal.setMarido(h);
				casal.setEsposa(m);
				casais.add(casal);
			}
		}

		return casais;
	}


}
