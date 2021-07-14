import http from "../../http-common.js";

class UserDataService {
    getAllUsers() {
        return http.get("/users");
    }

    getUserById(id) {
        return http.get(`/users/${id}`);
    }

    saveUser(data) {
        return http.post("/users", data);
    }

    findUserByLogin(title) {
        return http.get(`/users?title=${title}`);
    }

    deleteAllUsers() {
        return http.delete("/users");
    }

    deleteUserById(id) {
        return http.delete(`/users/${id}`);
    }

    update(id, user) {
        return http.put(`/users/${id}`, user);
    }
}

export default new UserDataService()