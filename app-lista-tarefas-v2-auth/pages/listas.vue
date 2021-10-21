<template>
  <div>
    <Navegacao />
    <div class="mx-auto" style="max-width: 1000px; padding: 30px">
      <h1>Lista de tarefas</h1>
      <b-table striped responsive hover :items="listas" :fields="fields">
        <template #cell(categoria)="data">
          <b-icon class="h2 mb-2" :icon="iconsCategoryMap[data.value]"></b-icon>
        </template>
        <template #cell(titulo)="data">
            <NuxtLink :to="`/${data.item.titulo}/${data.item.id}`">    
                <span>{{data.value}}</span>
            </NuxtLink>
        </template>
      </b-table>
    </div>
  </div>
</template>

<script>
export default {
  async asyncData({ app: { $api_lista } }) {
    let listas
    try {
      listas = await $api_lista.getAllListas()
    } catch (e) {
      console.log('Erro')
      console.log(e.response)
    }
    listas = listas['data']
    return { listas }
  },

  data: function () {
    return {
      fields: [
        {
          key: 'categoria',
        },
        {
          key: 'titulo',
        },
        {
          key: 'descricao',
          label: 'Descrição',
        },
      ],
      iconsCategoryMap: {
        Escola: 'briefcase-fill',
        Trabalho: 'house-door-fill',
        Saúde: 'suit-heart-fill',
        Compras: 'cart3',
      },
      listasOld: [
        {
          categoria: 'house-door-fill',
          titulo: 'Compras da casa',
          descricao: '',
          dataCriacao: '29/09/2021',
          id: 1,
        },
        {
          categoria: 'briefcase-fill',
          titulo: 'Lista comum',
          descricao: '',
          dataCriacao: '29/09/2021',
          id: 2,
        },
        {
          categoria: 'briefcase-fill',
          titulo: 'Compras da casa',
          descricao: '',
          dataCriacao: '29/09/2021',
          id: 3,
        },
      ],
    }
  },
  methods: {
    navigateToTarefas: function (path, listaObject) {
      const locationObject = {
        path: path,
        lista: listaObject,
      }
      console.log(locationObject)
      //this.$router.push(locationObject);
    },
  },
}
</script>
