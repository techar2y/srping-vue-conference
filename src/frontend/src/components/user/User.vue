<template>
    <div v-if="!notFound">
        <div v-if="!deleted && !updated" class="edit-form">
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
                            :state="validationFullNameInfo.value" @input="validateFullName">
                    </b-form-input>
                    <b-form-invalid-feedback :state="validationFullNameInfo.value" id="fullNameInvalidFeedback">
                        {{ validationFullNameInfo.invalid }}
                    </b-form-invalid-feedback>
                    <b-form-valid-feedback :state="validationFullNameInfo.value" id="fullNameValidFeedback">
                    </b-form-valid-feedback>
                </div>

                <div class="form-group">
                    <label>Логин</label>
                    <b-form-input
                            type="text"
                            class="form-control"
                            id="login"
                            required
                            v-model="currentUser.login"
                            name="login"
                            placeholder="user's login"
                            :state="validationLoginInfo.value" @input="validateLogin">
                    </b-form-input>

                    <b-form-invalid-feedback :state="validationLoginInfo.value" id="loginInvalidFeedback">
                        {{ validationLoginInfo.invalid }}
                    </b-form-invalid-feedback>
                    <b-form-valid-feedback :state="validationLoginInfo.value" id="loginValidFeedback">
                        {{ validationLoginInfo.valid }}
                    </b-form-valid-feedback>
                </div>

                <div class="form-group">
                    <label>Email</label>
                    <b-form-input
                            class="form-control"
                            id="email"
                            required
                            v-model="currentUser.email"
                            name="email"
                            placeholder="example@post.com"
                            :state="validationEmailInfo.value" @input="validateEmail">
                    </b-form-input>
                    <b-form-invalid-feedback :state="validationEmailInfo.value" id="emailInvalidFeedback">
                        {{ validationEmailInfo.invalid }}
                    </b-form-invalid-feedback>
                    <b-form-valid-feedback :state="validationEmailInfo.value" id="emailValidFeedback">
                        {{ validationEmailInfo.valid }}
                    </b-form-valid-feedback>
                </div>

                <div class="form-group">
                    <label>Статус</label>
                    <b-form-select
                            class="form-control"
                            id="role"
                            required
                            v-model="selectedRoleId"
                            :options="roles"
                            :state="validationRoleInfo.value"
                            name="role"
                            placeholder="user's role"
                            @input="validateRole"
                            aria-placeholder="Значение">
                    </b-form-select>
                    <p> {{ currentUser.role }}</p>
                    <b-form-invalid-feedback :state="validationRoleInfo.value" id="roleInvalidFeedback">
                        {{ validationRoleInfo.invalid }}
                    </b-form-invalid-feedback>
                    <b-form-valid-feedback :state="validationRoleInfo.value" id="roleValidFeedback">
                        {{ validationRoleInfo.valid }}
                    </b-form-valid-feedback>
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

        <div v-else-if="deleted">
            <p>Пользователь успешно удалён</p>
            <div>
                <router-link to="/users" class="nav-link">
                    <button type="button" class="btn btn-link">Вернуться к списку пользователей</button>
                </router-link>
            </div>
        </div>


        <div v-else-if="updated">
            <div style="text-align: center">
                <p>Пользователь успешно обновлён</p>
                <router-link to="/users" class="nav-link">
                    <button type="button" class="btn btn-link">Вернуться к списку пользователей</button>
                </router-link>
            </div>
        </div>

    </div>
    <div v-else>
        <h4>Пользователь не найден</h4>
    </div>
</template>

<script>
    import UserDataService from "../../services/UserDataService";
    import RoleDataService from "../../services/RoleDataService";
    //import ScheduleDataService from "../../services/ScheduleDataService";

    export default {
        name: "user-details",
        data() {
            return {
                notFound: false,
                currentUser: null,
                deleted: false,
                updated: false,
                selectedRoleId: null,
                roles: [],
                validationFullNameInfo: {
                    valid: "",
                    invalid: "",
                    value: false
                },
                validationLoginInfo: {
                    valid: "",
                    invalid: "",
                    value: false
                },
                validationEmailInfo: {
                    valid: "",
                    invalid: "",
                    value: false
                },
                validationRoleInfo: {
                    valid: "",
                    invalid: "",
                    value: null
                }
            };
        },
        methods: {
            getUserById(id) {
                return new Promise((resolve, reject) => {
                    UserDataService.getUserById(id)
                        .then(result => {
                            this.currentUser = result.data;
                            this.selectedRoleId = this.currentUser.role.id;
                            resolve(this.currentUser);
                        })
                        .catch(error => {
                            this.notFound = true;
                            reject(error);
                        });
                })
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
                            this.roles.splice(0, 0, {
                                value: null,
                                text: "Выбирете статус пользователя",
                                disabled: true
                            });
                            resolve(this.roles);
                        })
                        .catch(error => {
                            reject(error);
                        })
                })
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
                        //setTimeout(() => this.updated = false, 3000);
                    })
                    .catch(error => {
                        console.log(error);
                    });
            },
            validateFullName() {

                if (this.currentUser.fullName.length < 1
                    || this.currentUser.fullName.length > 128) {
                    this.validationFullNameInfo.invalid = "Поле обязательное к заполнению";
                    this.validationFullNameInfo.value = false;
                    return;
                } else if (!/^[a-zA-Z\s]+$/.test(this.currentUser.fullName)) {
                    this.validationFullNameInfo.invalid = "Имя не должно содержать числа и специальные символы";
                    this.validationFullNameInfo.value = false;
                    return;
                } else
                    this.validationFullNameInfo.value = true;
            }/*,
            async validateLogin() {
                const promise = await new Promise((resolve) => {
                    setTimeout(() => {
                        resolve(1);
                    }, 300);
                });

                if (promise.data > 0)
                    return false;
                else
                    return true;
            }*/

            ,
            async validateLogin() {
                /*let promise = await UserDataService.isLoginUnique(this.currentUser.login);
                console.log(promise);
                if(promise.data > 0) {
                    this.serversValidation = false;
                } else
                    this.serversValidation =  true;

            } catch(error) {
                console.log(error);
            }*/
                if (this.currentUser.login < 3) {
                    this.validationLoginInfo.invalid = "Логин не может стостоять меньше чем из двух символов";
                    this.validationLoginInfo.value = false;
                    return;
                }

                if (this.currentUser.login > 64) {
                    this.validationLoginInfo.invalid = "Логин не может превышать 64 символа";
                    this.validationLoginInfo.value = false;
                    return;
                }

                UserDataService.isLoginUnique(this.currentUser.login, this.currentUser.id)
                    .then(response => {
                        //console.log(response.data);

                        if (response.data > 0) {
                            this.validationLoginInfo.invalid = "Такой логин уже занят";
                            this.validationLoginInfo.value = false;
                            return;
                        }

                        //this.validationLoginInfo.valid = "Корректный логин";
                        this.validationLoginInfo.value = true;
                    })
                    .catch(error => {
                        console.log(error);
                        return false;
                    })
            },
            async validateEmail() {
                UserDataService.isEmailUnique(this.currentUser.email, this.currentUser.id)
                    .then(response => {
                        //console.log(response.data);

                        if (response.data > 0) {
                            this.validationEmailInfo.invalid = "Такой почтовый адрес уже занят";
                            this.validationEmailInfo.value = false;
                            return;
                        }

                        this.validationEmailInfo.value = true;
                    })
                    .catch(error => {
                        console.log(error);
                        return false;
                    })
            },
            validateRole() {
                if (this.selectedRoleId == null) {
                    this.validationRoleInfo.value = false;
                    return;
                }

                let role = this.roles.find(x => x.value === this.selectedRoleId);
                this.currentUser.role.id = role.value;
                this.currentUser.role.status = role.text;
                this.currentUser.role.name = role.name;
                this.validationRoleInfo.value = true;
                return;
            },
            validateAll() {
                this.validateFullName();
                this.validateLogin();
                this.validateEmail();
                this.validateRole();
            }
        }, async mounted() {
            /*await this.getUserById(this.$route.params.id);
            await this.getRoles();
            this.validateLogin();
            this.validateEmail();
            this.validateRole();*/

        }, created: async function () {
            try {
                await this.getUserById(this.$route.params.id);
                await this.getRoles();
                this.validateAll();
            } catch (e) {
                console.log(e);
            }
        }

        /*,
        computed: {
            validateLogin: async function() {
                const promise = await new Promise((resolve, reject) => {
                    setTimeout(() => {
                        resolve(1);
                    }, 300);
                });

                if (promise.data > 0)
                    return false;
                 else
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