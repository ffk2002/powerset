import axios from "axios";
const dest = "http://localhost:8080/set"


class postNewSetService{

    async postSet(type, reps, weight, date) {
        return await axios.post(dest, {
            type: type,
            reps: reps,
            weight: weight,
            date: date
        }, {
            'Content-Type': 'multipart/form-data'
        });
    }
}

export default new postNewSetService()