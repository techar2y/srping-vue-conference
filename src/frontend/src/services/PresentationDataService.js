import http from "../../http-common";

class PresentationDataService {
    defaultPath = "http://localhost:8080/api";

    getAllPresentations(params) {
        return http.get(this.defaultPath + "/presentations", {params: params});
    }

    getById(id) {
        return http.get(this.defaultPath + `/presentations/${id}`);
    }

    create(data) {
        return http.post(this.defaultPath + "/presentations", data);
    }

    findByTitle(title) {
        return http.get(this.defaultPath + `/presentations?title=${title}`);
    }

    deleteAllPresentations() {
        return http.delete(this.defaultPath + "/presentations");
    }

    deleteById(id) {
        return http.delete(this.defaultPath + `/presentations/${id}`);
    }

    updatePresentation(id, schedule) {
        return http.put(this.defaultPath + `/presentations/${id}`, schedule);
    }
}

export default new PresentationDataService()