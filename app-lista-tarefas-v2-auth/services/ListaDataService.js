export class ListaDataService{
    constructor($axios){
        this.$axios = $axios;
    }

    async getAllListas(){
        return this.$axios.get(`/listas`)
    }

    async getListaById(idListas){
        return this.$axios.get(`/listas/${idListas}`)
    }

    async createLista(lista){
        return this.$axios.post(`/listas/criar`, lista)
    }

    async createTarefaToLista(idLista, tarefa){
        console.log("ID: ", idLista)
        return this.$axios.post(`/listas/${idLista}/criar_tarefa`, tarefa)
    }

    async deleteTarefa(idTarefa){
        return this.$axios.delete(`/listas/deleta_tarefa/${idTarefa}`)
    }

    async alterTarefa(idTarefa){
        return this.$axios.put(`/listas/alterar_tarefa/${idTarefa}`)
    }
}