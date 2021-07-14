<template>
    <div class="list row">
        <div class="col-md-8">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Поиск по логину"
                       v-model="searchStr"/>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button"
                            @click="searchUser"
                    >
                        Искать
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <h4>Список пользователей</h4>
            <ul class="list-group" v-if="Object.keys(users).length !== 0">
                <li class="list-group-item"
                    :class="{ active: index == currentIndex }"
                    v-for="(user, index) in users"
                    :key="index"
                    @click="setActiveUser(user, index)"
                >
                    <div style="display:inline" v-if="Object.keys(user.fullName).length !== 0"> {{ user.fullName }}</div>
                    <div style="display:inline" v-else> Отсутствует полное имя</div>
                </li>
            </ul>
            <p v-else>Пользователи отсутствуют</p>

            <router-link to="/users/addUser">
                <button type="button" class="btn btn-sm btn-success" style="margin: 10px 5px 0px">
                    Добавить пользователя
                </button>
            </router-link>
            <button type="button" class="btn btn-sm btn-danger" style="margin: 10px 5px 0px"
                    @click="deleteAllUsers"
                    v-if="Object.keys(users).length !== 0">
                Очистить весь список
            </button>
        </div>
        <div class="col-md-6">
            <div v-if="currentUser">
                <h4>Пользователь</h4>
                <div>
                    <label><strong>ID:</strong></label> {{ currentUser.id }}
                </div>
                <div>
                    <label><strong>Полное имя:</strong></label> {{ currentUser.fullName }}
                </div>
                <div>
                    <label><strong>Email:</strong></label> {{ currentUser.email }}
                </div>
                <div>
                    <label><strong>Логин:</strong></label> {{ currentUser.login }}
                </div>
                <div>
                    <label><strong>Статус:</strong></label> {{ currentUser.role }}
                </div>

                <router-link v-bind:to="`/users/` + currentUser.id" >
                    <button type="button" class="btn btn-sm btn-warning" style="margin: 10px 0px">
                        Редактировать
                    </button>
                </router-link>
            </div>
            <div v-else>
                <br/>
                <p v-if="Object.keys(users).length !== 0">Пожалуйста выберите пользователя</p>
            </div>
        </div>
    </div>
</template>

<script>
    import UserDataService from "../../services/UserDataService";

    export default {
        name: "users-list",
        data() {
            return {
                users: {},
                currentUser: null,
                currentIndex: -1,
                searchStr: ""
            };
        },
        methods: {
            getAllUsers() {
                UserDataService.getAllUsers()
                    .then(result => {
                        this.users = result.data;
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            searchUser() {
                UserDataService.findUserByLogin(this.searchStr)
                    .then(response => {
                        this.users = response.data;
                        this.setActiveUser(null);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            setActiveUser(user, index) {
                this.currentUser = user;
                console.log(this.currentUser);
                this.currentIndex = user ? index : -1;
            },
            deleteAllUsers() {
                UserDataService.deleteAllUsers()
                    .then(() => {
                        this.users = {};
                        this.currentUser = null;
                        this.currentIndex = -1;
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }
        },
        mounted() {
            this.getAllUsers();
        }
    }
</script>

<style scoped>

</style>