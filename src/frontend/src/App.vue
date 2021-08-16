<template>
    <div id="app">
        <div>
            <b-navbar variant="dark" type="dark">
                <b-navbar-nav>
                    <b-navbar-brand to="/">
                        <font-awesome-icon icon="home" />
                    </b-navbar-brand>
                    <b-nav-item v-if="currentUser != null" to="/schedules">Расписание</b-nav-item>
                    <b-nav-item v-if="hasRoleAdmin || hasRolePresenter" to="/presentations">Доклады</b-nav-item>
                    <b-nav-item v-if="hasRoleAdmin" to="/users">Пользователи</b-nav-item>
                </b-navbar-nav>
                <b-navbar-nav v-if="!currentUser" class="ml-auto">
                    <b-nav-item to="/register">
                        <font-awesome-icon icon="user-plus"/>Sign Up
                    </b-nav-item>
                    <b-nav-item to="/login">
                        <font-awesome-icon icon="sign-in-alt" />Login
                    </b-nav-item>
                </b-navbar-nav>
                <b-navbar-nav v-if="currentUser" class="ml-auto">
                    <b-nav-item to="/profile">
                        <font-awesome-icon icon="user" /> {{ currentUser.username }}
                    </b-nav-item>
                    <b-nav-item @click.prevent="logOut">
                        <font-awesome-icon icon="sign-out-alt" />Выйти
                    </b-nav-item>
                </b-navbar-nav>
            </b-navbar>
        </div>

        <div class="container mt-3">
            <router-view/>
        </div>
    </div>
</template>

<script>
    const ROLE_ADMIN = 'ROLE_ADMIN';
    const ROLE_PRESENTER = 'ROLE_PRESENTER';

    export default {
        name: "app",
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },

            hasRoleAdmin() {
                if(this.$store.state.auth.user == null)
                    return false;

                return this.$store.state.auth.user.roles.find(x => x === ROLE_ADMIN);
            },

            hasRolePresenter() {
                if(this.$store.state.auth.user == null)
                    return false;

                return this.$store.state.auth.user.roles.find(x => x === ROLE_PRESENTER)
            }
        },
        methods: {
            logOut() {
                this.$store.dispatch('auth/logout');
                this.$router.push('/login');
            }
        }
    };
</script>

<style>

</style>
