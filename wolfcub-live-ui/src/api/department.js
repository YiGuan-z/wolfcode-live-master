import { get, postForm, postJson } from '@/utils/api'

const module = 'department'

export function treeList() {
  return get(`/${module}/treeList`)
}

export function listData(params) {
  return get(`/${module}/list`, { params })
}

export function saveOrUpdate(params) {
  return postJson(`/${module}/saveOrUpdate`, params)
}

export function deleteById(id) {
  return postForm(`/${module}/delete`, { id })
}

export function listAll() {
  return get(`/${module}/listAll`)
}
