<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="#">Lista de Tarefas</b-navbar-brand>

      <b-navbar-nav class="ml-auto">
        <b-nav-form>
          <b-button to="/nova-lista" size="sm" class="mr-sm-2">
                    Nova Lista
          </b-button>
        </b-nav-form>
        <b-nav-form>
          <b-button to="/" size="sm" class="mr-sm-2">
                Todas as Listas
          </b-button>
        </b-nav-form>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>