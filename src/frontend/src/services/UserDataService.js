import http from "../../http-common.js";

class UserDataService {
    getAllUsers(params) {
        return http.get("/users", { params: params });
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