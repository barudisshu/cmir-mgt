<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'CmirGit',
  data() {
    return {
      url: 'https://github.com/barudisshu/cmir-mgt'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
