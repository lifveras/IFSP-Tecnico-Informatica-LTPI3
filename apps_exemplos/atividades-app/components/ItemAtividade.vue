<template>
  <div class="item-box">
    <div class="item-description">
      <img class="item-pic" :src="urlPic" alt="Imagem da Lista de atividades" />
      <div>
        <h3>{{ atvTtitulo }}</h3>
        <span>{{ qtdAtividades }} atividades faltando</span>
      </div>
    </div>
    <NuxtLink v-bind:to="`/${atvId}`" style="margin-right:8px">
      <b-icon icon="caret-right-fill" scale="3"></b-icon>
    </NuxtLink>
  </div>
</template>

<script>
export default {
  props: ["atvId", "atvTtitulo", "qtdAtividades", "urlPic"],
};
</script>

<style>
.item-box {
  padding: 10px;
  margin-top: 30px;
  border-color: black;
  border-style: solid;
  border-width: 0.1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.item-description{
  display: flex;
  align-items: center;
}

.item-pic {
  margin-right: 12px;
  height: 80px;
  width: 80px;
  padding: 10px;
  background-color: antiquewhite;
  border-radius: 100%;
}

h3 {
  font-size: 1.2rem;
}

h3 + span {
  font-size: 0.8rem;
}
</style>