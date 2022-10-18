import { get } from '@/utils/api'

const module = 'logging'

/**
 * listData 方法
 * @param params 形参: 实际的值取决于调用方法时传入的是什么, 这里我们期望 params = {...}
 * @returns {*}
 */
export function listData(params) {
  return get(`/${module}/list`, params)
}
