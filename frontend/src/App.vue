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
              function (err) {
                    if (err.status === 401 && err.config && !err.config.__isRetryRequest) {
                        // if you ever get an unauthorized, logout the user
                        this.$store.dispatch(AUTH_LOGOUT)
                        // you can also redirect to /login if needed !
                    }
                    if (err.status === 500){
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
</style>
