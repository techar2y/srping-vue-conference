import http from "../../http-common"

class RoomDataService {
    getAllRooms () {
        return http.get(`/rooms`);
    }
}

export default new RoomDataService()