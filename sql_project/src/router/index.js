import VueRouter from "vue-router";
import Home from '../pages/Home'
import Login from '../pages/Login'
import Sql from '../pages/Sql'

const router = new VueRouter({
    mode: "history",
    routes: [
        {
            name: 'denglu',
            path: '/login',
            component: Login,
            meta: { title: '登录' },
        },
        {
            name: 'zhuye',
            path: '/home',
            component: Home,
            meta: { title: '主页' },
            children: [
                {
                    name: 'sql',
                    path: '/home/sql',
                    component: Sql,
                    meta: { title: '函数依赖' },
                }
            ]
        },
    ]
})

// 全局前置路由守卫 - 初始化/路由切换前调用
// router.beforeEach((to, from, next) => {
//     let isAuthenticated = localStorage.getItem('token') === null
//     console.log(isAuthenticated);
//     if (to.name !== 'denglu' && isAuthenticated) next({ name: 'denglu' })
//     else next()
// })

// 全局后置路由守卫 - 初始化/路由切换后调用
router.afterEach((to) => {
    document.title = to.meta.title || 'MySQL'
})

export default router