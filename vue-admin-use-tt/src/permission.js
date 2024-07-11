import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login','/reg'] // 白名单

/**
 * 路由守卫
 */
router.beforeEach(async(to, from, next) => {
  // 开启加载样式
  NProgress.start()

  // 设置浏览器选项卡名称 --- 浏览器标签
  document.title = getPageTitle(to.meta.title)

  // 获取token --- 登录时
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {  // 是否登录过
      // 直接跳转到首页
      next({ path: '/' })
      NProgress.done()  // 关闭加载样式
    } else {
      // 获取用户信息
      const hasGetUserInfo = store.getters.name
      if (hasGetUserInfo) {  // 是否含有用户信息
        next()  // 直接放行
      } else {
        try {
          // 获取用户信息
          await store.dispatch('user/getInfo')

          // 获取角色列表
          const roles = store.getters.roles

          // 通过角色列表获取路由列表
          const accessRoutes = await store.dispatch('permission/generateRoutes', roles)

          // 添加到路由列表中
          router.addRoutes(accessRoutes)

          next({ ...to, replace: true })
        } catch (error) {
          console.log(error);
          // remove token and go to login page to re-login
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /* has no token*/

    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
