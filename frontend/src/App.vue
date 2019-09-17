<template>
  <div id="app">
    <Header />
    <router-view/>
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
      created () {
          axios.interceptors.response.use(undefined, function (err) {
              return new Promise(function (resolve, reject) {
                  if (err.status === 401 && err.config && !err.config.__isRetryRequest) {
                      // if you ever get an unauthorized, logout the user
                      this.$store.dispatch(AUTH_LOGOUT)
                      // you can also redirect to /login if needed !
                  }
                  throw err;
              });
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
