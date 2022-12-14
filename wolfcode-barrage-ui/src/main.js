import Vue from 'vue'
import App from './App.vue'
import 'ant-design-vue/lib/message/style'
import {message} from "ant-design-vue"
import './plugins/element.js'

Vue.prototype.$message = message

new Vue({
    render: h => h(App),
}).$mount('#app')

