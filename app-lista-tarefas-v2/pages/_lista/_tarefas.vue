<template>
  <div>
    <Navegacao />
    <div class="mx-auto" style="max-width: 1000px; padding: 30px">
      <h1>{{ lista.titulo }}</h1>
      <h4>{{ lista.descricao }}</h4>
      <div>
        <b-button @click="showModal = true" pill variant="info">
          <b-icon icon="plus"></b-icon>
        </b-button>
      </div>
      <ul>
        <li :key="tarefa.id" v-for="tarefa in lista.tarefas">
          <b-form-checkbox size="sm" v-model="tarefa.checked">
            {{ tarefa.descricao }} - Prazo:
            {{ formatarData(new Date(tarefa.prazo)) }}
          </b-form-checkbox>
        </li>
      </ul>

      <b-modal id="modal-tarefa" size="lg" v-model="showModal" hide-footer>
        <h1>Nova tarefa</h1>
        <b-container fluid>
          <b-row class="my-1">
            <b-col sm="1">
              <label for="tarefa">Tarefa:</label>
            </b-col>
            <b-col sm="5">
              <b-form-input
                id="tarefa"
                size="sm"
                placeholder="Descreva a sua tarefa..."
                v-model="novaTarefa.descricao"
              ></b-form-input>
            </b-col>
            <b-col sm="1">
              <label for="prazo">Prazo:</label>
            </b-col>
            <b-col sm="5">
              <b-form-datepicker
                id="prazo"
                v-model="novaTarefa.prazo"
                :date-format-options="{
                  day: 'numeric',
                  month: 'numeric',
                  year: 'numeric',
                }"
                class="mb-2"
              >
              </b-form-datepicker>
            </b-col>
          </b-row>
        </b-container>
        <b-button class="mt-3" block variant="success" @click="criarNovaTarefa"
          >Criar Tarefa</b-button
        >
        <b-button class="mt-3" block variant="danger" @click="fecharModal">Fechar
        </b-button>
      </b-modal>
    </div>
  </div>
</template>

<script>
export default {
  async asyncData({ $axios, params }) {
    let lista
    try {
      // URL Final: http://localhost:8080/lista/{id}
      lista = await $axios.$get(`/listas/${params.tarefas}`)
      // data = response
    } catch (e) {
      //trata o erro se houver
      console.log(e)
    }
    console.log(JSON.stringify(lista))
    return { lista }
  },
  data: function () {
    return {
      showModal: false,
      novaTarefa: {
        descricao: '',
        prazo: '',
      },
      selecionados: [],
    }
  },
  methods: {
    async criarNovaTarefa(event) {
      //Enviar para o servidor a nova tarefa
      try {
        let dataTarefa = await this.$axios.$post(
          `/listas/${this.lista.id}/criar_tarefa`,
          this.novaTarefa
        )
        if (dataTarefa !== null) {
          this.fecharModal(event)
          this.novaTarefa = {
            tarefa: '',
            prazo: null,
          }
          this.atualizaListaTarefas();
        } else{
          throw new Error(`Tarefa para a lista de id ${this.lista.id} não foi criada!`);
        }
      } catch (error) {
        console.log(error)
      }
    },
    async atualizaListaTarefas(){
      this.lista = await this.$axios.$get(`/listas/${this.lista.id}`);
    },
    fecharModal(event) {
      this.$bvModal.hide('modal-tarefa')
    },
    formatarData(prazo) {
      return (prazo.getDate() + '/' + (prazo.getMonth() + 1) + '/' + prazo.getFullYear())
    },
  },
}
</script>
