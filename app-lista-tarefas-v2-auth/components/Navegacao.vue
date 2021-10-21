<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="/">Lista de Tarefas</b-navbar-brand>
      
      <b-navbar-nav>
        <b-nav-item @click.prevent="logout">Logout</b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
        <b-nav-form>
          <b-button to="/nova-lista" size="sm" class="mr-sm-2">
                    Nova Lista
          </b-button>
        </b-nav-form>
        <b-nav-form>
          <b-button to="/listas" size="sm" class="mr-sm-2">
                Todas as Listas
          </b-button>
        </b-nav-form>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {
  methods:{
    async logout(){
      await this.$auth.logout();
    }
  }
}
</script>
