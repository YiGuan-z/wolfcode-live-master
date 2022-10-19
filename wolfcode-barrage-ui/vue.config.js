module.exports = {
    publicPath:'./',
    // 关闭 eslint 告警与错误消息
    lintOnSave: false,
    devServer: {
        port: 8888,
        open: true,
        overlay: {
            // 关闭 eslint 告警与错误消息
            warnings: false,
            errors: false
        },
        proxy: {
            // 以指定的前缀开始应用代理
            '/api': {
                target: 'http://localhost:8080', // 代理后的目标服务器地址(后台服务器地址)
                changeOrigin: true, // 允许跨域
                pathRewrite: {'^/api': ''}
            }
        },
    }
}