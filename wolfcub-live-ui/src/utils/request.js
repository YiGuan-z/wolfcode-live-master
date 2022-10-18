import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

const DEFAULT_ERROR_MSG = '服务器繁忙'

// 创建 axios 实例对象
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = 基础 url + 请求 uri
  // withCredentials: true, // 在跨域时发送 cookie 信息
  timeout: 5000 // 请求超时时间
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 当前必须有 token 才会携带 token
    // 判断 vuex 中是否有 token
    if (store.getters.token) {
      // 如果有 token, 从 cookie 中获取到 token
      // 将其设置到自定义的请求头 X-Token 中
      config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    // 请求出现错误
    console.log(error) // 请求错误信息
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    // response.data 是后端响应的数据
    // response.data = {code: 200, msg: "", data: {}}
    const res = response.data

    // 如果响应回来的状态码不等于 200, 就提示错误信息
    // 否则将响应的数据返回
    if (res.code !== 200) {
      // 提示错误消息
      Message({
        message: res.msg || DEFAULT_ERROR_MSG,
        type: 'error',
        duration: 5 * 1000
      })

      // 401: 当前用户未登录
      if (res.code === 401) {
        // TODO Vuex => Vue 组件状态(数据)管理
        store.dispatch('user/resetToken').then(() => {
          // 刷新页面
          location.reload()
        })
      }
      return Promise.reject(new Error(res.msg || DEFAULT_ERROR_MSG))
    } else {
      return res
    }
  },
  error => {
    console.log('err', error) // 打印错误消息用于 debug
    Message({
      message: error.msg || error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
