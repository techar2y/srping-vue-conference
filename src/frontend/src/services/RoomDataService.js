import http from "../../http-common"

const API_URL = "/rooms";

class RoomDataService {
    getAllRooms () {
        return http.get(`${API_URL}/getAllRooms`);
    }
}

export default new RoomDataService()