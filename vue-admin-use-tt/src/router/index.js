import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * 动态路由
*/
export const asyncRoutes = []

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */

// 静态路由
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    name: 'reg',
    path: '/reg',
    component: () => import('@/views/login/register'),
    hidden: true
  },
  {
    path: '/desk',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Desk',
        component: () => import('@/views/deskManagement/index'),
        meta: { title: '桌台管理', icon: 'el-icon-dish' }
      }
    ]
  },
  {
    path: '/dish',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Dish',
        component: () => import('@/views/dishManagement/index'),
        meta: { title: '菜品管理', icon: 'el-icon-food' }
      }
    ]
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
      meta: { title: '首页', icon: 'dashboard', affix: true }
    }]
  },
  {
    path: '/setmeal',
    component: Layout,
    redirect: '/setmeal/list',
    name: 'Setmeal',
    meta: { title: '套餐管理', icon: 'el-icon-dish' },
    children: [
      {
        path: 'list',
        name: 'DishList',
        component: () => import('@/views/setmeal/list'),
        meta: { title: '套餐列表', icon: 'el-icon-tickets' }
      },
      {
        path: 'add',
        name: 'DishAdd',
        component: () => import('@/views/setmeal/add'),
        meta: { title: '套餐添加', icon: 'el-icon-plus' }
      },
    ]
  },
  {
    path: '/dish',
    component: Layout,
    redirect: '/dish/list',
    name: 'Dish',
    meta: { title: '菜品管理', icon: 'el-icon-dish' },
    children: [
      {
        path: 'list',
        name: 'DishList',
        component: () => import('@/views/dish/list'),
        meta: { title: '菜单列表', icon: 'el-icon-tickets' }
      },
      {
        path: 'add',
        name: 'DishAdd',
        component: () => import('@/views/dish/add'),
        meta: { title: '菜品添加', icon: 'el-icon-plus' }
      },
    ]
  },

  {
    path: '/kitchen',
    component: Layout,
    redirect: '/kitchen/order',
    name: 'kitchen',
    meta: { title: '后厨面板', icon: 'el-icon-fork-spoon' },
    children: [
      {
        path: 'order',
        name: 'Order',
        component: () => import('@/views/kitchen/order'),
        meta: { title: '上菜信息', icon: 'el-icon-dish' }
      },
      {
        path: 'book',
        name: 'Book',
        component: () => import('@/views/kitchen/book'),
        meta: { title: '预约信息', icon: 'table' }
      }
    ]
  },
  {
    path: '/chart',
    component: Layout,
    redirect: '/chart/dish-flow',
    name: 'chart',
    meta: { title: '统计信息', icon: 'el-icon-s-data' },
    children: [
      {
        path: 'dish-flow',
        name: 'DishFlow',
        component: () => import('@/views/chart/dish-flow'),
        meta: { title: '菜品统计', icon: 'table' }
      },
      {
        path: 'shop-flow',
        name: 'ShopFlow',
        component: () => import('@/views/chart/shop-flow'),
        meta: { title: '门店统计', icon: 'tree' }
      }
    ]
  },
  {
    path: '/user-config',
    component: Layout,
    redirect: '/user-config/user-center',
    name: 'user',
    meta: { title: '用户管理', icon: 'el-icon-user' },
    children: [
      {
        path: 'user-center',
        name: 'UserCenter',
        component: () => import('@/views/user/user-center'),
        meta: { title: '个人中心', icon: 'el-icon-user-solid' }
      },
      {
        path: 'shop-config',
        name: 'ShopConfig',
        component: () => import('@/views/user/shop-config'),
        meta: { title: '门店管理', icon: 'el-icon-s-shop' }
      },
      {
        path: 'consumer-list',
        name: 'ConsumerList',
        component: () => import('@/views/user/consumer-list'),
        meta: { title: '顾客列表', icon: 'el-icon-s-custom' }
      }
    ]
  },
  {
    path: '/voucher',
    component: Layout,
    redirect: '/voucher/voucher-config',
    name: 'Voucher',
    meta: { title: '优惠劵管理', icon: 'el-icon-s-ticket' },
    children: [
      {
        path: 'voucher-config',
        name: 'VoucherConfig',
        component: () => import('@/views/voucher/voucher-config'),
        meta: { title: '优惠劵管理', icon: 'el-icon-s-ticket' }
      },
    ]
  },
  {
    path: '/permission',
    component: Layout,
    redirect: '/permission/buy',
    name: 'user',
    meta: { title: '权限管理', icon: 'el-icon-s-flag' },
    children: [
      {
        path: 'buy',
        name: 'Buy',
        component: () => import('@/views/permission/buy'),
        meta: { title: '权限购买', icon: 'el-icon-shopping-cart-2' }
      },
      {
        path: 'config',
        name: 'Config',
        component: () => import('@/views/permission/config'),
        meta: { title: '权限管理', icon: 'el-icon-s-check' }
      }

    ]
  },
  {
    path: '/chat',
    component: Layout,
    redirect: '/chat/admin-chat',
    name: 'user',
    meta: { title: '问题反馈', icon: 'el-icon-service' },
    children: [
      {
        path: 'admin-chat',
        name: 'AdminChat',
        component: () => import('@/views/chat/admin-chat'),
        meta: { title: '客服服务', icon: 'el-icon-service' }
      },
      {
        path: 'shop-chat',
        name: 'ShopChat',
        component: () => import('@/views/chat/shop-chat'),
        meta: { title: '人工客服', icon: 'el-icon-service' }
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
