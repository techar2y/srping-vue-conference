<template>
    <div class="submit-form">
        <form v-if="!submitted">
            <h4>Добавление нового пользователя</h4>
            <div class="form-group">
                <label for="fullName">Полное имя</label>
                <input
                        type="text"
                        class="form-control"
                        id="fullName"
                        required
                        v-model="currentUser.fullName"
                        name="fullName"
                />
            </div>

            <div class="form-group">
                <label for="login">Логин</label>
                <input
                        type="text"
                        class="form-control"
                        id="login"
                        required
                        v-model="currentUser.login"
                        name="login"
                />
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input
                        class="form-control"
                        id="email"
                        required
                        v-model="currentUser.email"
                        name="email"
                />
            </div>

            <div class="form-group">
                <label for="role">Статус</label>
                <input
                        class="form-control"
                        id="role"
                        required
                        v-model="currentUser.role"
                        name="role"
                />
            </div>

            <button type="button" class="btn btn-success" style="margin: 10px 0px"
                    @click="saveUser">Добавить
            </button>
        </form>

        <div v-else>
            <h4>Пользователь успешно добавлен!</h4>
            <button type="button" class="btn btn-success" @click="newUser">Добавить ещё</button>
        </div>

        <div>
            <router-link to="/users" class="nav-link">
                <button type="button" class="btn btn-link">Вернуться к списку пользователей</button>
            </router-link>
        </div>
    </div>

</template>

<script>
    import UserDataService from "../../services/UserDataService";

    export default {
        name: "add-user",
        data() {
            return {
                currentUser: {
                    id: null,
                    fullName: "",
                    email: "",
                    login: "",
                    role: ""
                },
                submitted: false
            }
        },
        methods: {
            saveUser() {
                UserDataService.saveUser(this.currentUser)
                    .then(result => {
                        this.currentUser.id = result.data;
                        this.submitted = true;
                    })
                    .catch(e => {
                        console.log(e);
                    })
            },
            newUser() {
                this.submitted = false;
                this.currentUser = {};
            }
        }
    }
</script>

<style scoped>
    .submit-form {
        max-width: 300px;
        margin: auto;
    }
</style>