import axios from "axios";
const source = "http://localhost:8080/login"


class loginService{

    async fetchLogin() {
        return await axios.get(source);
    }
}

export default new loginService()