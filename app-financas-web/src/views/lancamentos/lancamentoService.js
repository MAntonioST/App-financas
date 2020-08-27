import ApiService from '../apiservice'

class LancamentoService extends ApiService {

  constructor(){
      super('/api/lancamentos')
  }

  consultar(lancamentoFiltro){
     let  params = `?ano=${lancamentoFiltro.ano}`

     if(lancamentoFiltro.mes){
       params = `${params}&mes=${lancamentoFiltro.mes}`
     }
     if(lancamentoFiltro.tipo){
      params = `${params}&tipo=${lancamentoFiltro.tipo}`
     }
     if(lancamentoFiltro.status){
      params = `${params}&status=${lancamentoFiltro.status}`
     }
     return this.get(params);
  }
  obterSaldoPorUsuario(id){
    return this.get(`/${id}/saldo`);
  }

  salvar(usuario){
    return this.post('/save', usuario);
  }
}
export default LancamentoService