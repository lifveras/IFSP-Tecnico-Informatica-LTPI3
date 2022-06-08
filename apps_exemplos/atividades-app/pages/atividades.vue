<template>
  <div>
    <Menu> </Menu>
    <b-container>
      <div>
        <h3>Atividades</h3>
        <div class="painel-popular">
          <span>Popular</span>
          <span>Novo desafio deste mês</span>
          <div>
            <div class="qtd-tarefas">8</div>
            <span>Tarefas para agora</span>
          </div>
        </div>
      </div>

      <div>
        <h3>Todos os desafios de</h3>
        <div :key="atividade.id" v-for="atividade in desafios">
          <item-atividade
            v-bind:atvId = "atividade.id"
            :atvTtitulo="atividade.titulo"
            :qtdAtividades="atividade.qtd"
            :urlPic="atividade.urlPic"
          ></item-atividade>
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
    };
  },
};
</script>

<style>
.painel-popular {
  height: fit-content;
  padding-left: 20px;
  padding-top: 10px;
  background-color: #78e2fa;
  border-radius: 0.5rem;
}

.painel-popular span {
  color: #ffffff;
  display: block;
}

.painel-popular > span:first-child {
  text-transform: uppercase;
  opacity: 70%;
}

.painel-popular > span:nth-child(2) {
  margin-top: 10px;
  font-weight: bolder;
}

.painel-popular > div {
  display: flex;
  margin: 20px auto 20px auto;
  align-items: center;
  justify-content: flex-start;
  padding-bottom: 20px;
}

.qtd-tarefas {
  text-align: center;
  padding: 10px;
  height: 60px;
  width: 60px;
  background-color: #cae3e8;
  border-radius: 100%;
  color: white;
  font-size: 1.4rem;
  margin-right: 20px;
}
</style>