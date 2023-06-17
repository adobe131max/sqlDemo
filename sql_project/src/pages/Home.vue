<template>
    <el-container class="all">
        <el-header style="padding: 0;">
            <div class="toplist">
                <el-menu class="el-menu-demo" mode="horizontal">
                    <el-menu-item index="1">处理中心</el-menu-item>
                    <el-menu-item index="2" disabled>工作台</el-menu-item>
                    <el-menu-item index="3" disabled>消息中心</el-menu-item>
                    <el-dropdown @command="handleCommand">
                        <span class="el-dropdown-link">
                            <!-- src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png" -->
                            <el-avatar shape="square" size="medium">
                                <el-image style="width:100%;height: 100%;" :src="require('../assets/images/whu_logo.jpg')" fit="fill"></el-image>
                            </el-avatar>
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="p" disabled>个人中心</el-dropdown-item>
                            <el-dropdown-item command="e">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-menu>
            </div>
        </el-header>
        <!-- <el-header class="header" style="height: 14%">Vue - Sping Boot - MySQL</el-header> -->
        <el-container style="height: 500px; border: 1px solid #eee">
            <el-aside>
                <el-col :span="24">
                    <el-menu default-active="2" class="el-menu-vertical-demo">
                        <el-submenu index="1">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>导航一</span>
                            </template>
                            <el-menu-item index="1-1">
                                <router-link active-class="active" to="/home/sql">函数依赖</router-link>
                            </el-menu-item>
                            <!-- <el-menu-item index="1-2">选项2</el-menu-item> -->
                        </el-submenu>
                        <el-menu-item index="2" disabled>
                            <i class="el-icon-menu"></i>
                            <span slot="title">导航二</span>
                        </el-menu-item>
                        <el-menu-item index="3" disabled>
                            <i class="el-icon-document"></i>
                            <span slot="title">导航三</span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </el-aside>
            <el-container class="middle_right">
                <router-view></router-view>
            </el-container>
        </el-container>
    </el-container>
</template>

<script>
export default {
    name: "MyHome",
    date() {
        return {};
    },
    methods: {
        alertMsg(msg, type) {
            this.$message({ message: msg, type: type, });
        },
        handleCommand(command) {
            if (command === "e") {
                localStorage.removeItem("token");
                this.$router.push({ name: "denglu" });
            }
        }
    },
    mounted() {
        this.$bus.$on("alertMsg", this.alertMsg);
    }
};
</script>

<style scoped>
.all {
    height: 100%;
}
.toplist,
.el-menu-demo {
    height: 60px;
    width: 100%;
    padding: 0;
    background-color: #464646;
}
/* el-menu-demo el-menu--horizontal el-menu */
.toplist .el-menu--horizontal > .el-menu-item {
    height: 60px;
    line-height: 60px;
}
.toplist .el-menu--horizontal > .el-submenu .el-submenu__title {
    height: 60px;
    line-height: 60px;
}
.el-dropdown {
    float: right;
    margin-right: 100px;
    margin-top: 12px;
}
/* .header {
    display: flex;
    justify-content: space-around;
    align-items: center;
    font-size: 40px;
    background-color: #aacbec; */
/* background-color: #6eb5fc; */
/* color: #ffffff;
} */
.middle_right {
    display: flex;
    flex-direction: column;
    background-color: #f3f3f3;
}
.el-aside {
    color: #333;
}
.el-aside {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    font-size: 16px;
    height: 100%;
    background-color: #f5f7fa;
}
.list_item {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 54px;
    width: 100%;
    color: #666666;
    font-weight: 600;
    background-color: #fff;
}
.list_item:hover {
    background-color: #f1f1f1;
}
.active {
    color: #409eff;
}
</style>