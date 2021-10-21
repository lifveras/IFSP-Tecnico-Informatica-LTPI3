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
        <li :key="tarefa.id" v-for="tarefa in tarefas">
          <b-form-checkbox size="sm" v-model="tarefa.checked">
            {{ tarefa.tarefa }} - Prazo:
            {{ tarefa.prazoTarefa }}</b-form-checkbox
          >
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
                v-model="novaTarefa.tarefa"
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
        <b-button class="mt-3" block variant="danger" @click="fecharModal"
          >Fechar</b-button
        >
      </b-modal>
    </div>
  </div>
</template>

<script>
export default {
  asyncData( {params} ){
    let tarefas = [];
    if (params.tarefas === 'Compras da casa') {
      tarefas = [
        {
          id: 1,
          checked: false,
          tarefa: 'Uma tarefa da lista',
          prazoTarefa: '10/10/2021',
        },
        {
          id: 2,
          checked: false,
          tarefa: 'Uma tarefa da lista',
          prazoTarefa: '10/10/2021',
        },
        {
          id: 3,
          checked: false,
          tarefa: 'Uma tarefa da lista',
          prazoTarefa: '10/10/2021',
        },
      ]
    } else {
      tarefas = [
        {
          id: 1,
          checked: false,
          tarefa: 'Tarefa genérica',
          prazoTarefa: '01/01/2022',
        },
      ]
    }

    return {tarefas};
  },

  // Também podemos pegar no data os valores dos parâmetros passado pela rota.
  // data: function () {
  //   let tarefas = []
  //   console.log(this.$route)
  //   const params = this.$route.params
  //   if (params.tarefas === 'Compras da casa') {
  //     tarefas = [
  //       {
  //         id: 1,
  //         checked: false,
  //         tarefa: 'Uma tarefa da lista',
  //         prazoTarefa: '10/10/2021',
  //       },
  //       {
  //         id: 2,
  //         checked: false,
  //         tarefa: 'Uma tarefa da lista',
  //         prazoTarefa: '10/10/2021',
  //       },
  //       {
  //         id: 3,
  //         checked: false,
  //         tarefa: 'Uma tarefa da lista',
  //         prazoTarefa: '10/10/2021',
  //       },
  //     ]
  //   } else {
  //     tarefas = [
  //       {
  //         id: 1,
  //         checked: false,
  //         tarefa: 'Tarefa genérica',
  //         prazoTarefa: '01/01/2022',
  //       },
  //     ]
  //   }

  data: function(){
    return {
      showModal: false,
      lista: {
        titulo: 'Minha lista de tarefas',
        descricao: 'Lista nova de tarefas',
      },
      novaTarefa: {
        tarefa: '',
        prazo: '',
      },
      selecionados: [],
    }
  },
  methods: {
    criarNovaTarefa(event) {
      //Enviar para o servidor a nova tarefa
      this.tarefas.push({
        id: null,
        checked: false,
        tarefa: this.novaTarefa.tarefa,
        prazoTarefa: this.novaTarefa.prazo,
      })
      console.log(this.tarefas[this.tarefas.length - 1])
      this.fecharModal(event)
    },
    fecharModal(event) {
      this.novaTarefa = {
        tarefa: '',
        prazo: null,
      }
      this.$bvModal.hide('modal-tarefa')
    },
  },
}
</script>
