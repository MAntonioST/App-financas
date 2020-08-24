package com.app.financas.core.controler;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.financas.core.IFacade;
import com.app.financas.core.IStrategy;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.business.lancamento.AtualizaStatusLancamento;
import com.app.financas.core.business.lancamento.FindByLancamento;
import com.app.financas.core.business.lancamento.ValidLancamento;
import com.app.financas.core.business.usuario.AuthenticateUsuario;
import com.app.financas.core.business.usuario.FindBySaldo;
import com.app.financas.core.business.usuario.FindByUsuario;
import com.app.financas.core.business.usuario.ValidUsuario;
import com.app.financas.domain.DomainEntity;
import com.app.financas.domain.Lancamento;
import com.app.financas.domain.Usuario;



@SuppressWarnings("rawtypes")
@Component
public class AppFacade<T extends DomainEntity, R extends Object> implements IFacade<T, R> {

	private static final String SAVE = "SAVE";
	private static final String UPDATE = "UPDATE";
	private static final String FIND = "FIND";
	private static final String FINDALL = "FINDALL";
    
   
    @Autowired
    private FindByUsuario findByUsuario;
    @Autowired
    private AuthenticateUsuario authenticateUsuario;
    @Autowired
    private ValidUsuario validUsuario;
    @Autowired
    private FindBySaldo findBySaldo;
    @Autowired
    private ValidLancamento validLancamento;
    @Autowired
    private FindByLancamento findByLancamento;
    @Autowired
    private AtualizaStatusLancamento atualizaStatusLancamento;
   
	
    private Map<String, Map<String, List<IStrategy>>> rns;

    private BusinessCase<T> appBusinessCase;

 
	@PostConstruct
    public void initMap() {
      
        /* Intânciando o Map de Regras de Neg�cio */
        rns = new HashMap<String, Map<String, List<IStrategy>>>();
   
        // --------------------- Hash Usuario ------------------------------//

        // creating User rns save and update
        List<IStrategy> rnsSaveUsuario = new ArrayList<>();
        List<IStrategy> rnsFindSaldoUsuario = new ArrayList<>();
        List<IStrategy> rnsAutentUsuario = new ArrayList<>();
        List<IStrategy> rnsFindUsuario = new ArrayList<>();
       
        rnsSaveUsuario.add(validUsuario);
        rnsAutentUsuario.add(authenticateUsuario);
        rnsFindUsuario.add(findByUsuario);
        rnsFindSaldoUsuario.add(findBySaldo);
        
        
        Map<String, List<IStrategy>> rnsUsuario = new HashMap<>();
        rnsUsuario.put(SAVE, rnsSaveUsuario);
        rnsUsuario.put(FIND, rnsFindUsuario);
        rnsUsuario.put(UPDATE, rnsAutentUsuario);
        rnsUsuario.put(FINDALL, rnsFindSaldoUsuario);
      
        rns.put(Usuario.class.getName(), rnsUsuario);

        // --------------------- Hash Lancamento ------------------------------//
     // creating  RNS lancamento
        List<IStrategy> rnsSaveLancamento = new ArrayList<IStrategy>();
        List<IStrategy> rnsFindByLancamento = new ArrayList<IStrategy>();
        List<IStrategy> rnsAtualizaStatusLancamento = new ArrayList<IStrategy>();
    
        rnsSaveLancamento.add(validLancamento);
        rnsFindByLancamento.add(findByLancamento);
        rnsAtualizaStatusLancamento.add(atualizaStatusLancamento);
        
        
        Map<String, List<IStrategy>> rnsLancamento = new HashMap<String, List<IStrategy>>();
        rnsLancamento.put(SAVE, rnsSaveLancamento);
        rnsLancamento.put(FINDALL, rnsFindByLancamento);
        rnsLancamento.put(UPDATE, rnsAtualizaStatusLancamento);
        rns.put(Lancamento.class.getName(), rnsLancamento);

    }

	
	@Override
	public  BusinessCase<T> save(T aEntity) {
		appBusinessCase = new BusinessCase<T>();  
		appBusinessCase = executeRules(aEntity, "SAVE");
        if(appBusinessCase.hasMsg()){
            String msg = appBusinessCase.getMsg();
            appBusinessCase.setMsg(msg); 
        }
        return appBusinessCase;
	}

	@Override
	public  BusinessCase<T> update(T aEntity) {
		appBusinessCase = new BusinessCase<T>();  
		appBusinessCase = executeRules(aEntity, "UPDATE");
        if(appBusinessCase.hasMsg()){
            String msg = appBusinessCase.getMsg();
            appBusinessCase.setMsg(msg); 
        }
        return appBusinessCase;
	}

	@Override
	public  BusinessCase<T> delete(T aEntity) {
		appBusinessCase = new BusinessCase<T>();  
		appBusinessCase = executeRules(aEntity, "DELETE");
        if(appBusinessCase.hasMsg()){
            String msg = appBusinessCase.getMsg();
            appBusinessCase.setMsg(msg); 
        }
        return appBusinessCase;
	}

	
	@Override
	public BusinessCase<T> findAll(T aEntity) {
		appBusinessCase = new BusinessCase<T>();  
		appBusinessCase = executeRules(aEntity, "FINDALL");
        if(appBusinessCase.hasMsg()){
            String msg = appBusinessCase.getMsg();
            appBusinessCase.setMsg(msg); 
        }
        return appBusinessCase;
	}

	@Override
	public BusinessCase<T> find(T aEntity) {
		appBusinessCase = new BusinessCase<T>();  
		appBusinessCase = executeRules(aEntity, "FIND");
        if(appBusinessCase.hasMsg()){
            String msg = appBusinessCase.getMsg();
            appBusinessCase.setMsg(msg); 
        }
        return appBusinessCase;
	}
  
	@SuppressWarnings("unchecked")
	private BusinessCase<T> executeRules(T aEntity, String stAction) {
        String nmClass = aEntity.getClass().getName();
        Map<String, List<IStrategy>> rulesAction = rns.get(nmClass);
        if (rulesAction != null) {
            List<IStrategy> rules = rulesAction.get(stAction);
            if (rules != null) {
                for (IStrategy<T> s : rules) {
                	appBusinessCase = s.process(aEntity, appBusinessCase);
        			
                    if(!appBusinessCase.hasMsg()){
                        return appBusinessCase;
                    }else {
	                    appBusinessCase.getMsg();
	                    return appBusinessCase;
                    }
                }
            }   
        }
                return null;  		
    }

}
