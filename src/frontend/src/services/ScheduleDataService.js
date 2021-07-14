import http from "../../http-common.js";

class ScheduleDataService {
    getAll() {
        return http.get("/schedules");
    }

    getById(id) {
        return http.get(`/schedules/${id}`);
    }

    create(data) {
        return http.post("/schedules", data);
    }

    findByTitle(title) {
        return http.get(`/schedules?title=${title}`);
    }

    deleteAllSchedules() {
        return http.delete("/schedules");
    }

    deleteById(id) {
        return http.delete(`/schedules/${id}`);
    }

    update(id, schedule) {
        return http.put(`/schedules/${id}`, schedule);
    }
}

export default new ScheduleDataService()