<template>
  <div>
    <Navegacao />
    <div class="mx-auto" style="max-width: 1000px; padding: 30px">
      <h1>Adicionar nova lista</h1>
      <b-form @submit="criarLista">
        <b-form-group
          id="input-group-1"
          label="Título da nova lista:"
          label-for="titulo"
        >
          <b-form-input
            id="titulo"
            v-model="form.titulo"
            placeholder="Entre com um título"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="input-group-2"
          label="Categoria:"
          label-for="categoria"
        >
          <b-form-select
            id="categoria"
            v-model="form.categoria"
            :options="categorias"
            :value="null"
            required
          ></b-form-select>
        </b-form-group>

        <b-form-group
          id="input-group-3"
          label="Descrição:"
          label-for="descricao"
        >
          <b-form-textarea
            id="descricao"
            v-model="form.descricao"
            placeholder="Digite uma descrição..."
            rows="3"
            no-resize
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary">Criar</b-button>
        <b-button type="reset" variant="danger">Limpar</b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      form: {
        titulo: '',
        categoria: '',
        descricao: '',
      },
      categorias: [
        { text: 'Selecione uma categoria', value: null },
        'Escola',
        'Trabalho',
        'Saúde',
        'Compras',
      ],
    }
  },
  methods: {
    criarLista(event) {
      event.preventDefault();
      //implementar o envio da nova lista
      console.log(this.form)
      this.$bvModal.msgBoxOk('A nova lista foi criada com sucesso', {
          title: 'Confirmation',
          size: 'sm',
          buttonSize: 'sm',
          okVariant: 'success',
          headerClass: 'p-2 border-bottom-0',
          footerClass: 'p-2 border-top-0',
          centered: true
      })
      this.limparCampos()
    },
    limparCampos() {
      this.form = {
        titulo: '',
        categoria: '',
        descricao: '',
      }
    },
  },
}
</script>
