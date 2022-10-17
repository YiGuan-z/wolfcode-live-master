import { get, postForm, postJson } from '@/utils/api'
import qs from 'qs'

const module = 'employee'

/**
 * listData 方法
 * @param params 形参: 实际的值取决于调用方法时传入的是什么, 这里我们期望 params = {...}
 * @returns {*}
 */
export function listData(params) {
  return get(`/${module}/list`, params)
}

/**
 * saveOrUpdate 方法
 * @param params 形参: 实际的值取决于调用方法时传入的是什么, 这里我们期望 params = {...}
 * @returns {*}
 */
export function saveOrUpdate(params) {
  return postJson(`/${module}/saveOrUpdate`, params)
}

export function deleteById(id) {
  return postForm(`/${module}/delete`, qs.stringify({ id }))
}
