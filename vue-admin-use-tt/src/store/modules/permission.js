import { constantRoutes, subMenu_ } from '@/router'
import store from '..'
import Layout from '@/layout'

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  // 获取路由
  generateRoutes({ commit }, roles) {
    return new Promise(resolve => {
      let asyncRoutes = []  // 动态路由表

      // 从store中获取动态路由
      const menus = store.getters.menus

      // 处理后端动态路由数据
      const menuList = []
      if (menus && menus.length > 0) {
        for (let i = 0; i < menus.length; i++) {
          const element = {}
          element.name = menus[i].menuName
          element.path = menus[i].menuPath
          element.redirect = menus[i].menuMedirect
          element.meta = menus[i].menuMeta

          // 处理组件
          if (menus[i].menuComponent === 'Layout') {
            element.component = Layout
          } else {
            const component = menus[i].menuComponent
            element.component = (resolve) => require([`@/views/${component}`], resolve)
          }
          // 子路由
          element.children = subMenu_(menus[i].children)
          menuList.push(element)
        }
      }

      // 将后端的路由数据处理后复制给静态路由表 asyncRoutes
      asyncRoutes = menuList
      const accessedRoutes = filterAsyncRoutes(asyncRoutes, roles)

      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
