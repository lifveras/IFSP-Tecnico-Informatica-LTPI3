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
          <NuxtLink :to="`/lista/${data.item.id}`">
            <span>{{ data.value }}</span>
          </NuxtLink>
        </template>
      </b-table>
    </div>
  </div>
</template>

<script>
export default {
  async asyncData({ $axios }) {
    let listas
    try {
      // URL Final: http://localhost:8080/listas
      listas = await $axios.$get('/listas')
      // data = response
    } catch (e) {
      //trata o erro se houver
      console.log(e)
    }
    console.log(JSON.stringify(listas));
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
        }
      ],
      iconsCategoryMap: {
        Escola: 'briefcase-fill',
        Trabalho: 'house-door-fill',
        Saúde: 'suit-heart-fill',
        Compras: 'cart3',
      },
      // listas: [
      //   {
      //     categoria: 'house-door-fill',
      //     titulo: 'Compras da casa',
      //     descricao: '',
      //     dataCriacao: '29/09/2021',
      //     id: 1,
      //   },
      //   {
      //     categoria: 'briefcase-fill',
      //     titulo: 'Lista comum',
      //     descricao: '',
      //     dataCriacao: '29/09/2021',
      //     id: 2,
      //   },
      //   {
      //     categoria: 'briefcase-fill',
      //     titulo: 'Compras da casa',
      //     descricao: '',
      //     dataCriacao: '29/09/2021',
      //     id: 3,
      //   },
      // ],
    }
  },
}
</script>
