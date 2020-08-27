import ApiService from '../apiservice'

class UsuarioService extends ApiService {

  constructor(){
      super('/api')
  }

  autenticar(credenciais){
    return this.post('/autenticar', credenciais);
  }
  obterSaldoPorUsuario(id){
    return this.get(`/${id}/saldo`);
  }

  salvar(usuario){
    return this.post('/save', usuario);
  }
}
export default UsuarioService