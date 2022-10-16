import { get, postForm } from '@/utils/api'

export function login(params) {
  return postForm(`/login`, params)
}

export function logout() {
  return get(`/logout`)
}

export function getInfo() {
  return get(`/employee/getInfo`)
}
