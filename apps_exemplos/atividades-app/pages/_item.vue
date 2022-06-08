<template>
  <div>
    <Menu> </Menu>
    <b-container>
      <div class="painel-item">
        <img v-bind:src="desafios[item].urlPic" alt="">
        <h1>{{desafios[item].titulo}}</h1>

        <div class="">
          Título: {{desafios[item].titulo}}<br/>
          Quantidade de desafios: {{desafios[item].qtd}}
        </div>
      </div>
    </b-container>
  </div>
</template>

<script>
import ItemAtividade from "~/components/ItemAtividade.vue";
import Menu from "~/components/Menu.vue";
export default {
  components: { ItemAtividade, Menu },
  async asyncData({ route }){
    const { item } = route.params;
    return {item};
  }, 

  data() {
    return {
      desafios: [
        {
          id: 0,
          titulo: "Lista de Física",
          qtd: 2,
          urlPic:
            "https://visaoefoco.com.br/wp-content/uploads/2019/06/business-document-icon.png",
        },
        {
          id: 1,
          titulo: "Filmes",
          qtd: 4,
          urlPic:
            "https://cdn-icons-png.flaticon.com/512/125/125345.png",
        },
        {
          id: 2,
          titulo: "Lazer e Férias",
          qtd: 6,
          urlPic:
            "https://cdn-icons-png.flaticon.com/512/1942/1942396.png",
        },
        {
          id: 3,
          titulo: "Globo rural",
          qtd: 4,
          urlPic:
            "https://periodicos.sbu.unicamp.br/ojs/public/journals/3/cover_article_234_pt_BR.png",
        },
      ],
      id: 1,
    };
  },
};
</script>

<style>
.painel-item {
  height: fit-content;
  padding-left: 20px;
  padding-top: 10px;
  margin: 20px auto 0 auto;
  text-align: center;
}

img{
  max-width: 256px;
  max-height: 256px;
}
</style>