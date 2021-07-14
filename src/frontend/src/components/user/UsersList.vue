<template>
    <div class="list row">

        <div class="col-md-8">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Поиск по логину"
                       v-model="searchStr" style="margin-right: 10px"/>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button"
                            @click="searchUser"
                    >
                        Искать
                    </button>
                </div>
            </div>
        </div>

        <div class="col-md-12">
            <div class="mb-3">
                Items per Page:
                <select v-model="pageSize" @change="handlePageSizeChange($event)">
                    <option v-for="size in pageSizes" :key="size" :value="size">
                        {{ size }}
                    </option>
                </select>
            </div>

            <b-pagination
                    v-model="page"
                    :total-rows="count"
                    :per-page="pageSize"
                    prev-text="Prev"
                    next-text="Next"
                    @change="handlePageChange"
            ></b-pagination>
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
                    <div style="display:inline" v-if="Object.keys(user.fullName).length !== 0"> {{ user.fullName }}
                    </div>
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

                <!--router-link v-bind:to="`/users/` + currentUser.id"-->
                <b-button v-bind:to="`/users/` + currentUser.id" class="btn btn-sm btn-warning"
                          style="margin: 10px 0px">
                    Редактировать
                </b-button>
                <!--/router-link-->
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
                searchStr: "",

                page: 1,
                count: 0,
                pageSize: 3,

                pageSizes: [3, 6, 9]
            };
        },
        methods: {
            getAllUsers() {
                let params = this.getRequestParams(this.searchStr, this.page, this.pageSize);
                UserDataService.getAllUsers(params)
                    .then(result => {
                        console.log(result.data);
                        this.users = result.data.users;
                        this.count = result.data.totalItems;
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
            },
            getRequestParams(searchStr, page, pageSize) {
                let params = {};

                if (searchStr) {
                    params["title"] = searchStr;
                }

                if (page) {
                    params["page"] = page - 1;
                }

                if (pageSize) {
                    params["size"] = pageSize;
                }

                return params;
            },
            handlePageChange(value) {
                this.page = value;
                this.getAllUsers();
            },

            handlePageSizeChange(event) {
                this.pageSize = event.target.value;
                this.page = 1;
                this.getAllUsers();
            }
        },
        mounted() {
            this.getAllUsers();
        }
    }
</script>

<style scoped>

</style>