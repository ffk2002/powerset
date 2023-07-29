<template>
  <div class="container">
    <h1 class="text-dark">Dashboard:</h1>


    <table v-for="type in setsByType" v-bind:key="setsByType[type]" class="d-table">
      <caption>{{ type[0]["type"] }}</caption>
      <thead>
        <th>Date</th>
        <th>Type</th>
        <th>Weight</th>
        <th>Repetitions</th>
      </thead>
      <tbody>
        <tr v-for="set in type" v-bind:key="set.id">
          <td> {{set.date}}</td>
          <td> {{set.type}}</td>
          <td> {{set.weight}}</td>
          <td> {{set.reps}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import getAllSetsService from "../service/getAllSetsService";
import getSetsByTypeService from "@/service/getSetsByTypeService";

export default {
  name: 'HomePage',
  data(){
    return {
      payload: [],
      setsByType: {}
    }
  },
  methods: {
    getPayload(){
      console.log("getpayload")
      getAllSetsService.getAll().then(
        (response) => {
          this.payload=response.data._embedded.pSetList;
        }
      )
    },
    getByType(){
      console.log("get by type")
      getSetsByTypeService.getByType().then(
          (response) => {
            this.setsByType = response.data;
            console.log(this.setsByType)
          }
      )
    }
  },
  created() {
    this.getPayload();
    this.getByType();
  }

}
</script>
