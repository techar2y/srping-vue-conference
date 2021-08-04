<template>
    <div class="submit-form">
        <form v-if="!submitted">
            <h4>Новый пользователь</h4>
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

            <b-button variant="success" style="margin: 10px 0px"
                      @click="saveUser">Добавить
            </b-button>
            <b-form-invalid-feedback :state="validationForm.value" id="formInvalidFeedback">
                {{ validationForm.invalid }}
            </b-form-invalid-feedback>
        </form>

        <div v-else>
            <h4>Пользователь успешно добавлен!</h4>
            <b-button variant="success" @click="newUser">Добавить ещё</b-button>

            <b-button to="/users" variant="link">Вернуться к списку пользователей</b-button>
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
                selectedRoleId: null,
                currentUser: {
                    id: -1,
                    fullName: "",
                    login: "",
                    email: "",
                    role: {}
                },
                roles: [],
                submitted: false,
                validationFullNameInfo: {valid: "", invalid: "", value: null},
                validationLoginInfo: {valid: "", invalid: "", value: null},
                validationEmailInfo: {valid: "", invalid: "", value: null},
                validationRoleInfo: {valid: "", invalid: "", value: null},
                validationForm: {invalid: "", value: null}
            }
        },
        methods: {
            async saveUser() {
                let valid = await this.validateAll();
                if (!valid) {
                    this.validationForm.invalid = "Проверьте правильность заполнения полей формы";
                    this.validationForm.value = false;
                    return;
                }

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
                this.selectedRoleId = null;
                this.currentUser = {id: -1, fullName: "", login: "", email: "", role: {}};
                this.roles = this.getRoles();
                this.submitted = false;
                this.validationFullNameInfo = {valid: "", invalid: "", value: null};
                this.validationLoginInfo = {valid: "", invalid: "", value: null};
                this.validationEmailInfo = {valid: "", invalid: "", value: null};
                this.validationRoleInfo = {valid: "", invalid: "", value: null};
                this.validationForm = {invalid: "", value: null};
            },
            getRoles() {
                return new Promise((resolve, reject) => {
                    RoleDataService.getAllRoles()
                        .then(result => {
                            if (result.data.length === 0)
                                return;

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
            validateFullName() {

                if (this.currentUser.fullName.length < 1
                    || this.currentUser.fullName.length > 128) {
                    this.validationFullNameInfo.invalid = "Поле обязательное к заполнению";
                    this.validationFullNameInfo.value = false;
                    return this.validationFullNameInfo.value;
                } else if (!/^[a-zA-Z\s]+$/.test(this.currentUser.fullName)) {
                    this.validationFullNameInfo.invalid = "Имя не должно содержать числа и специальные символы";
                    this.validationFullNameInfo.value = false;
                    return this.validationFullNameInfo.value;
                } else
                    this.validationFullNameInfo.value = true;
            },
            async validateLogin() {
                if (this.currentUser.login.length < 2) {
                    this.validationLoginInfo.invalid = "Логин не может стостоять меньше чем из двух символов";
                    this.validationLoginInfo.value = false;
                    return this.validationLoginInfo.value;
                }

                if (this.currentUser.login.length > 64) {
                    this.validationLoginInfo.invalid = "Логин не может превышать 64 символа";
                    this.validationLoginInfo.value = false;
                    return this.validationLoginInfo.value;
                }

                UserDataService.isLoginUnique(this.currentUser.login, this.currentUser.id)
                    .then(response => {
                        if (response.data > 0) {
                            this.validationLoginInfo.invalid = "Такой логин уже занят";
                            this.validationLoginInfo.value = false;
                            return this.validationLoginInfo.value;
                        }

                        this.validationLoginInfo.value = true;
                        return this.validationLoginInfo.value;
                    })
                    .catch(error => {
                        console.log(error);
                        return false;
                    })
            },
            async validateEmail() {
                if (this.currentUser.email.length < 2) {
                    this.validationEmailInfo.invalid = "Email не может стостоять меньше чем из двух символов";
                    this.validationEmailInfo.value = false;
                    return this.validationEmailInfo.value;
                }

                const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                if (!re.test(String(this.currentUser.email).toLowerCase())) {
                    this.validationEmailInfo.invalid = "Такая почта не подойдет";
                    this.validationEmailInfo.value = false;
                    return this.validationEmailInfo.value;
                }


                UserDataService.isEmailUnique(this.currentUser.email, this.currentUser.id)
                    .then(response => {
                        if (response.data > 0) {
                            this.validationEmailInfo.invalid = "Такой почтовый адрес уже занят";
                            this.validationEmailInfo.value = false;
                            return this.validationEmailInfo.value;
                        }

                        this.validationEmailInfo.value = true;
                        return this.validationEmailInfo.value;
                    })
                    .catch(error => {
                        console.log(error);
                        return false;
                    })
            },
            validateRole() {
                if (this.selectedRoleId == null) {
                    this.validationRoleInfo.value = false;
                    return this.validationRoleInfo.value;
                }

                let role = this.roles.find(x => x.value === this.selectedRoleId);
                this.currentUser.role.id = role.value;
                this.currentUser.role.status = role.text;
                this.currentUser.role.name = role.name;
                this.validationRoleInfo.value = true;
                return this.validationRoleInfo.value;
            },
            validateAll() {
                return new Promise((resolve) => {
                    let valid = this.validateFullName();
                    this.validateLogin()
                        .then(resolve => {
                            valid = resolve || valid;
                        })
                        .catch(reject => console.log(reject));
                    this.validateEmail()
                        .then(resolve => {
                            valid = resolve || valid
                        })
                        .catch(reject => console.log(reject));
                    valid = this.validateRole() || valid;
                    resolve(valid);
                })
            }
        },
        created: async function () {
            try {
                await this.getRoles();
            } catch (e) {
                console.log(e);
            }
        }
    }
</script>

<style scoped>
    .submit-form {
        max-width: 450px;
        margin: auto;
    }
</style>