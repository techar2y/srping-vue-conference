<template>
    <div class="edit-form" v-if="!deleted">
        <h4>Редактировать пользователя</h4>
        <form v-if="currentUser != null">
            <div class="form-group">
                <label>Полное имя</label>
                <b-form-input
                        type="text"
                        class="form-control"
                        required
                        v-model="currentUser.fullName"
                        placeholder="Peter Parker"
                        name="fullName"
                        id="fullName"
                        :state="fullNameValidation()">
                </b-form-input>
                <b-form-invalid-feedback :state="validation" id="fullNameInvalidFeedback">
                    {{ validationInfo }}
                </b-form-invalid-feedback>
                <b-form-valid-feedback :state="validation" id="fullNameValidFeedback">
                </b-form-valid-feedback>
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
                        placeholder="user's login"
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
                        placeholder="example@post.com"
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
                        placeholder="USER"
                />
            </div>

        </form>

        <button type="button" @click="updateUser(currentUser.id, currentUser)"
                class="btn btn-success btn-sm" style="margin: 5px 5px 5px 0px">
            Обновить
        </button>

        <button type="button" @click="deleteUserById(currentUser.id)"
                class="btn btn-danger btn-sm" style="margin: 5px 5px 5px 0px">
            Удалить
        </button>

    </div>

    <p v-else-if="deleted">Пользователь успешно удалён</p>

    <p v-if="updated">Пользователь успешно обновлён</p>

    <div>
        <router-link to="/users" class="nav-link">
            <button type="button" class="btn btn-link">Вернуться к списку пользователей</button>
        </router-link>
    </div>
</template>

<script>
    import UserDataService from "../../services/UserDataService";

    export default {
        name: "user-details",
        data() {
            return {
                currentUser: null,
                deleted: false,
                updated: false,
                validationInfo: ""
            };
        },
        methods: {
            getUserById(id) {
                UserDataService.getUserById(id)
                    .then(result => {
                        this.currentUser = result.data;
                    })
                    .catch(error => {
                        console.log(error);
                    });
            },
            deleteUserById(id) {
                UserDataService.deleteUserById(id)
                    .then(() => {
                        this.currentUser = null;
                        this.deleted = true;
                    })
                    .catch(error => {
                        console.log(error);
                    });
            },
            updateUser(id, user) {
                UserDataService.update(id, user)
                    .then(() => {
                        this.updated = true;
                        setTimeout(() => this.updated = false, 3000);
                    })
                    .catch(error => {
                        console.log(error);
                    });
            },
            fullNameValidation() {

                if (this.currentUser.fullName.length < 1
                    || this.currentUser.fullName.length > 128) {
                    this.validationInfo = "Поле обязательное к заполнению";
                    return false;
                }

                if (!/^[a-zA-Z\s]+$/.test(this.currentUser.fullName)) {
                    this.validationInfo = "Имя не должно содержать числа и специальные символы";
                    return false;
                }

                return true;
            }
        },
        mounted() {
            this.getUserById(this.$route.params.id);
        }
        /*computed: {
            fullNameValidation() {

                    if (this.currentUser.fullName.length < 1
                        || this.currentUser.fullName.length > 128) {
                        this.validationInfo = "Поле обязательное к заполнению";
                        return false;
                    }

                    if (!/^[a-zA-Z\s]+$/.test(this.currentUser.fullName)) {
                        //this.validationInfo = "Имя не должно содержать числа и специальные символы";
                        return false;
                    }

                    return true;
                }
        }*/
    }
</script>

<style scoped>
    .edit-form {
        max-width: 300px;
        margin: auto;
    }
</style>