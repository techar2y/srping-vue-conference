import http from "../../../http-common.js";

const API_URL = "/users";

class UserDataService {

    getAllUsers(params) {
        return http.get(`${API_URL}/getAllUsers`, { params: params });
    }

    getUserById(id) {
        return http.get(`${API_URL}/getUserById/${id}`);
    }

    createUser(data) {
        return http.post(`${API_URL}/createUser`, data);
    }

    deleteAllUsers() {
        return http.delete(`${API_URL}/deleteAllUsers`);
    }

    deleteUserById(id) {
        return http.delete(`${API_URL}/deleteUserById/${id}`);
    }

    updateUser(id, user) {
        return http.put(`${API_URL}/updateUser/${id}`, user);
    }

    isUsernameUnique(username, id) {
        return http.get(`${API_URL}/isUsernameUnique?username=${username}&id=${id}`);
    }

    isEmailUnique(email, id) {
        return http.get(`${API_URL}/isEmailUnique?email=${email}&id=${id}`);
    }

    getUsersByUserRole(role) {
        return http.post(`${API_URL}/getUsersByUserRole`, role);
    }
}

export default new UserDataService()