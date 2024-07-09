<template>
  <div>
    <el-select :value="value" placeholder="请选择" @change="$emit('input', $event)">
      <el-option
        v-for="item in cates"
        :key="item.id"
        :label="item.name"
        :value="item.id"
      />
    </el-select>

  </div>
</template>

<script>

import { mapActions, mapState } from 'vuex'
import { string } from 'clipboard'
export default {
  name: 'CateSelect',
  props: {
    value: {
      type: string,
      default: ''
    }
  },
  data() {
    return {

    }
  },
  computed: {

    // goods模块下的分类选项的属性
    ...mapState('goods', ['cates'])

  },
  created() {
    this.getCates()
  },
  methods: {
    ...mapActions('goods', ['getCates'])

  }

}
</script>

<style>

</style>
