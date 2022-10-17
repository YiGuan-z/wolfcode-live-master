import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import '@/assets/styles/element-variables.scss'
import '@/assets/styles/index.scss'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n
import { resetForm } from './utils'
import plugins from './plugins' // plugins

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

Vue.use(ElementUI, { locale })
Vue.use(plugins)

Vue.config.productionTip = false

// 全局方法挂载
Vue.prototype.resetForm = resetForm

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
