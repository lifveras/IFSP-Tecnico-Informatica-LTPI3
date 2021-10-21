
export class LoginService{
    constructor($axios){
        this.$axios = $axios;
    }

    async doLogin(credentials){
        return this.$axios.post(`/login/auth`, credentials)
    }

    async createLogin(userData){
        return this.$axios.post(`/login/create`, userData)
    }
}