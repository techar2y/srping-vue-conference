/*import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


createApp(App).use(router, BootstrapVue).mount('#app')*/


import Vue from 'vue'
import App from './App.vue'
import router from './router'
/*import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'*/

import { BootstrapVue, BootstrapVueIcons  } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.devtools = true
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
