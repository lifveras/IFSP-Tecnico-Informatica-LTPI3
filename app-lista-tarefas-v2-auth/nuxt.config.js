export default {
  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'teste-nuxt',
    htmlAttrs: {
      lang: 'en'
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '~/plugins/api.js'
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/bootstrap
    'bootstrap-vue/nuxt',
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
    // https://auth.nuxtjs.org/
    '@nuxtjs/auth'
  ],

  bootstrapVue: {
    icons: true
  },
  //...
  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios: {
    baseURL: 'http://localhost:8080', //altera para a URL da sua API
    headers: {
      'Content-Type': 'application/json'
    }
  },
  //...
  // Auth module configuration: https://go.nuxtjs.dev/config-auth
  auth: {
    strategies: {
      local: {
        user: {
          autoFetch: false,
        },
        // https://auth.nuxtjs.org/schemes/local
        endpoints: {
          login: { url: '/login/auth', method: 'post', propertyName: "data.token" },
          logout: false,
          user: false,
        }
      }
    },
    redirect: {
      login: '/',
      logout: '/',
      callback: '/',
      home: '/'
    }
  },
  router: {
    middleware: ['auth']
  },
  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
  }
}
