import { get, postForm, postJson } from '@/utils/api'

const module = 'user'

/**
 * listData 方法
 * @param params 形参: 实际的值取决于调用方法时传入的是什么, 这里我们期望 params = {...}
 * @returns {*}
 */
export function listData(params) {
  return get(`/${module}/list`, params)
}

export function changeUserStatus(id, status) {
  return postForm(`/${module}/changeStatus`, { id, status })
}

/**
 * saveOrUpdate 方法
 * @param params 形参: 实际的值取决于调用方法时传入的是什么, 这里我们期望 params = {...}
 * @returns {*}
 */
export function saveOrUpdate(params) {
  return postJson(`/${module}/saveOrUpdate`, params)
}

export function deleteById(id, deleted) {
  return postForm(`/${module}/delete`, { id, deleted })
}

export function todayData() {
  return postForm(`/todayData`)
}
