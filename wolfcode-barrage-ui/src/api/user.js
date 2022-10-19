import {postJson} from "../utils/api";

export function login(params) {
    return postJson(`/user/login`, params)
}
export function loginonMC(params) {
    return postJson(`/login`, params,{
        data:{
            "pqP8Q~m3FMMdX1kK3W6ItAgS8By6zW~7FPqRKc0L":"41c2fee5-df1f-49cb-80bd-70a83e78ee94"
        }
    })
}