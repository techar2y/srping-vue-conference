<template>
    <div>
        <div v-if="currentPresentation != null">
            <div v-if="!notFound">
                <div v-if="!deleted && !updated" class="edit-form">
                    <h4 style="text-align: center">Редактировать доклад</h4>
                    <form v-if="currentPresentation != null">
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
                            <label>Продолжительность доклада:&nbsp;</label>
                            <b-time v-model="currentPresentation.lasts" locale="ru"></b-time>
                            <b-form-invalid-feedback :state="validationLastsInfo.value" id="lastsInvalidFeedback">
                                {{ validationLastsInfo.invalid }}
                            </b-form-invalid-feedback>
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
                            <p> {{ currentPresentation.role }}</p>
                            <b-form-invalid-feedback :state="validationRoomInfo.value" id="roomInvalidFeedback">
                                {{ validationRoomInfo.invalid }}
                            </b-form-invalid-feedback>
                            <b-form-valid-feedback :state="validationRoomInfo.value" id="roomValidFeedback">
                                {{ validationRoomInfo.valid }}
                            </b-form-valid-feedback>
                        </div>
                    </form>

                    <b-button @click="updatePresentation(currentPresentation.id, currentPresentation)"
                              variant="success" size="sm" style="margin: 5px 5px 5px 0px">
                        Обновить
                    </b-button>

                    <b-button @click="deletePresentationById(currentPresentation.id)"
                              variant="danger" size="sm" style="margin: 5px 5px 5px 0px">
                        Удалить
                    </b-button>
                </div>

                <div v-else-if="deleted">
                    <p>Пользователь успешно удалён</p>
                    <div>
                        <b-button to="/presentations" variant="link">Вернуться к списку пользователей</b-button>
                    </div>
                </div>


                <div v-else-if="updated">
                    <div style="text-align: center">
                        <p>Пользователь успешно обновлён</p>
                        <b-button to="/presentations" variant="link">Вернуться к списку пользователей</b-button>
                    </div>
                </div>

            </div>
            <div v-else>
                <h4>Доклад не найден</h4>
            </div>
        </div>
        <div v-else>
            <div class="d-flex justify-content-center mb-3">
                <b-spinner label="Loading..."></b-spinner>
            </div>
        </div>

        <div>
            <b-button to="/presentations" variant="link">Вернуться к списку докладов</b-button>
        </div>
    </div>
</template>

<script>
    import PresentationDataService from "../../services/PresentationDataService";
    import RoomDataService from "../../services/RoomDataService";
    import ValidatePresentationFormUtil from "../../utils/ValidatePresentationFormUtil";


    export default {
        name: "presentation-details",
        data() {
            return {
                notFound: false,
                currentPresentation: null,
                deleted: false,
                updated: false,
                selectedRoomId: null,
                rooms: [],
                validationTitleInfo: {valid: "", invalid: "", value: null},
                validationDescriptionInfo: {valid: "", invalid: "", value: null},
                validationLastsInfo: {valid: "", invalid: "", value: null},
                validationSubjectInfo: {valid: "", invalid: "", value: null},
                validationRoomInfo: {valid: "", invalid: "", value: null},
                validationForm: {invalid: "", value: null}
            };
        },
        methods: {
            getPresentationById(id) {
                return new Promise((resolve, reject) => {
                    PresentationDataService.getById(id)
                        .then(result => {
                            this.currentPresentation = result.data;
                            this.selectedRoomId = this.currentPresentation.room.id;
                            resolve(this.currentPresentation);
                        })
                        .catch(error => {
                            this.notFound = true;
                            reject(error);
                        })
                })
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
            }, deletePresentationById(id) {
                PresentationDataService.deletePresentationById(id)
                    .then(() => {
                        this.currentPresentation = null;
                        this.deleted = true;
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }, updatePresentation(id, user) {
                PresentationDataService.updatePresentation(id, user)
                    .then(() => {
                        this.updated = true;
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }, validateAll() {
                return new Promise((resolve) => {
                    let valid = this.validateTitle();
                    valid = this.validateSubject() && valid;
                    valid = this.validateLasts() && valid;
                    valid = this.validateRoom() && valid;
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
            }
        },
        created: async function () {
            try {
                await this.getPresentationById(this.$route.params.id);
                await this.getRooms();
                this.validateAll();
            } catch (e) {
                console.log(e);
            }
        }
    }
</script>

<style scoped>
    .edit-form {
        max-width: 450px;
        margin: auto;
    }
</style>