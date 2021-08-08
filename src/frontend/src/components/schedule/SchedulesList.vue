<template>
    <b-table striped hover bordered no-border-collapse head-variant="dark"
             :items="items" :fields="fields"></b-table>
</template>

<script>
    import PresentationDataService from "../../services/PresentationDataService";

    export default {
        name: "SchedulesList",
        data() {
            return {
                items: [],
                fields: [{ key: "subject", label: "Предмет" }, { key: "title", label: "Заголовок" },
                    { key: "room.number", label: "Аудитория" }, { key: "date", label: "Дата" },
                    { key: "lasts", label: "Длительность" }]
            };
        }, methods: {
            getPresentationsByRoom() {
                return new Promise((resolve, reject) => {
                    PresentationDataService.getPresentationsByRoom({id: 1, number: 54})
                        .then(result => {
                            resolve(result.data);
                        })
                        .catch(e => {
                            reject(e);
                        })
                })
            }
        }, async created() {
            try {
                this.items = await this.getPresentationsByRoom();
            } catch (e) {
                console.log(e);
            }
        }
    }
</script>

<style scoped>

</style>