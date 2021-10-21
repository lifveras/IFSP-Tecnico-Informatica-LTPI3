<template>
    <div>
        <Navegacao/>
        <div class="mx-auto" style="max-width: 1000px">
          <h1>Lista de tarefas</h1>
            <b-table striped responsive hover :items = "tarefas" :fields="fields">
            <template #cell(categoria)="data">
              <b-icon class="h2 mb-2" :icon="data.value"></b-icon>
            </template>
          </b-table>
      </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
              fields:[
                {
                  key: "categoria"
                },
                {
                  key: "titulo"
                },
                {
                  key: "dataCriacao",
                  label: "Criado em"
                },
              ],
              tarefas:[
                {categoria:"house-door-fill", titulo: "Compras da casa", descricao:"", dataCriacao: "29/09/2021"},
                {categoria:"briefcase-fill", titulo: "Compras da casa", descricao:"", dataCriacao: "29/09/2021"},
                {categoria:"briefcase-fill", titulo: "Compras da casa", descricao:"", dataCriacao: "29/09/2021"},
              ]
            }
        }
    }
</script>
