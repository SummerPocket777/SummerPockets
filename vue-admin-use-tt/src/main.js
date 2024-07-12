import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

// 引入高德地图依赖
// import VueAMap from '@vuemap/vue-amap';
// import '@vuemap/vue-amap/dist/style.css'

import '@/icons' // icon
import '@/permission' // permission control

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

// 初始化vue-amap
// Vue.use(VueAMap);
// VueAMap.initAMapApiLoader({
//   key: '67ec8f31660efface2be65ff016daaa4',
//   plugin: [
//     'AMap.Geolocation',  //定位空间，用来获取和展示用户主机所在的经纬度位置
//     'AMap.CircleEditor ',
//     "AMap.Geocoder"     //地图编码
//   ]
// });

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
