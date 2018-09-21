import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import './assets/css/main.css'
import routeConfig from './router'

Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(ElementUI);

Vue.http.interceptors.push(function (request, next) {
    let loadingInstance = ElementUI.Loading.service({text: '拼命加载中...'});
    request.headers.set('Content-Type', 'application/json;charset=UTF-8');
    request.headers.set('Access-Control-Allow-Origin', '*');
    request.headers.set('Access-Control-Allow-Credentials', 'true');

    // request.url = 'http://192.168.21.100:9991' + request.url;
    next(function (response) {
        loadingInstance.close();
        if (response.status !== 200) {
            this.$alert('请求失败!');
        } else if (response.data.code !== '000') {
            this.$alert('请求失败!返回的错误消息：' + response.data.message);
        } else {
            return response;
        }
    });
});

//定义路由
const router = new VueRouter({
    routes: routeConfig
});

new Vue({
    router
}).$mount('#app');