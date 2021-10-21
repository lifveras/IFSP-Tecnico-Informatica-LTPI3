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
            {{ formatarData(new Date(tarefa.prazo)) }}</b-form-checkbox
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
        <b-button class="mt-3" block variant="danger" @click="fecharModal"
          >Fechar</b-button
        >
      </b-modal>
      <!-- <b-form-group
        label="Form-checkbox-group stacked checkboxes">
        <b-form-checkbox-group
          v-model="selecionados"
          :options="options"
          :aria-describedby="ariaDescribedby"
          name="flavour-2a"
          stacked
        ></b-form-checkbox-group>
      </b-form-group> -->
    </div>
  </div>
</template>

<script>
export default {
  // Os plugins deve iniciasr com $
  // Os parâmetros e outros objetos Router Link não precisam.
  async asyncData({ $api_lista, params }) {
    let lista
    try {
      lista = (await $api_lista.getListaById(params.tarefas)).data
      console.log(lista)
    } catch (e) {
      console.log(e.response)
    }
    return { lista }
  },
  data: function () {
    /** Removido da versão anterior*/
    // $api_lista.getTarefas($params.)

    // if (params.tarefas === 'Compras da casa') {
    //   tarefas = [
    //     {
    //       id: 1,
    //       checked: false,
    //       tarefa: 'Uma tarefa da lista',
    //       prazoTarefa: '10/10/2021',
    //     },
    //     {
    //       id: 2,
    //       checked: false,
    //       tarefa: 'Uma tarefa da lista',
    //       prazoTarefa: '10/10/2021',
    //     },
    //     {
    //       id: 3,
    //       checked: false,
    //       tarefa: 'Uma tarefa da lista',
    //       prazoTarefa: '10/10/2021',
    //     },
    //   ]
    // } else {
    //   tarefas = [
    //     {
    //       id: 1,
    //       checked: false,
    //       tarefa: 'Tarefa genérica',
    //       prazoTarefa: '01/01/2022',
    //     },
    //   ]
    // }

    // console.log("Objeto params:" + params)

    return {
      showModal: false,
      /** Removido da versão anterior*/
      // lista: {
      //   titulo: 'Minha lista de tarefas',
      //   descricao: 'Lista nova de tarefas',
      // },
      novaTarefa: {
        descricao: '',
        prazo: '',
      },
      selecionados: [],
      // tarefas: tarefas,
    }
  },
  methods: {
    async criarNovaTarefa(event) {
      //Enviar para o servidor a nova tarefa
      if (this.novaTarefa !== '') {
        console.log(this.lista.id)
        this.$api_lista.createTarefaToLista(this.lista.id, this.novaTarefa)
        await this.atualizaListaTarefas()
        this.fecharModal(event)
      }
    },

    //Função para atualizar a lista de tarefas após a criação de uma nova tarefa
    async atualizaListaTarefas() {
      //Delay de 1s para que o servidor, internamente, salve nossa tarefa.
      await new Promise(resolve => setTimeout(resolve, 1000));
      this.lista = (await this.$api_lista.getListaById(this.lista.id)).data
    },

    //Função para fechar o Modal
    fecharModal(event) {
      this.novaTarefa = {
        tarefa: '',
        prazo: null,
      }
      this.$bvModal.hide('modal-tarefa')
    },
    formatarData: function (data) {
      return (
        data.getDate() + '/' + (data.getMonth() + 1) + '/' + data.getFullYear()
      )
    },
  },
}
</script>
