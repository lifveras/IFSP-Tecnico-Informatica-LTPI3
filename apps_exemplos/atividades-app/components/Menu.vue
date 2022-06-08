<template>
  <div class="menu-component">
      <div class="menu">
        <a class="hamburguer-menu" v-on:click="toggleMenu">
            <div></div>
            <div></div>
            <div></div>
        </a>
      </div>
      <div class="menu-bar">
            <a href="/">Home</a>
            <a href="/atividades">Atividades</a>
            <a href="#">Menu 3</a> 
            <a href="#">Menu 4</a>
      </div>
  </div>
</template>

<script>
export default {
  methods:{
    toggleMenu(){
      const divMenu = document.querySelector(".menu-bar");
      divMenu.classList.toggle("show-menu");
    }
  }
};
</script>

<style>

.menu-component{
  position: relative;
  margin-bottom: 20px;
  box-shadow: 0px 2px 5px gray;
}

.menu {
  height: 100px;
  background-color: coral;
  display: flex;
  align-items: center;
}

.hamburguer-menu > div {
  height: 8px;
  width: 50px;
  margin: 5px 0 5px 15px;
  background-color: black;
  display: block;
}
.menu::before {
  height: 20px;
  width: 200px;
  background-color: rgb(204, 199, 199);
  display: block;
}

.menu-bar{
    background-color: chocolate;
    display: flex;
    position: fixed;
    flex-flow: column;
    height: 100vh;
    width: 200px;
    left: -200px;
    /* Necessário para evitar que elementos transparentes fique por cima */
    z-index: 1; 
}

.show-menu{
  left: 0px;
}

.show:active[true]{
    left: -200px;
}

.show:active{
    left: 0
}

.menu-bar a{
    margin-left: 40px;
    margin-bottom: 20px;
    font-size: 30px;
}
</style>