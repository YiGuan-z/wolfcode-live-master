import { get, postForm, postJson } from '@/utils/api'
import request from '@/utils/request'

const module = 'report'

export function listData(params) {
  return get(`/${module}/list`, params)
}

export function saveOrUpdate(params) {
  return postJson(`/${module}/update`, params)
}

export function deleteById(id) {
  return postForm(`/${module}/delete`, { id })
}

export function selectInfoById({ id }) {
  return request.get(`/${module}/getInfo`, { params: { id }})
}
export function reportStatus({ id, stateus }) {
  return request.get(`/${module}/reportStatus`, { params: { id, stateus }})
}

