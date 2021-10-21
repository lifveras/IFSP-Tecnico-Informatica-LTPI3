import {ListaDataService} from '../services/ListaDataService'
import {LoginService} from '../services/LoginService'

export default ({$axios}, inject) => {
    inject("api_login", new LoginService($axios));
    inject("api_lista", new ListaDataService($axios));
}