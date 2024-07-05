<template>
  <div>
    <el-button type="text" @click="dialogVisible = true">点击注册</el-button>

    <el-dialog
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <el-form
        class="registerForm"
        :model="registerForm"
        autocomplete="on"
        label-position="left"
      >
        <el-form-item label="用户：">
          <el-input
            v-model="registerForm.account"
            placeholder="请输入用户名"
            type="text"
            class="custom-input"
            tabindex="1"
            autocomplete="on"
          />
        </el-form-item>
        <el-form-item label="密码：">
          <el-input
            v-model="registerForm.password"
            placeholder="请输入密码"
            type="password"
            class="custom-input"
            tabindex="1"
            autocomplete="on"
          />
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="dialogVisible = false"
        >确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'RegisterForm',
  data() {
    return {
      registerForm: {
        account: '',
        password: '',
        phone: '',
        business_name: '',
        image_code: '',
        phone_code: ''
      },

      dialogVisible: false
    }
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then((_) => {
          done()
        })
        .catch((_) => {})
    }
  }
}
</script>

<style lang="scss" >
.el-input {
  color: black;
  background-color: rgb(36, 47, 60);
}
.el-dialog {
  background-color: rgb(40, 52, 67);
}
</style>

<style scoped>
</style>
