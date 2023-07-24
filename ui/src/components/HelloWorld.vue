<template>
  <div class="container">
    <h1 class="text-dark">All Sets:</h1>
    <table class="table table-striped">
      <thead>
        <th>Date</th>
        <th>Type</th>
        <th>Weight</th>
        <th>Repetitions</th>
      </thead>
      <tbody>
        <tr v-for="set in sets" v-bind:key="set.id">
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
import getAllSetsService from "@/service/getAllSetsService";

export default {
  name: 'HomePage',
  data(){
    return {
      payload: []
    }
  },
  methods: {
    getPayload(){
      console.log("getpayload")
      getAllSetsService.getAll().then(
        (response) => {
          this.payload=response.data;
        }
      )
    }
  },
  created() {
    this.getPayload();
  }

}
</script>
