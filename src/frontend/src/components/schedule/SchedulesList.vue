<template>
    <!--div class="container">
        <h2>Расписание докладов</h2>
        <div class="row">
            <div class="col">
                ID
            </div>
            <div class="col">
                Дата
            </div>
            <div class="col">
                Предмет
            </div>
            <div class="col">
                Описание
            </div>
            <div class="col">
                Аудитория
            </div>
            <div class="col">
                Действие
            </div>
        </div>

        <div class="row" v-for="schedule in schedules" v-bind:key="schedule.id">
            <div class="col" v-for="element in schedule" v-bind:key="element.id"> {{ element }}</div>
            <div class="col">
                <button type="button" class="btn btn-primary btn-sm">
                    <i class="bi bi-cloud-upload-fill"></i> Редактировать
                </button>
                <button type="button" class="btn btn-danger btn-sm">
                    <i class="bi bi-cloud-upload-fill"></i> Удалить
                </button>
            </div>
        </div>
        <router-link to="/schedules/addSchedule" class="nav-link">
            <button type="button" class="btn btn-success">
                <i class="bi bi-cloud-upload-fill"></i>
                Добавить
            </button>
        </router-link>
    </div-->

    <div class="list row">
        <div class="col-md-8">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Поиск по заголовку"
                       v-model="searchStr"/>
                <div class="input-group-append">
                    <b-button class="btn btn-outline-secondary" type="button"
                            @click="searchTitle"
                    >
                        Искать
                    </b-button>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <h4>Список докладов</h4>
            <ul class="list-group" v-if="Object.keys(schedules).length !== 0">
                <li class="list-group-item"
                    :class="{ active: index == currentIndex }"
                    v-for="(schedule, index) in schedules"
                    :key="index"
                    @click="setActiveSchedule(schedule, index)"
                >
                    <div style="display:inline" v-if="Object.keys(schedule.name).length !== 0"> {{ schedule.name }}</div>
                    <div style="display:inline" v-else> Отсутствует наименование</div>
                </li>
            </ul>
            <p v-else>Доклады отсутствуют</p>

            <router-link to="/addSchedule">
                <button class="btn btn-sm btn-success" style="margin: 10px 0px">
                    Добавить доклад
                </button>
            </router-link>
            <b-button class="btn btn-sm btn-danger" style="margin: 10px 0px"
                    @click="deleteAllSchedules"
                    v-if="Object.keys(schedules).length !== 0">
                Очистить весь список
            </b-button>
        </div>
        <div class="col-md-6">
            <div v-if="currentSchedule">
                <h4>Доклад</h4>
                <div>
                    <label><strong>Наименование: </strong></label>
                    <div style="display:inline" v-if="currentSchedule.name"> {{ currentSchedule.name }}</div>
                    <div style="display:inline" v-else> Отсутствует наименование</div>
                </div>
                <div>
                    <label><strong>Предмет:</strong></label> {{ currentSchedule.subject }}
                </div>
                <div>
                    <label><strong>Описание:</strong></label> {{ currentSchedule.description }}
                </div>
                <div>
                    <label><strong>Аудитория:</strong></label> {{ currentSchedule.room }}
                </div>

                <router-link v-bind:to="`/schedules/` + currentSchedule.id">
                    <button class="btn btm btn-warning" style="margin: 10px 0px">
                        Редактировать
                    </button>
                </router-link>
            </div>
            <div v-else>
                <br/>
                <p v-if="Object.keys(schedules).length !== 0">Пожалуйста выберите доклад</p>
            </div>
        </div>
    </div>
</template>

<script>
    import ScheduleDataService from "../../services/ScheduleDataService";

    export default {
        name: "schedules-list",
        data() {
            return {
                /*schedules: [{ id: 1, date: "07.07.2021",
                        description: "test", subject: "test2",
                        room: 22, name: "test" }],*/
                schedules: {},
                currentSchedule: null,
                currentIndex: -1,
                searchStr: ""
            };
        },
        methods: {
            getAllSchedules() {
                ScheduleDataService.getAll()
                    .then(result => {
                        this.schedules = result.data;
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            searchTitle() {
                ScheduleDataService.findByTitle(this.searchStr)
                    .then(response => {
                        this.schedules = response.data;
                        this.setActiveSchedule(null);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            setActiveSchedule(schedule, index) {
                this.currentSchedule = schedule;
                this.currentIndex = schedule ? index : -1;
            },
            deleteAllSchedules() {
                ScheduleDataService.deleteAllSchedules()
                    .then(() => {
                        this.schedules = {};
                        this.currentSchedule = null;
                        this.currentIndex = -12;
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }
        },
        mounted() {
            this.getAllSchedules();
        }
    }
</script>

<style scoped>

</style>