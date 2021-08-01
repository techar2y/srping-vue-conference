import http from "../../http-common"

class RoleDataService {
    getAllRoles () {
        return http.get(`/roles`);
    }
}

export default new RoleDataService()