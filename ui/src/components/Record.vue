<template>
  <div class="container">
    <h1 class="text-dark"><div style="text-align: center;">Record:</div></h1>
    <input class="input-group-text" v-model="activity" placeholder="enter activity">
    <input class="input-group-text" v-model="repetitions" placeholder="enter repetitions">
    <input class="input-group-text" v-model="weight" placeholder="enter weight">
    <input class="input-group-text" v-model="date" placeholder="enter date">
    <div>
      <button class="btn btn-outline-dark" @click="routeRecord()">Record</button>
      <button class="btn btn-outline-danger" @click="routeRecord()">Cancel</button>
    </div>
  </div>


</template>

<script>
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
  },
  created() {
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