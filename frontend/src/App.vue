<template>
  <div id="app">
    <Header />
    <router-view/>
    <b-modal
            v-model="showError"
            title="Ошибка"
            hide-footer
            centered
    >
        <label>{{errorMsg}}</label>
    </b-modal>
  </div>
</template>

<script>
  import Header from "./components/layout/header";
  import axios from 'axios';
  import {AUTH_LOGOUT} from "./store/actions/auth";
  export default {
      name:"app",
      components:{Header},
      comments:{
          Header
      },
      data(){
          return{
              showError: false,
              errorMsg: ""
          }
      },
      created () {
          axios.interceptors.response.use(
              (response) => {
                  return response;
              },
              (err) => {
                  if (!err.response){
                      return Promise.reject(err);
                  }
                    if (err.response.status === 401) {
                        // if you ever get an unauthorized, logout the user
                        this.errorMsg = "Ваша сессия была завершена. Пожалуйста, зайдите в аккаунт заново";
                        this.showError = true;
                        this.$store.dispatch(AUTH_LOGOUT).then(() => this.$router.push('login'))
                        // you can also redirect to /login if needed !
                    } else if (err.response.status === 500){
                        this.errorMsg = "Произошла ошибка при соединении с сервером";
                        this.showError = true;
                    } else if (err.response.status === 503){
                        this.errorMsg = "Операция временно недоступна";
                        this.showError = true;
                    }

                  return Promise.reject(err.response);
          });
      }
  }
</script>

<style>
#app {
  font-family: tahoma,verdana,arial,serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #333;
  background: #eeeeee;
  min-height: 100vh;
}

.toast-item{
  z-index: 1;
}
</style>
