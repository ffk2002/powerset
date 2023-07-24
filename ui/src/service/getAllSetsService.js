import axios from "axios";
const source = "http://localhost:8080/set"


class getAllSetsService{
    async getAll() {
        return await axios.get(source);
    }
}

export default new getAllSetsService()