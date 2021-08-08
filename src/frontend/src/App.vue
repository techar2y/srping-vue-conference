<template>
    <div id="app">
        <div>
            <b-navbar variant="dark" type="dark">
                <b-navbar-nav>
                    <b-navbar-brand to="/">
                        <b-icon icon="house-fill" scale="1.25" shift-v="1.25" aria-hidden="true">Home</b-icon>
                    </b-navbar-brand>
                    <b-nav-item to="/schedules">Расписание</b-nav-item>
                    <b-nav-item to="/presentations">Доклады</b-nav-item>
                    <b-nav-item to="/users">Пользователи</b-nav-item>
                </b-navbar-nav>
            </b-navbar>
        </div>

        <div class="container mt-3">
            <router-view/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "app"
    };
</script>

<style>

</style>
