<template>
    <div class="edit-form" v-if="!deleted">
        <h4>Доклад</h4>
        <form v-if="currentSchedule != null">
            <div class="form-group">
                <label for="date">Дата</label>
                <input
                        type="text"
                        class="form-control"
                        id="date"
                        required
                        v-model="currentSchedule.date"
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
                        v-model="currentSchedule.name"
                        name="name"
                />
            </div>

            <div class="form-group">
                <label for="description">Описание</label>
                <input
                        class="form-control"
                        id="description"
                        required
                        v-model="currentSchedule.description"
                        name="description"
                />
            </div>

            <div class="form-group">
                <label for="subject">Предмет</label>
                <input
                        class="form-control"
                        id="subject"
                        required
                        v-model="currentSchedule.subject"
                        name="subject"
                />
            </div>

            <div class="form-group">
                <label for="room">Аудитория</label>
                <input
                        class="form-control"
                        id="room"
                        required
                        v-model="currentSchedule.room"
                        name="room"
                />
            </div>
        </form>

        <button type="button" @click="updateSchedule(currentSchedule.id, currentSchedule)"
                class="btn btn-success btn-sm" style="margin: 5px 5px 5px 0px">
            Обновить
        </button>

        <button type="button" @click="deleteSchedule(currentSchedule.id)"
                class="btn btn-danger btn-sm" style="margin: 5px 5px 5px 0px">
            Удалить
        </button>

    </div>

    <p v-else-if="deleted">Доклад успешно удалён</p>

    <p v-if="updated">Доклад успешно обновлён</p>

    <div>
        <router-link to="/schedules" class="nav-link">
            <button type="button" class="btn btn-link">Вернуться к списку докладов</button>
        </router-link>
    </div>
</template>

<script>
    import ScheduleDataService from "../../services/ScheduleDataService";

    export default {
        name: "schedule-details",
        data() {
            return {
                currentSchedule: null,
                deleted: false,
                updated: false
            };
        },
        methods: {
            getSchedule(id) {
                ScheduleDataService.getById(id)
                    .then(result => {
                        this.currentSchedule = result.data;
                    })
                    .catch(error => {
                        console.log(error);
                    });
            },
            deleteSchedule(id) {
                ScheduleDataService.deleteById(id)
                    .then(() => {
                        this.currentSchedule = null;
                        this.deleted = true;
                    })
                    .catch(error => {
                        console.log(error);
                    });
            },
            updateSchedule(id, schedule) {
                ScheduleDataService.update(id, schedule)
                    .then(() => {
                        this.updated = true;
                        setTimeout(() => this.updated = false, 3000);
                    })
                    .catch(error => {
                       console.log(error);
                    });
            }
        },
        mounted() {
            this.getSchedule(this.$route.params.id);
        }
    }
</script>

<style scoped>
    .edit-form {
        max-width: 300px;
        margin: auto;
    }
</style>