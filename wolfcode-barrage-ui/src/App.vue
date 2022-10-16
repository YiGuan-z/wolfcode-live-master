<template>
  <div id="app">
      <player/>
  </div>
</template>
<script>
import Player from "./components/player/Player";
export default {
  components: {
    "player": Player
  },


}
</script>


<style>

#app {
  margin: 60px auto;
  /*width: 1300px;*/
  /*height: 600px;*/
  border-radius: 4px;
}


</style>