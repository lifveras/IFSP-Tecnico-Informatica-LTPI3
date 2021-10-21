<template>
  <div class="container">
    <div>
      <img alt="App de tarefas" src="/figura-login.png" />
    </div>
    <div class="login">
      <h2>App de</h2>
      <h1>Lista de tarefas</h1>
      <div class="login-form">
        <b-form @submit.prevent="doLogin">
          <b-form-group
            id="username-group"
            label="Nome de usuário:"
            label-for="username"
          >
            <b-form-input
              id="username"
              v-model="loginForm.username"
              placeholder="Entre com o nome de usupario"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="password-group"
            label="Senha: "
            label-for="password"
          >
            <b-form-input
              id="password"
              v-model="loginForm.password"
              type="password"
              placeholder="Insira sua senha"
              required
            ></b-form-input>
          </b-form-group>
          <b-button type="submit">Entrar</b-button>
          <b-button to="/cadastro-usuario">Cadastrar novo usuário</b-button>
        </b-form>
      </div>
      <b-alert variant="danger" show v-if="error">
        <h4 class="alert-heading">Falha no login.</h4>
        {{ this.error }}
      </b-alert>
    </div>
  </div>
</template>

<script>
export default {
  auth: false,
  data: function () {
    return {
      // Deve estar de acordo com o padrão
      loginForm: {
        username: 'ifsp',
        password: '1234',
      },
      error: false,
    }
  },
  methods: {
    doLogin: async function () {
      try {
        console.log(this.loginForm)
        console.log(JSON.stringify(this.loginForm))
        // await this.$api_login.doLogin(JSON.stringify(this.loginForm))
        // auth só armazenará na memória o login caso a API retorne o token
        const response = await this.$auth.loginWith('local', {
          data: this.loginForm,
        })

        if (this.$auth.loggedIn) {
          console.log('Successfully Logged In')
          // console.log(this.$auth.strategy.token)
          // console.log(this.$auth.strategy)

          // Problema de armazenamento de token resolvido com:
          // https://dev.to/mrnaif2018/how-to-make-nuxt-auth-working-with-jwt-a-definitive-guide-9he 
          //this.$auth.setToken('local', response.data.access_token)
          this.$router.push('/listas')
        }
      } catch (error) {
        console.log(error)
        this.error = error
      }
    },
  },
}
</script>

<style scoped>
.container {
  padding-top: 100px;
  text-align: center;
  display: flex;
  justify-content: center;
}

.login {
  margin-left: 100px;
}

.login h2 {
  color: chocolate;
}

.login h1 {
  color: dodgerblue;
}

.login-form {
  padding: 20px;
  margin-top: 20px;
  border-style: solid;
  border-radius: 2%;
  border-color: gray;
  /* border: 2px; */
}
</style>