import http from "../../http-common"

const API_URL = "/roles";

class RoleDataService {
    getAllRoles () {
        return http.get(`${API_URL}/getAllRoles`);
    }
}

export default new RoleDataService()