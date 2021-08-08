<template>
    <div class="submit-form">
        <div v-if="!submitted">
            <h4 style="text-align: center">Новый доклад</h4>

            <div class="form-group">
                <label>Заголовок доклада</label>
                <b-form-input
                        type="text"
                        class="form-control"
                        id="title"
                        required
                        v-model="currentPresentation.title"
                        name="title"
                        placeholder="Чем интересней заголовок тем больше студентов придёт на доклад"
                        :state="validationTitleInfo.value" @input="validateTitle">
                </b-form-input>

                <b-form-invalid-feedback :state="validationTitleInfo.value" id="titleInvalidFeedback">
                    {{ validationTitleInfo.invalid }}
                </b-form-invalid-feedback>
                <b-form-valid-feedback :state="validationTitleInfo.value" id="titleValidFeedback">
                    {{ validationTitleInfo.valid }}
                </b-form-valid-feedback>
            </div>

            <div class="form-group">
                <label>Предмет</label>
                <b-form-input
                        type="text"
                        class="form-control"
                        id="title"
                        required
                        v-model="currentPresentation.subject"
                        name="title"
                        placeholder="Предмет доклада"
                        :state="validationSubjectInfo.value" @input="validateSubject">
                </b-form-input>

                <b-form-invalid-feedback :state="validationSubjectInfo.value" id="subjectInvalidFeedback">
                    {{ validationSubjectInfo.invalid }}
                </b-form-invalid-feedback>
                <b-form-valid-feedback :state="validationSubjectInfo.value" id="subjectValidFeedback">
                    {{ validationSubjectInfo.valid }}
                </b-form-valid-feedback>
            </div>

            <div class="form-group">
                <label>Описание</label>
                <b-form-textarea
                        id="textarea"
                        v-model="currentPresentation.description"
                        placeholder="Введите что нибудь..."
                        rows="3"
                        max-rows="6"
                ></b-form-textarea>
            </div>

            <div class="form-group">
                <label>Дата доклада</label>
                <b-form-datepicker
                        id="date" v-model="currentPresentation.date" class="mb-2"
                        :date-format-options="{ year: 'numeric', month: 'numeric', day: 'numeric' }"
                        locale="ru" placeholder="Выберете дату доклада" @input="validateDate">
                </b-form-datepicker>
                <b-form-invalid-feedback :state="validationDateInfo.value" id="dateInvalidFeedback">
                    {{ validationDateInfo.invalid }}
                </b-form-invalid-feedback>
            </div>


            <div class="form-group">
                <label>Продолжительность доклада:&nbsp;</label>
                <b-time v-model="currentPresentation.lasts" locale="ru"></b-time>
                <b-form-invalid-feedback :state="validationLastsInfo.value" id="lastsInvalidFeedback">
                    {{ validationLastsInfo.invalid }}
                </b-form-invalid-feedback>
                <p>{{ currentPresentation.lasts }}</p>
            </div>

            <div class="form-group">
                <label>Аудитория</label>
                <b-form-select
                        class="form-control"
                        id="room"
                        required
                        v-model="selectedRoomId"
                        :options="rooms"
                        :state="validationRoomInfo.value"
                        name="room"
                        placeholder="Номер аудитории"
                        @input="validateRoom"
                        aria-placeholder="Значение">
                </b-form-select>
                <b-form-invalid-feedback :state="validationRoomInfo.value" id="roomInvalidFeedback">
                    {{ validationRoomInfo.invalid }}
                </b-form-invalid-feedback>
                <b-form-valid-feedback :state="validationRoomInfo.value" id="roomValidFeedback">
                    {{ validationRoomInfo.valid }}
                </b-form-valid-feedback>
            </div>

            <div class="form-group">
                <label>Ведущий</label>
                <b-form-select
                        class="form-control"
                        id="presenter"
                        required
                        v-model="selectedPresenterId"
                        :options="cmbFormPresenters"
                        name="presenter"
                        placeholder="Ведущий"
                        aria-placeholder="Значение">
                </b-form-select>

                <b-form-invalid-feedback :state="validationPresenterInfo.value" id="presenterInvalidFeedback">
                    {{ validationPresenterInfo.invalid }}
                </b-form-invalid-feedback>
                <b-form-valid-feedback :state="validationPresenterInfo.value" id="presenterValidFeedback">
                    {{ validationPresenterInfo.valid }}
                </b-form-valid-feedback>
            </div>

            <b-button variant="success" style="margin: 10px 5px 0px"
                      @click="savePresentation">Добавить
            </b-button>
            <b-button to="/presentations" variant="danger" style="margin: 10px 5px 0px">Отмена
            </b-button>
            <b-form-invalid-feedback :state="validationForm.value" id="formInvalidFeedback">
                {{ validationForm.invalid }}
            </b-form-invalid-feedback>
        </div>

        <div v-else>
            <h4>Доклад успешно добавлен!</h4>
            <b-button variant="success" size="sm" @click="newPresentation">Добавить ещё</b-button>

            <div>
                <b-button to="/presentations" variant="link">Вернуться к списку докладов</b-button>
            </div>
        </div>
    </div>
</template>

<script>
    import PresentationDataService from "../../services/PresentationDataService";
    import RoomDataService from "../../services/RoomDataService";
    import ValidatePresentationFormUtil from "../../utils/ValidatePresentationFormUtil";
    import UserDataService from "../../services/UserDataService";

    export default {
        name: "addPresentation",
        data() {
            return {
                currentPresentation: {
                    id: -1,
                    lasts: "",
                    subject: "",
                    description: "",
                    room: {},
                    title: "",
                    date: "",
                    presenters: []
                },
                selectedRoomId: null,
                rooms: [],
                presenters: [],
                cmbFormPresenters: [],
                selectedPresenterId: null,
                submitted: false,
                validationTitleInfo: {valid: "", invalid: "", value: null},
                validationDescriptionInfo: {valid: "", invalid: "", value: null},
                validationLastsInfo: {valid: "", invalid: "", value: null},
                validationSubjectInfo: {valid: "", invalid: "", value: null},
                validationRoomInfo: {valid: "", invalid: "", value: null},
                validationPresenterInfo: {valid: "", invalid: "", value: null},
                validationDateInfo: {valid: "", invalid: "", value: null},
                validationForm: {invalid: "", value: null}
            }
        }, methods: {
            async savePresentation() {
                let valid = await this.validateAll();
                if (!valid) {
                    this.validationForm.invalid = "Проверьте правильность заполнения полей формы";
                    this.validationForm.value = false;
                    return;
                }

                PresentationDataService.savePresentation(this.currentPresentation)
                    .then(result => {
                        this.currentPresentation.id = result.data;
                        this.submitted = true;
                    })
                    .catch(e => {
                        console.log(e);
                    })
            }, newPresentation() {
                this.submitted = false;
                this.selectedRoomId = null;
                this.selectedPresenterId = null;
                this.currentPresentation = {
                    id: -1,
                    date: "",
                    login: "",
                    subject: "",
                    description: "",
                    title: "",
                    room: {},
                    presenters: []
                };
                this.room = this.getRooms();
                this.submitted = false;
                this.validationTitleInfo = {valid: "", invalid: "", value: null};
                this.validationRoomInfo = {valid: "", invalid: "", value: null};
                this.validationPresenterInfo = {valid: "", invalid: "", value: null};
                this.validationDescriptionInfo = {valid: "", invalid: "", value: null};
                this.validationDateInfo = {valid: "", invalid: "", value: null};
                this.validationSubjectInfo = {valid: "", invalid: "", value: null};
                this.validationForm = {invalid: "", value: null};
            }, getRooms() {
                return new Promise((resolve, reject) => {
                    RoomDataService.getAllRooms()
                        .then(result => {
                            if (result.data.length === 0)
                                return;

                            this.rooms = result.data;
                            this.rooms.forEach(obj => {
                                obj.value = obj.id;
                                delete obj.id;
                                obj.text = "Аудитория " + obj.number;
                                delete obj.number;
                            });
                            this.rooms.splice(0, 0, {
                                value: null,
                                text: "Выберете аудиторию для доклада",
                                disabled: true
                            });
                            resolve(this.rooms);
                        })
                        .catch(error => {
                            reject(error);
                        })
                })
            }, getPresenters() {
                return new Promise((resolve, reject) => {
                    let status = "PRESENTER";
                    UserDataService.getUsersByStatus(status)
                        .then(result => {
                            if (result.data.length === 0)
                                return;

                            this.cmbFormPresenters.push({value: null, text: "Выберете ведущего доклада", disabled: true});
                            this.presenters = result.data;
                            this.presenters.forEach(obj => {
                                this.cmbFormPresenters.push({value: obj.id, text: obj.fullName});
                            });
                            resolve(this.presenters);
                        })
                        .catch(error => {
                            reject(error);
                        })

                })
            }, validateAll() {
                return new Promise((resolve) => {
                    let valid = this.validateTitle();
                    valid = this.validateSubject() && valid;
                    valid = this.validateLasts() && valid;
                    valid = this.validateDate() && valid;
                    valid = this.validateRoom() && valid;
                    valid = this.validatePresenter() && valid;
                    resolve(valid);
                })
            }, validateTitle() {
                return ValidatePresentationFormUtil.validateTitle(this);
            }, validateLasts() {
                return ValidatePresentationFormUtil.validateLasts(this);
            }, validateSubject() {
                return ValidatePresentationFormUtil.validateSubject(this);
            }, validateRoom() {
                return ValidatePresentationFormUtil.validateRoom(this);
            }, validatePresenter() {
                return ValidatePresentationFormUtil.validatePresenter(this);
            }, validateDate() {
                return ValidatePresentationFormUtil.validateDate(this);
            }

        }, created: async function () {
            try {
                await this.getRooms();
                await this.getPresenters();
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