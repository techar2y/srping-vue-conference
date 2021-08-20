import http from "../../http-common";

const API_URL = "/presentations";

class PresentationDataService {

    getAllPresentations(params) {
        return http.get(`${API_URL}/getAllPresentations`, {params: params});
    }

    getPresentationById(id) {
        return http.get(`${API_URL}/getPresentationById/${id}`);
    }

    findByTitle(title) {
        return http.get(`${API_URL}/?title=${title}`);
    }

    deleteAllPresentations() {
        return http.delete(`${API_URL}/deleteAllPresentations`);
    }

    deletePresentationById(id) {
        return http.delete(`${API_URL}/deletePresentationById/${id}`);
    }

    updatePresentation(id, schedule) {
        return http.put(`${API_URL}/updatePresentation/${id}`, schedule);
    }

    createPresentation(data) {
        return http.post(`${API_URL}/createPresentation`, data);
    }

    getPresentationsByRoom(room, params) {
        return http.post(`${API_URL}/getPresentationsByRoom`, room, {params: params} );
    }
}

export default new PresentationDataService()