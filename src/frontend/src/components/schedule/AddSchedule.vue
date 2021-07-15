<template>
    <div class="submit-form">
        <div v-if="!submitted">
            <div class="form-group">
                <label for="date">Дата</label>
                <input
                        type="text"
                        class="form-control"
                        id="date"
                        required
                        v-model="schedule.date"
                        name="date"
                />
            </div>

            <div class="form-group">
                <label for="name">Наименование доклада</label>
                <input
                        type="text"
                        class="form-control"
                        id="name"
                        required
                        v-model="schedule.name"
                        name="name"
                />
            </div>

            <div class="form-group">
                <label for="description">Описание</label>
                <input
                        class="form-control"
                        id="description"
                        required
                        v-model="schedule.description"
                        name="description"
                />
            </div>

            <div class="form-group">
                <label for="subject">Предмет</label>
                <input
                        class="form-control"
                        id="subject"
                        required
                        v-model="schedule.subject"
                        name="subject"
                />
            </div>

            <div class="form-group">
                <label for="room">Аудитория</label>
                <input
                        class="form-control"
                        id="room"
                        required
                        v-model="schedule.room"
                        name="room"
                />
            </div>

            <b-button type="button" class="btn btn-success" style="margin: 10px 0px"
                    @click="saveSchedule">Добавить</b-button>
        </div>

        <div v-else>
            <h4>Доклад успешно добавлен!</h4>
            <b-button type="button" class="btn btn-success" @click="newSchedule">Добавить ещё</b-button>
        </div>

        <div>
            <!--router-link to="/schedules" class="nav-link"-->
                <b-button to="/schedules" type="button" class="btn btn-link">Вернуться к списку докладов</b-button>
            <!--/router-link-->
        </div>
    </div>


</template>

<script>
    import ScheduleDataService from "../../services/ScheduleDataService";

    export default {
        name: "addSchedule",
        data() {
            return {
                schedule: {
                    id: null,
                    date: "",
                    subject: "",
                    description: "",
                    room: "",
                    name: ""
                },
                submitted: false
            }
        },
        methods: {
            saveSchedule() {
                var data = {
                    subject: this.schedule.subject,
                    description: this.schedule.description,
                    room: this.schedule.room,
                    date: this.schedule.date,
                    name: this.schedule.name
                };
                ScheduleDataService.create(data)
                    .then(result => {
                        this.schedule.id = result.data.id;
                        this.submitted = true;
                    })
                    .catch(error => {
                        console.log(error.data)
                    });
            },

            newSchedule() {
                this.submitted = false;
                this.schedule = {};
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