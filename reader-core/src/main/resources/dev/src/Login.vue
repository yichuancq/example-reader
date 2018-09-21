<template>
    <div class="login">
        <div class="panel">
            <div class="logo">
                <img src="./assets/img/logo.png">
            </div>
            <div class="place"><h2 class="title">登录</h2>
                <form action="" class="form">
                    <div class="item"><label>账号</label>
                        <br>
                        <input type="text" v-model="userInfo.userName" class="text">
                    </div>
                    <div class="item"><label>密码</label>
                        <br>
                        <input type="password" v-model="userInfo.userPwd" class="text">
                    </div>
                    <div class="item">
                        <el-button class="btn" type="primary" @click="login">登录</el-button>
                    </div>
                    <div class="item text-right">
                        <router-link class="btn_link" to="/reader">读者入口</router-link>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                userInfo: {
                    'userName': '',
                    'userPwd': ''
                }
            };
        },
        methods: {
            login(){
                this.$http.post('/api/reader/UserController/userLogin', this.userInfo).then((res) => {
                    if (res.data.result.user.legal) {
                        this.$router.push('/app/stock');
                    } else {
                        this.$alert('没有找到相关会员信息');
                    }
                });
            },
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            detectOS(){
                let isWin = (navigator.platform === "Win32") || (navigator.platform === "Windows");
                if (isWin) return "Window";
                let isMac = (navigator.platform === "Mac68K") || (navigator.platform === "MacPPC") || (navigator.platform === "Macintosh") || (navigator.platform === "MacIntel");
                if (isMac) return "Mac";
                let isUnix = (navigator.platform === "X11") && !isWin && !isMac;
                if (isUnix) return "Unix";
                let isLinux = (String(navigator.platform).indexOf("Linux") > -1);
                if (isLinux) return "Linux";
                return "other";
            }
        },
        mounted: function () {
            let platform = this.detectOS();
            if (platform === 'Unix' || platform === 'Linux') {
                this.$router.push('/reader');
            }
        }
    }
</script>

<style>
    .login {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: url('./assets/img/bg.jpg') no-repeat;
    }

    .login .panel {
        padding-top: 100px;
        width: 455px;
        margin: 0 auto;
    }

    .login .panel .logo {
        text-align: center;
    }

    .login .place {
        margin-top: 34px;
        border-radius: 3px;
        border: 1px solid #e7e7e7;
        box-shadow: 0 5px 13px rgba(28, 33, 42, 0.06);
        background: #fff;
    }

    .login .place h2 {
        font-weight: bold;
        padding: 25px 40px;
        color: #666;
        font-size: 20px;
        line-height: 20px;
        border-bottom: 1px solid #eff4f6;
    }

    .login .place .form {
        padding: 20px 40px 75px 40px;
    }

    .login .place .form .item {
        margin-top: 15px;
    }

    .login .place .form .item label {
        color: #999;
        font-size: 12px;
    }

    .login .place .form .item .text {
        margin-top: 10px;
        width: 352px;
        height: 18px;
        padding: 14px 10px;
        font-size: 16px;
        border: 0;
        background: #eff3f6;
        border-radius: 3px;
    }

    .login .place .form .item .btn {
        display: block;
        margin-top: 32px;
        width: 372px;
        padding: 12px 0;
        border: 0;
        color: #fff;
        border-radius: 5px;
        background: #118fe9;
        text-align: center;
        text-decoration: none;
    }
</style>