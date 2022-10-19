import axios from 'axios'
import {message} from 'ant-design-vue'
import config from "../config";

const DEFAULT_ERROR_MSG = '服务器繁忙'

// 创建 axios 实例对象
const service = axios.create({
    baseURL: '/api', // url = 基础 url + 请求 uri
    // withCredentials: true, // 在跨域时发送 cookie 信息
    timeout: 5000 // 请求超时时间
})

// 请求拦截器
service.interceptors.request.use(
    config => {
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
            message.error(res.msg || DEFAULT_ERROR_MSG)

            // 401: 当前用户未登录
            if (res.code === 401) {
                config.userId = undefined
            }
            return Promise.reject(new Error(res.msg || DEFAULT_ERROR_MSG))
        } else {
            return res
        }
    },
    error => {
        console.log('err', error) // 打印错误消息用于 debug
        let {response} = error
        let msg = response.statusText || error.msg || error.message
        if (response.status === 404) {
            msg = '404 找不到资源'
        } else if (response.status === 400) {
            msg = '参数出错啦'
        } else if (response.status === 500) {
            msg = '服务器炸啦'
        }
        message.error(msg)
        return Promise.reject(error)
    }
)

export default service
