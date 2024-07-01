<template>
  <div>
    <el-select :value="value" placeholder="请选择" @change="$emit('input', $event)">
      <el-option
        v-for="item in options"
        :key="item.id"
        :label="item.cate_zh"
        :value="item.cate"
      />
    </el-select>

  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: 'CateSelect',
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  data() {
    return {

      options: [
        { cate: '选项1', cate_zh: '黄金糕' },
        { cate: '选项2', cate_zh: '双皮奶' },
        { cate: '选项3', cate_zh: '蚵仔煎' },
        { cate: '选项4', cate_zh: '龙须面' },
        { cate: '选项5', cate_zh: '北京烤鸭' }
      ]
    }
  },
  computed: {

    // goods模块下的分类选项的属性
    ...mapState('goods', ['cates'])
  },
  created() {
    // this.getCates()
  },
  methods: {
    ...mapActions('goods', ['getCates'])
  }

}
</script>

<style>

</style>
