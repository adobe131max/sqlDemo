import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import './assets/css/global.css'
import './theme/index.css'

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(ElementUI)

new Vue({
    render: h => h(App),
    beforeCreate() {
        Vue.prototype.$bus = this
    },
    router: router
}).$mount('#app')