<template>
    <div class="bili-mini-mask" :style="{display: visible ? '' : 'none'}">
        <div class="bili-mini" :style="{'background-image': loginImg}">
            <div class="bili-mini-close" @click="handleClose"></div>
            <div class="bili-mini-content">
                <div class="bili-mini-scan">
                    <div class="bili-mini-scan-top">
                        <div class="bili-mini-scan-qrcode">
                            <div class="bili-mini-scan-qrcode-box">
                                <div>
                                    <div class="bili-mini-scan-main">
                                        <p class="bili-mini-scan-title">扫描二维码登录</p>
                                        <div>
                                            <div class="bili-mini-scan-code">
                                                <div style="width: 160px; height: 160px;">
                                                    <div>
                                                        <img alt="Scan me!"
                                                             src="/images/wechat.png"
                                                             style="display: block;">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="bili-mini-scan-description"><p
                                class="bili-mini-scan-text"> 请关注<a
                                href="https://www.wolfcode.cn/" target="_blank">叩丁狼公众号</a></p>
                        </div>
                    </div>
                </div>
                <div class="bili-mini-line"></div>
                <div class="bili-mini-login-wrapper">
                    <div class="bili-mini-tab"><span
                            class="bili-mini-tab-password isActive"> 密码登录 </span><span
                            class="bili-mini-tab-line"></span><span
                            class="bili-mini-tab-message"> 短信登录 </span>
                    </div>
                    <div class="bili-mini-password-wrapper">
                        <div class="bili-mini-account">
                            <span class="text">账号</span>
                            <input v-model="loginForm.username" type="text" placeholder="请输入账号">
                        </div>
                        <div class="bili-mini-password">
                            <div class="left">
                                <span class="text">密码</span>
                                <input v-model="loginForm.password"  @focus="focusPass" @blur="blurPass" placeholder="请输入密码" type="password">
                            </div>
                            <div class="eye-btn"
                                 style="--thememini-color:#00a1d6;">
                                <svg width="20" height="20" viewBox="0 0 20 20" fill="none"
                                     xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                          d="M17.5753 6.85456C17.7122 6.71896 17.8939 6.63806 18.0866 6.63806C18.7321 6.63806 19.0436 7.42626 18.5748 7.87006C18.1144 8.30554 17.457 8.69885 16.6478 9.03168L18.1457 10.5296C18.2101 10.5941 18.2613 10.6706 18.2962 10.7548C18.331 10.839 18.349 10.9293 18.349 11.0204C18.349 11.1116 18.331 11.2019 18.2962 11.2861C18.2613 11.3703 18.2101 11.4468 18.1457 11.5113C18.0812 11.5757 18.0047 11.6269 17.9205 11.6618C17.8363 11.6967 17.746 11.7146 17.6548 11.7146C17.5637 11.7146 17.4734 11.6967 17.3892 11.6618C17.305 11.6269 17.2284 11.5757 17.164 11.5113L15.3409 9.68819C15.2898 9.63708 15.247 9.57838 15.2141 9.51428C14.4874 9.71293 13.6876 9.87122 12.8344 9.98119C12.8363 9.99011 12.8381 9.99908 12.8397 10.0081L13.2874 12.5472C13.315 12.7266 13.2713 12.9098 13.1656 13.0573C13.0598 13.2049 12.9005 13.3052 12.7217 13.3367C12.5429 13.3683 12.3589 13.3285 12.2091 13.2259C12.0592 13.1234 11.9555 12.9663 11.9202 12.7882L11.4725 10.2491C11.4645 10.2039 11.4611 10.1581 11.4621 10.1125C10.9858 10.1428 10.4976 10.1586 10.0002 10.1586C9.57059 10.1586 9.14778 10.1468 8.73362 10.1241C8.73477 10.1656 8.7322 10.2074 8.72578 10.249L8.27808 12.7881C8.24612 12.9694 8.14345 13.1306 7.99265 13.2362C7.84186 13.3418 7.65528 13.3831 7.47398 13.3512C7.29268 13.3192 7.1315 13.2166 7.0259 13.0658C6.9203 12.915 6.87892 12.7284 6.91088 12.5471L7.35858 10.008C7.35877 10.007 7.35896 10.0061 7.35915 10.0052C6.50085 9.90284 5.6941 9.75191 4.95838 9.56025C4.93012 9.60634 4.89634 9.64933 4.85748 9.68819L3.03438 11.5113C2.96992 11.5757 2.8934 11.6269 2.80918 11.6618C2.72496 11.6967 2.63469 11.7146 2.54353 11.7146C2.45237 11.7146 2.36211 11.6967 2.27789 11.6618C2.19367 11.6269 2.11714 11.5757 2.05268 11.5113C1.98822 11.4468 1.93709 11.3703 1.90221 11.2861C1.86732 11.2019 1.84937 11.1116 1.84937 11.0204C1.84937 10.9293 1.86732 10.839 1.90221 10.7548C1.93709 10.6706 1.98822 10.5941 2.05268 10.5296L3.49373 9.08855C2.6197 8.744 1.91247 8.33062 1.42559 7.87006C0.956591 7.42636 1.26799 6.63816 1.91359 6.63816C2.10629 6.63816 2.28789 6.71896 2.42489 6.85456C2.70009 7.12696 3.19529 7.45886 3.98459 7.77796C5.54429 8.40856 7.73699 8.77016 10.0001 8.77016C12.2632 8.77016 14.4558 8.40856 16.0156 7.77796C16.8049 7.45886 17.3001 7.12696 17.5753 6.85456Z"
                                          fill="#9499A0"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="bili-mini-login-register-wrapper">
                        <div class="universal-btn register-btn" :style="{color: regColor, border: regBorder}"
                             @mouseover="rego" @mouseleave="regl"> 注册
                        </div>
                        <div class="universal-btn login-btn" :style="{backgroundColor: loginBg}" @mouseover="logino"
                             @mouseleave="loginl" @click="handleLogin"> 登录
                        </div>
                    </div>
                    <div class="third-party-login-wrapper">
                        <div class="title">其他方式登录</div>
                        <div class="sns"><span class="btn wechat">微信登录</span><span
                                class="btn weibo">微博登录</span><span class="btn qq">QQ登录</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="bili-mini-agreement-wrapper">
            </div>
        </div>
    </div>
</template>

<script>
    import './index.scss'
    import {login} from "../../api/user"
    import config from '../../config'

    export default {
        name: "Login",
        props: {
            visible: {
                type: Boolean,
                required: true,
                default: false
            }
        },
        data: function () {
            return {
                loginForm: {
                    username: null,
                    password: null
                },
                loginImg: 'url(/images/22_open.png),url(/images/33_open.png)',
                regColor: '#000',
                regBorder: '1px solid #e7e7e7',
                loginBg: '#00a1d6'
            }
        },
        methods: {
            validate() {
                return new Promise((resolve, reject) => {
                    const {username, password} = this.loginForm

                    if (!username) {
                        this.$message.error('用户名不能为空')
                        return reject(false)
                    }
                    if (!password) {
                        this.$message.error('密码不能为空')
                        return reject(false)
                    }

                    return resolve(true)
                })
            },
            handleLogin() {
                this.validate().then(() => {
                    // 校验通过, 发送请求到后台进行登录
                    login(this.loginForm).then(res => {
                        // 后台需要返回的数据: res = {code: 200, msg: 'success': data: {token: '', loginInfo: {用户信息}}}
                        let {data} = res
                        console.log(res)
                        if (data.loginInfo) {
                            config.userId = data.loginInfo.userId
                            this.$emit('closelogin', false) // 关闭登录窗口
                        } else {
                            // 数据格式错误
                            this.$message.error('数据格式错误, data 对象中必须要有 loginInfo 属性');
                        }
                    })
                })
            },
            handleClose() {
                this.$emit('closelogin', false)
            },
            focusPass() {
                this.loginImg = 'url(/images/22_close.png),url(/images/33_close.png)'
            },
            blurPass() {
                this.loginImg = 'url(/images/22_open.png),url(/images/33_open.png)'
            },
            rego() {
                this.regColor = "#00a1d6";
                this.regBorder = "1px solid #00a1d6";
            },
            regl() {
                this.regColor = "#000";
                this.regBorder = "1px solid #e7e7e7";
            },
            logino() {
                this.loginBg = "#1fb9ed";
            },
            loginl() {
                this.loginBg = "#00a1d6";
            },
        }
    }
</script>
