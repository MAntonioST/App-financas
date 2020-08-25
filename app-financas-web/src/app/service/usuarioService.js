import ApiService from '../apiservice'

class UsuarioService extends ApiService {

  constructor(){
      super('/api')
  }

  autenticar(credenciais){
    return this.post('/autenticar', credenciais);
  }
  obterSaldoPorUsuario(id){
    this.get(`/${id}/saldo`);
  }
}
export default UsuarioService