import axios from "axios";
const source = "http://localhost:8080/set/types"


class getSetsByTypeService{

    async getByType() {
        return await axios.get(source);
    }
}

export default new getSetsByTypeService()