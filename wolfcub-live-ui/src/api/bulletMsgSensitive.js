import { get, postForm, postJson } from '@/utils/api'

const module = 'bulletMsgSensitive'

export function listData(params) {
  return get(`/${module}/list`, params)
}

export function saveOrUpdate(params) {
  return postJson(`/${module}/saveOrUpdate`, params)
}

export function deleteById(id) {
  return postForm(`/${module}/delete`, { id })
}
