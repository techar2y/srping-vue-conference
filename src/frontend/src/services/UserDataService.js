import http from "../../http-common.js";

class UserDataService {
    defaultPath = "http://localhost:8080/api";

    getAllUsers(params) {
        return http.get(this.defaultPath + "/users", { params: params });
    }

    getUserById(id) {
        return http.get(`${this.defaultPath}/users/${id}`);
    }

    saveUser(data) {
        return http.post(this.defaultPath + "/users", data);
    }

    findUserByLogin(searchStr) {
        return http.get(`${this.defaultPath}/users?searchStr=${searchStr}`);
    }

    deleteAllUsers() {
        return http.delete(this.defaultPath + "/users");
    }

    deleteUserById(id) {
        return http.delete(`${this.defaultPath}/users/${id}`);
    }

    update(id, user) {
        return http.put(`${this.defaultPath}/users/${id}`, user);
    }

    isLoginUnique(login, id) {
        return http.get(`${this.defaultPath}/users/isLoginUnique?login=${login}&id=${id}`);
    }

    isEmailUnique(email, id) {
        return http.get(`${this.defaultPath}/users/isEmailUnique?email=${email}&id=${id}`);
    }
}

export default new UserDataService()