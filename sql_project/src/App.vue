<template>
    <div class="app">
        <router-link to="/home" style="display: none;"></router-link>
        <router-link to="/login" style="display: none;"></router-link>
        <router-view class="main"></router-view>
    </div>
</template>

<script>
export default {
    name: 'App',
    data() {
        return {
            isLoad: false
        }
    },
    beforeCreate() {
        if (localStorage.getItem('token') === null) {
            this.$router.push({ name: 'denglu' })
        } else if (!this.isLoad) {
            this.isLoad = true
            this.$router.push({ name: 'denglu' })
        }
    }
}
</script>

<style scoped>
.app {
    width: 100%;
    height: 100%;
}
.main {
    width: 100%;
    height: 100%;
}
</style>>