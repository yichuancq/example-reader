<template>
    <div>
        <div class="menu">
            <el-menu :router="true" theme="dark" :default-active="defaultActive" class="el-menu-demo" mode="horizontal">
                <el-menu-item :index="item.index" v-for="item in menuList">
                    {{item.text}}
                </el-menu-item>
            </el-menu>
        </div>
        <div class="content">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                defaultActive: '',
                menuList: [
                    {text: '库存查询', index: '/app/stock'},
                    {text: '商品流查询', index: '/app/flow'},
                    {text: '末位商品流查询', index: '/app/lastFlow'}
                ]
            };
        },
        mounted: function () {
            this.defaultActive = this.$route.path;
        }
    }
</script>

<style>
    .menu {
        position: fixed;
        width: 100%;
        top: 0;
        left: 0;
        z-index: 999;
    }

    .content {
        margin-top: 60px;
    }
</style>