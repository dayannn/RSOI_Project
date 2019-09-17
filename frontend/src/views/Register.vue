<template>
    <div class="credentials-page">
        <form class="login" @submit.prevent="register" style="margin: auto; padding-top: 80px">
            <h1>Регистрация</h1>
            <br/>
            <label>Имя</label>
            <b-form-input required v-model="user.name" type="text" placeholder="Введите имя"> </b-form-input>
            <br/>
            <label>Фамилия</label>
            <b-form-input required v-model="user.last_name" type="text" placeholder="Введите фамилию"> </b-form-input>
            <br/>
            <label>Имя пользователя</label>
            <b-form-input required v-model="user.username" type="text" placeholder="Введите имя пользователя"> </b-form-input>
            <br/>
            <label>Пароль</label>
            <b-form-input required v-model="user.password" type="password" placeholder="Введите пароль"></b-form-input>
            <br/>
            <label>Подтверждение пароля</label>
            <b-form-input required v-model="password2" type="password" placeholder="Введите пароль ещё раз"></b-form-input>
            <br/>
            <button class="btn btn-info btn-sm shadowed-button" type="submit">Зарегестрироваться</button>
            <br/>
            <label style="color: #cc0000">{{errorText}}</label>
            <router-link to="/login" >
                Вход
            </router-link>
        </form>
    </div>
</template>


<style>
    .login {
        display: flex;
        flex-direction: column;
        width: 300px;
        padding: 10px;
    }

    .login input{
        box-shadow: inset 0 1px 2px 0 rgba(0, 0, 0, .4)
    }

    .login h1{
        font-family: sans-serif;
        font-weight: bold;
    }

    .login label{
        font-family:  "Trebuchet MS", sans-serif;
        font-weight: 600;
    }
</style>

<script>
    import axios from 'axios';
    export default {
        name: 'register',
        data () {
            return {
                user: {
                    username: '',
                    name: '',
                    last_name: '',
                    password: '',
                    role : 'user'
                },
                password2: '',
                errorText: ''
            }
        },
        methods: {
            register: function () {
                this.errorText = '';

                if (this.user.password !== this.password2){
                    this.errorText = "Пароли не совпадают!";
                    return;
                }

                axios.post('api/register', this.user)
                    .then(() => {
                        this.$router.push('/login');
                        this.user.password = '';
                        this.user.last_name = '';
                        this.user.name = '';
                        this.user.username = '';
                        this.errorText = '';
                        this.password2 = '';
                        }
                    )
                    .catch(err => console.log(err));

            }
        },
    }
</script>

<style scoped>
    /*.shadowed-input{*/
        /*box-shadow: inset 0 1px 2px 0 rgba(0, 0, 0, .4)*/
    /*}*/

    .shadowed-button {
        box-shadow: 0 4px 5px 0 rgba(0, 0, 0, .14), 0 1px 10px 0 rgba(0, 0, 0, .12), 0 2px 4px -1px rgba(0, 0, 0, .2)
    }
    .shadowed-button:hover{
        box-shadow: 0 2px 2px 0 rgba(0, 0, 0, .14), 0 3px 1px -2px rgba(0, 0, 0, .2), 0 1px 5px 0 rgba(0, 0, 0, .12)
    }
</style>
