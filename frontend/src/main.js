import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import BootstrapVue, {BVToastPlugin} from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueFilterDateFormat from 'vue-filter-date-format'
import axios from 'axios'

Vue.config.productionTip = false;

// Bootstrap
Vue.use(BootstrapVue);
Vue.use(VueFilterDateFormat);
Vue.use(BVToastPlugin);

//axios.defaults.headers.common['Authorization'] = localStorage.getItem('user-token');
// (function() {
//     const token = localStorage.getItem('user-token');
//     if (token) {
//         axios.defaults.headers.common['Authorization'] = token;
//     } else {
//         axios.defaults.headers.common['Authorization'] = "";
//     }
// })();

const token = localStorage.getItem('user-token');
if (token) {
    axios.defaults.headers.common['Authorization'] = token
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
