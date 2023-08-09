<template>
  <div class="container">
    <h1 class="text-dark"><div style="text-align: center;">Record:</div></h1>
    <div>
      <input class="input-group-text" v-model="activity" placeholder="enter activity">
      <input class="input-group-text" v-model="repetitions" placeholder="enter repetitions">
      <input class="input-group-text" v-model="weight" placeholder="enter weight">
      <input type="date" class="input-group-text" v-model="date">
    </div>
    <div>
      <button class="btn btn-outline-dark" @click="submit(); routeDashboard();">Record</button>
      <button class="btn btn-outline-danger" @click="routeRecord()">Cancel</button>
    </div>
  </div>


</template>

<script>
import postNewSetService from "@/service/postNewSetService";

export default {
  name: 'RecordPage',
  data(){
    return {
      activity: '',
      repetitions: null,
      weight: null,
      date: new Date().toISOString().split("T")[0]
    }
  },
  methods: {
    postNewSet(type, reps, weight, date){
      console.log("POST")
      postNewSetService.postSet(type, reps, weight, date).then(
          (response) => {
            this.responseData=response.data
            console.log(response)
          }
      )
    },
    submit(){
      console.log("submitandreturn")
      this.postNewSet(this.activity, this.repetitions, this.weight, this.date)
    },
    async routeDashboard(){
      await this.$router.push({name: 'Dashboard'})
    },
  },

}
</script>
<style>
.scroll {
  max-height: 250px;
  overflow: auto;
  padding-bottom: 20px;
}
</style>