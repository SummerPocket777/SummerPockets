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
    name:'reg',
    path: '/reg',
    component: () => import('@/views/login/register'),
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
      meta: { title: '首页', icon: 'dashboard', affix: true }
    }]
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
    redirect: '/kitchen/book',
    name: 'kitchen',
    meta: { title: '后厨面板', icon: 'el-icon-fork-spoon' },
    children: [
      {
        path: 'book',
        name: 'Book',
        component: () => import('@/views/kitchen/book'),
        meta: { title: '上菜信息', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/kitchen/order'),
        meta: { title: '预约信息', icon: 'tree' }
      }
    ]
  },
  {
    path: '/chart',
    component: Layout,
    redirect: '/chart/dish-flow',
    name: 'chart',
    meta: { title: '统计信息', icon: 'el-icon-fork-spoon' },
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
    meta: { title: '用户管理', icon: 'el-icon-fork-spoon' },
    children: [
      {
        path: 'user-center',
        name: 'UserCenter',
        component: () => import('@/views/user/user-center'),
        meta: { title: '个人中心', icon: 'table' }
      },
      {
        path: 'shop-config',
        name: 'ShopConfig',
        component: () => import('@/views/user/shop-config'),
        meta: { title: '门店管理', icon: 'tree' }
      },
      {
        path: 'consumer-list',
        name: 'ConsumerList',
        component: () => import('@/views/user/consumer-list'),
        meta: { title: '顾客列表', icon: 'tree' }
      }
    ]
  },
  {
    path: '/voucher',
    component: Layout,
    redirect: '/voucher/voucher-config',
    name: 'Voucher',
    meta: { title: '优惠劵管理', icon: 'el-icon-fork-spoon' },
    children: [
      {
        path: 'voucher-config',
        name: 'VoucherConfig',
        component: () => import('@/views/voucher/voucher-config'),
        meta: { title: '优惠劵管理', icon: 'table' }
      },
    ]
  },
  {
    path: '/permission',
    component: Layout,
    redirect: '/permission/buy',
    name: 'user',
    meta: { title: '权限管理', icon: 'el-icon-fork-spoon' },
    children: [
      {
        path: 'buy',
        name: 'Buy',
        component: () => import('@/views/permission/buy'),
        meta: { title: '权限购买', icon: 'table' }
      },
      {
        path: 'config',
        name: 'Config',
        component: () => import('@/views/permission/config'),
        meta: { title: '权限管理', icon: 'table' }
      }

    ]
  },
  {
    path: '/chat',
    component: Layout,
    redirect: '/chat/admin-chat',
    name: 'user',
    meta: { title: '问题反馈', icon: 'el-icon-fork-spoon' },
    children: [
      {
        path: 'admin-chat',
        name: 'AdminChat',
        component: () => import('@/views/chat/admin-chat'),
        meta: { title: '客服服务', icon: 'table' }
      },
      {
        path: 'shop-chat',
        name: 'ShopChat',
        component: () => import('@/views/chat/shop-chat'),
        meta: { title: '人工客服', icon: 'table' }
      }

    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        name: 'Menu2',
        meta: { title: 'menu2' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
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
