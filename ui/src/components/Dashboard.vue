<template>
  <div class="container">
    <h1 class="text-dark"><div style="text-align: center;">Dashboard:</div></h1>

    <div class="container">
      <div class="row">
        <div class="col-md-4 scroll" v-for="type in setsByType" v-bind:key="setsByType[type]">
          <table class="table table-bordered table-striped">
            <caption style="caption-side: top">{{ type[0]["type"] }}</caption>
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
      </div>
    </div>
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
<style>
.scroll {
  max-height: 250px;
  overflow: auto;
  padding-bottom: 20px;
}
</style>