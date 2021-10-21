<template>
  <div>
    <div class="mx-auto" style="max-width: 1000px; padding: 30px">
      <h1>Criar um novo usuário</h1>
      <b-form @submit="criarUsuario">
        <b-form-group
          id="username-group"
          label="Nome de usuário:"
          label-for="username"
        >
          <b-form-input
            id="username"
            v-model="novoUsuario.username"
            placeholder="Entre com o nome de usuário"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="email-group" label="Email:" label-for="email">
          <b-form-input
            id="email"
            v-model="novoUsuario.email"
            placeholder="Entre com o Email"
            type="email"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="password-group" label="Senha: " label-for="password">
          <b-form-input
            id="password"
            v-model="novoUsuario.password"
            type="password"
            placeholder="Insira sua senha"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="password-repeat-group"
          label="Repita a senha: "
          label-for="password-repeat"
        >
          <b-form-input
            id="password-repeat"
            v-model="passwordRepeat"
            type="password"
            placeholder="Insira sua senha novamente"
            required
          ></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="primary">Criar</b-button>
        <b-button type="reset" variant="danger" @click="cancelarCadastro"
          >Cancelar</b-button
        >
      </b-form>
    </div>
  </div>
</template>

<script>
export default {
  auth: false,
  data: function () {
    return {
      novoUsuario: {
        username: '',
        password: '',
        email: '',
      },
      passwordRepeat: '',
    }
  },
  methods: {
    criarUsuario(event) {
      // console.log(this.novoUsuario)
      event.preventDefault()
      //implementar o envio da nova lista
      if (
        this.novoUsuario.password !== '' &&
        this.novoUsuario.password === this.passwordRepeat
      ) {
        //enviar para o servidor
        console.log(this.novoUsuario)
        this.$api_login
          .createLogin(JSON.stringify(this.novoUsuario))
          .then(
            this.$bvModal.msgBoxOk('O novo usuário foi criado com sucesso', {
              title: 'Confirmation',
              size: 'sm',
              buttonSize: 'sm',
              okVariant: 'success',
              headerClass: 'p-2 border-bottom-0',
              footerClass: 'p-2 border-top-0',
              centered: true,
            }).then(() => this.$router.push('/'))
          )          
          .catch((error) => {
            console.log(error)
          })
      } else {
        alert(
          `As senhas não são iguais. Digite a mesma senha nos dois campos! ${this.novoUsuario.password} -- ${this.passwordRepeat}`
        )
      }
    },
    cancelarCadastro() {
      this.$router.push('/')
    },
  },
}
</script>
