<style lang="less">
    @import './login.less';
</style>

<template>
    <div class="login" @keydown.enter="handleSubmit">
        <div class="login-con">
            <Card :bordered="false">
                <p slot="title">
                    <Icon type="log-in"></Icon>
                    Welcome
                </p>
                <div class="form-con">
                    <Form ref="loginForm" :model="form" :rules="rules">
                        <FormItem prop="username">
                            <Input v-model="form.username" placeholder="Please input username">
                                <span slot="prepend">
                                    <Icon :size="16" type="person"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="form.password" placeholder="Please input password">
                                <span slot="prepend">
                                    <Icon :size="14" type="locked"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem>
                            <Button @click="handleSubmit" type="primary" long>Login</Button>
                        </FormItem>
                    </Form>
                    <!--<p class="login-tip">输入任意用户名和密码即可</p>-->
                </div>
            </Card>
        </div>
    </div>
</template>

<script>
import Cookies from 'js-cookie';
export default {
    data () {
        return {
            form: {
                username: '',
                password: ''
            },
            rules: {
                username: [
                    { required: true, message: 'Username empty', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: 'Password empty', trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        handleSubmit () {
            this.form.username = 'admin';
            this.form.password = '123456';
            this.$refs.loginForm.validate((valid) => {
                if (valid) {
                    this.$api.login(this.form.username, this.form.password).then(res => {
                        // Cookies.set('user', this.form.username);
                        // // Cookies.set('password', this.form.password);
                        // Cookies.set('access', 0);
                        // //Cookies.set('access_token', (res.data.token_type + ' ' + res.data.access_token))
                        localStorage.token = res.headers.authorization;
                        console.log(localStorage.token)
                        this.$router.push({
                            name: 'home_index'
                        });
                    })
                }
            });
        }
    }
};
</script>

<style>

</style>
