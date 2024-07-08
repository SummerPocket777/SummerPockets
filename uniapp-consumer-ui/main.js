import App from './App'
import uviewPlus from '@/uni_modules/uview-plus'
// #ifndef VUE3
import Vue from 'vue'
import './uni.promisify.adaptor'



Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
  ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
//导入Pinia的createPinia方法,用于创建Pinia实例(状态管理库)
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
export function createApp() {
  const app = createSSRApp(App)
  //创建一个Pinia实例,用于在应用中集中管理状态(store)
  const pinia = createPinia()
  //将插件添加到 pinia 实例上
  pinia.use(piniaPluginPersistedstate)
   app.use(uviewPlus)
   app.use(pinia) //将Pinia实例注册到Vue应用中
  return {
    app
  }
}
// #endif