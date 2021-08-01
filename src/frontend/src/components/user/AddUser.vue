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
                        placeholder="Peter Parker"
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
                <label>Статус</label>
                <b-form-select
                        class="form-control"
                        id="role"
                        required
                        v-model="currentUser.role"
                        :options="roles"
                        :state="validationRoleInfo.value"
                        name="role"
                        placeholder="user's role"
                        @input="validateRole">
                </b-form-select>
                <!--p> {{ currentUser.role }}</p-->
                <b-form-invalid-feedback :state="validationRoleInfo.value" id="roleInvalidFeedback">
                    {{ validationRoleInfo.invalid }}
                </b-form-invalid-feedback>
                <b-form-valid-feedback :state="validationRoleInfo.value" id="roleValidFeedback">
                    {{ validationRoleInfo.valid }}
                </b-form-valid-feedback>
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
    import RoleDataService from "../../services/RoleDataService";

    export default {
        name: "addUser",
        data() {
            return {
                currentUser: {
                    id: -1,
                    fullName: "",
                    login: "",
                    email: "",
                    role: ""
                },
                roles: {},
                submitted: false,
                validationRoleInfo: {
                    valid: "",
                    invalid: "",
                    value: false
                }
            }
        },
        methods: {
            saveUser() {
                console.log(this.roles[this.currentUser.role]);
                this.currentUser.role = this.roles[this.currentUser.role];
                this.currentUser.role = { id: 1, status: "ADMIN" }
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
            },
            getRoles() {
                return new Promise((resolve, reject) => {
                    RoleDataService.getAllRoles()
                        .then(result => {
                            this.roles = result.data;
                            this.roles.forEach(obj => {
                                obj.value = obj.id;
                                delete obj.id;
                                obj.text = obj.status;
                                delete obj.status;
                            });
                            resolve(this.roles);
                        })
                        .catch(error => {
                            console.log(error);
                            reject(error);
                        })
                })
            },
            validateRole() {
                if(this.currentUser ===  null)
                    return;

                if(this.currentUser.role == null) {
                    this.validationRoleInfo.invalid = true;
                    return;
                }
            }
        },
        async mounted() {
            await this.getRoles();
            /*this.validateLogin();
            this.validateEmail();*/
            this.validateRole();
        }
    }
</script>

<style scoped>
    .submit-form {
        max-width: 300px;
        margin: auto;
    }
</style>