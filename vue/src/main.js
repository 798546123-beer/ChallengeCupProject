import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import setAxios from './setAxios'
import axios from 'axios';
import ElementUI, {Notification} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import 'default-passive-events'
import DonMessage from "./assets/js/message"
import qs from 'qs'
import VCharts from 'v-charts'
import Es6Promise from 'es6-promise'
import './global/index.js'
import animated from 'animate.css'
//使用预防按钮多次重复点击指令
import preventReClick from './util/preventRepeatClick'
//使用按钮60s倒计时
import countDown from './util/countDown'
//引入自定义工具类
import tools from "./util/tools";

Vue.prototype["$VAR"] = VAR
Vue.use(animated)
require('es6-promise').polyfill()
Es6Promise.polyfill()

Vue.use(preventReClick);

Vue.prototype.$countDown = countDown;

Vue.prototype.$tools = tools;

Vue.prototype["$msg"] = DonMessage
const http = axios.create({
  timeout: 30000, // 请求超时时间
  baseURL: VAR.http,
})
if (localStorage.getItem("satoken")) {
  http.defaults.headers['satoken'] = localStorage.getItem("satoken");
}
Vue.prototype["$axios"] = axios
Vue.prototype["$http"] = http;
Vue.prototype["$qs"] = qs;
Vue.use(VCharts)
Vue.use(ElementUI);
setAxios();
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})


NProgress.configure({
  easing: 'ease',  // 动画方式
  speed: 500,  // 递增进度条的速度
  showSpinner: false, // 是否显示加载ico
  trickleSpeed: 200, // 自动递增间隔
  minimum: 0.3 // 初始化时的最小百分比
})
router.beforeEach((to, from, next) => {
  // 每次切换页面时，调用进度条
  NProgress.start();
  if (to.matched.some(record => record.meta.requiresAuth)) {
    //判断当前是否有登录的权限
    if (store.state.token === null) {
      Notification({title: '警告', message: '请先登录！', type: 'warning'});
      next({
        path: '/loginForm',
      })
    }
  }

  if (to.matched.some(record => record.meta.productRole)) {
    //判断当前是否有登录的权限
    if (store.state.role && store.state.role.indexOf(VAR.adminRole) === -1 && store.state.role.indexOf(VAR.productRole) === -1) {
      next('/NoAccess')
    }
  } else if (to.matched.some(record => record.meta.orderRole)) {
    //判断当前是否有登录的权限
    if (store.state.role && store.state.role.indexOf(VAR.adminRole) === -1 && store.state.role.indexOf(VAR.orderRole) === -1) {
      next('/NoAccess')
    }
  } else if (to.matched.some(record => record.meta.userRole)) {
    //判断当前是否有登录的权限
    if (store.state.role && store.state.role.indexOf(VAR.adminRole) === -1 && store.state.role.indexOf(VAR.userRole) === -1) {
      next('/NoAccess')
    }
  }

  if (to.path === '/login') {
    next({
      path: '/loginForm',
    })
  }

  if (to.path === '/HomePage' && store.state.role && store.state.role.indexOf(VAR.ordinaryRole) !== -1) {
    if (from.path !== '/MallHome') {
      next({
        path: '/MallHome',
      })
    } else {
      router.go(0)
    }
  } else if (to.path === '/MallHome' && store.state.role && store.state.role.indexOf(VAR.ordinaryRole) === -1) {
    if (from.path !== '/HomePage') {
      next({
        path: '/HomePage',
      })
    } else {
      router.go(0)
    }
  } else {
    next() // 确保一定要调用 next()
  }
});

router.afterEach(() => {
  // 在即将进入新的页面组件前，关闭掉进度条
  NProgress.done()
})

// 配置 axios 默认值
axios.defaults.baseURL = '/' // 使用相对路径
axios.defaults.timeout = 30000 // 增加超时时间到30秒
axios.defaults.headers.post['Content-Type'] = 'application/json'

// 添加请求拦截器
axios.interceptors.request.use(
  config => {
    // 在发送请求之前做些什么
    console.log('发送请求:', config)
    return config
  },
  error => {
    // 对请求错误做些什么
    console.error('请求配置错误:', error)
    return Promise.reject(error)
  }
)

// 添加响应拦截器
axios.interceptors.response.use(
  response => {
    console.log('收到响应:', response)
    return response
  },
  error => {
    console.error('响应错误:', error)
    if (error.response) {
      console.log('错误状态码:', error.response.status)
      console.log('错误数据:', error.response.data)
    }
    return Promise.reject(error)
  }
)

Vue.prototype.$axios = axios
