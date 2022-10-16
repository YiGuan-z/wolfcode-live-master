import {get, postForm, postJson} from "@/utils/api";

const module = 'bulletMsg'

/**
 * listData 方法
 * @param params 形参: 实际的值取决于调用方法时传入的是什么, 这里我们期望 params = {...}
 * @returns {*}
 */
export function listData(params) {
  return get(`/${module}/list`, params)
}

export function changeStatus(id, status) {
  return postForm(`/${module}/changeStatus`, {id, status})
}

/**
 * saveOrUpdate 方法
 * @param params 形参: 实际的值取决于调用方法时传入的是什么, 这里我们期望 params = {...}
 * @returns {*}
 */
export function saveOrUpdate(params) {
  return postJson(`/${module}/saveOrUpdate`, params);
}

export function deleteById(id) {
  return postForm(`/${module}/delete`, {id});
}
