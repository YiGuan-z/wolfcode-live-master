import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'el-icon-s-home' }
    }]
  },
  {
    path: '/business',
    component: Layout,
    name: 'Business',
    meta: { title: '业务模块', icon: 'el-icon-s-platform' },
    children: [
      {
        path: 'bulletmsg',
        name: 'BulletMsg',
        component: () => import('@/views/business/bulletmsg'),
        meta: { title: '弹幕管理', icon: 'el-icon-c-scale-to-original' }
      },
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/business/user'),
        meta: { title: '用户管理', icon: 'el-icon-s-custom' }
      }
    ]
  },

  {
    path: '/system',
    component: Layout,
    name: 'System',
    meta: { title: '系统管理', icon: 'el-icon-s-operation' },
    children: [
      {
        path: 'employee',
        name: 'Employee',
        component: () => import('@/views/system/employee'),
        meta: { title: '员工管理', icon: 'el-icon-user-solid' }
      },
      {
        path: 'department',
        name: 'Department',
        component: () => import('@/views/system/department'),
        meta: { title: '部门管理', icon: 'tree' }
      },
      {
        path: 'bulletMsgSensitive',
        name: 'BulletMsgSensitive',
        component: () => import('@/views/system/bulletMsgSendsitive'),
        meta: { title: '敏感词管理', icon: 'language' }
      },
      {
        path: 'report',
        name: 'Report',
        component: () => import('@/views/system/report'),
        meta: { title: '举报管理', icon: 'el-icon-document' }
      },
      {
        path: 'video',
        name: 'Video',
        component: () => import('@/views/system/video'),
        meta: { title: '视频管理', icon: 'el-icon-video-camera-solid' }
      },
      {
        path: 'logging',
        name: 'Logging',
        component: () => import('@/views/system/logging'),
        meta: { title: '日志管理', icon: 'list' }
      }
    ]
  },
  {
    path: '/drawing',
    name: 'Drawing',
    component: Layout,
    children: [
      {
        path: 'draw',
        name: 'Draw',
        component: () => import('@/views/drawing'),
        meta: { title: '图片管理', icon: 'image' }
      }
    ]
  },

  {
    path: '/layout',
    component: Layout,
    children: [
      {
        path: 'https://www.wolfcode.cn',
        meta: { title: '叩丁狼官网', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
