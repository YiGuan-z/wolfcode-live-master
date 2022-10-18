import { get, postForm, postJson } from '@/utils/api'

const module = 'report'

export function listData(params) {
  return get(`/${module}/list`, params)
}

export function saveOrUpdate(params) {
  return postJson(`/${module}/update`, params)
}

export function reportStatus(id, status) {
  return postForm(`/${module}/reportStatus`, { id, status })
}

export function deleteById(id) {
  return postForm(`/${module}/delete`, { id })
}

