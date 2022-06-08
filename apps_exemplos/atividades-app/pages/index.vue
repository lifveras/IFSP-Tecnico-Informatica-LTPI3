<template>
  <div>
    <b-container>
      <h1 class="titulo-pag">Crie sua conta</h1>
      <b-form class="form" @submit="onSubmit" @reset="onReset">
        <b-form-group id="input-group-1" label="Nome:" label-for="input-1">
          <b-form-input id="input-1" v-model="form.email" placeholder="Digite seu nome..." required>
          </b-form-input>
        </b-form-group>

        <b-form-group id="input-group-2" label="Email:" label-for="input-2">
          <b-form-input id="input-2" v-model="form.name" type="email" placeholder="Digite seu email..." required></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-3" label="Senha:" label-for="input-2">
          <b-form-input id="input-3" v-model="form.password" type="password" placeholder="Digite seu email..." required></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-4" v-slot="{ ariaDescribedby }">
          <b-form-checkbox-group v-model="form.checked" id="checkboxes-4" :aria-describedby="ariaDescribedby">
            <b-form-checkbox value="me"> Eu gostaria de receber suas notíticias e outras informações promocionais.</b-form-checkbox>
          </b-form-checkbox-group>
        </b-form-group>

        <b-button class = "btn_login" type="submit" variant="primary" v-on:click="criaCadastro">Sign-up</b-button>
        <a class="link">Esqueceu a senha?</a>
      </b-form>
    </b-container>

  </div>
</template>

<script>
export default {
  name: 'IndexPage',
  data() {
    return {
      form: {
        email: '',
        name: '',
        password: null
      },
      show: true
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      alert(JSON.stringify(this.form))
    },
    onReset(event) {
      event.preventDefault()
      // Reseta os valores do form
      this.form.email = ''
      this.form.name = ''
      this.form.password = null
      // Truque para resetar/limpar a validação nativa de formulários do navegador
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    },
    criaCadastro(){
      this.$router.push("/atividades");
    }
  }
}
</script>

<style>
.btn_login{
  background-color: #3b9c1b;
  width: 100%;
  border-radius: 2rem;
  margin-top: 20px;
}

.titulo-pag{
  text-align: center;
  margin: 10px auto 0 auto;
  display: block;
}

.link{
  color: #3b9c1b;
  font-size: 18px;
  font-weight: bold;
  display: block;
  text-align: center;
  margin-top: 15px;
}

.form {
  margin-top: 20px;
}

.form input{
  background: #F6F6F6
}
</style>