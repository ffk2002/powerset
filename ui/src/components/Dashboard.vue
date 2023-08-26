<template>
  <div class="container">
    <h1 class="text-dark"><div style="text-align: center;">Dashboard:</div></h1>
    <button class="btn btn-outline-dark" @click="routeRecord()">Record Set</button>
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
import getSetsByTypeService from "@/service/getSetsByTypeService";

export default {
  name: 'HomePage',
  data(){
    return {
      payload: [],
      setsByType: {},
      timeout: null
    }
  },
  methods: {
    routeRecord(){
      this.$router.push({name: 'Record'})
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
    this.getByType();
    this.timeout = setTimeout(this.getByType, 500)
  },
  beforeUnmount() {
    clearTimeout(this.timeout)
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