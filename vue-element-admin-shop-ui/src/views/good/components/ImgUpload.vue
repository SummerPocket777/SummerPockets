<template>
  <el-upload
    class="upload-demo"
    drag
    action="/api/common-fileupload/fileupload/file/upload"
    multiple
    name="good"
    :show-file-list="false"
  >
    <!-- name是上传图片的名称，后端使用这个名称去接收图片 -->
    <i class="el-icon-upload" />
    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
  </el-upload>
</template>

<script>
export default {

  name: 'ImgUpload',
  props: [],
  data() {
    return {
      imgList: []
    }
  }
}
</script>

<style>

</style>
