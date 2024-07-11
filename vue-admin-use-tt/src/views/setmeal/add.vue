<template>
  <div class="addBrand-container">
    <div class="container">
      <el-form ref="ruleForm"
               :model="ruleForm"
               :rules="rules"
               :inline="true"
               label-width="180px"
               class="demo-ruleForm">
        <div>
          <el-form-item label="套餐名称:"
                        prop="name">
            <el-input v-model="ruleForm.name"
                      placeholder="请填写套餐名称"
                      maxlength="14" />
          </el-form-item>
          <el-form-item label="套餐分类:"
                        prop="idType">
            <el-select v-model="ruleForm.idType"
                       placeholder="请选择套餐分类"
                       @change="$forceUpdate()">
              <el-option v-for="(item, index) in setMealList"
                         :key="index"
                         :label="item.name"
                         :value="item.id" />
            </el-select>
          </el-form-item>
        </div>
        <div>
          <el-form-item label="套餐价格:"
                        prop="price">
            <el-input v-model="ruleForm.price"
                      placeholder="请设置套餐价格" />
          </el-form-item>
        </div>
        <div>
          <el-form-item label="套餐菜品:"
                        required>
            <el-form-item>
              <div class="addDish">
                <span v-if="dishTable.length == 0"
                      class="addBut"
                      @click="openAddDish('new')">
                  + 添加菜品</span>
                <div v-if="dishTable.length != 0"
                     class="content">
                  <div class="addBut"
                       style="margin-bottom: 20px"
                       @click="openAddDish('change')">
                    + 添加菜品
                  </div>
                  <div class="table">
                    <el-table :data="dishTable"
                              style="width: 100%">
                      <el-table-column prop="name"
                                       label="名称"
                                       width="180"
                                       align="center" />
                      <el-table-column prop="price"
                                       label="原价"
                                       width="180"
                                       align="center">
                        <template slot-scope="scope">
                          {{ (Number(scope.row.price).toFixed(2) * 100) / 100 }}
                        </template>
                      </el-table-column>
                      <el-table-column prop="address"
                                       label="份数"
                                       align="center">
                        <template slot-scope="scope">
                          <el-input-number v-model="scope.row.copies"
                                           size="small"
                                           :min="1"
                                           :max="99"
                                           label="描述文字" />
                        </template>
                      </el-table-column>
                      <el-table-column prop="address"
                                       label="操作"
                                       width="180px;"
                                       align="center">
                        <template slot-scope="scope">
                          <el-button type="text"
                                     size="small"
                                     class="delBut non"
                                     @click="delDishHandle(scope.$index)">
                            删除
                          </el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </div>
                </div>
              </div>
            </el-form-item>
          </el-form-item>
        </div>
        <div>
          <el-form-item label="套餐图片:"
                        required
                        prop="image">
            <image-upload :prop-image-url="imageUrl"
                          @imageChange="imageChange">
              图片大小不超过2M<br>仅能上传 PNG JPEG JPG类型图片<br>建议上传200*200或300*300尺寸的图片
            </image-upload>
          </el-form-item>
        </div>
        <!-- <el-form-item
          label="商品码:"
          prop="code"
        >
          <el-input
            v-model="ruleForm.code"
            placeholder="请输入商品码"
          />
        </el-form-item> -->
        <div class="address">
          <el-form-item label="套餐描述:">
            <el-input v-model="ruleForm.description"
                      type="textarea"
                      :rows="3"
                      maxlength="200"
                      placeholder="套餐描述，最长200字" />
          </el-form-item>
        </div>
        <div class="subBox address">
          <el-form-item>
            <el-button @click="() => $router.back()">
              取消
            </el-button>
            <el-button type="primary"
                       :class="{ continue: actionType === 'add' }"
                       @click="submitForm('ruleForm', false)">
              保存
            </el-button>
            <el-button v-if="actionType == 'add'"
                       type="primary"
                       @click="submitForm('ruleForm', true)">
              保存并继续添加
            </el-button>
          </el-form-item>
        </div>
      </el-form>
    </div>
    <el-dialog v-if="dialogVisible"
               title="添加菜品"
               class="addDishList"
               :visible.sync="dialogVisible"
               width="60%"
               :before-close="handleClose">
      <el-input v-model="value"
                class="seachDish"
                placeholder="请输入菜品名称进行搜索"
                style="width: 293px; height: 40px"
                size="small"
                clearable>
        <i slot="prefix"
           class="el-input__icon el-icon-search"
           style="cursor: pointer"
           @click="seachHandle" />
      </el-input>
      <AddDish v-if="dialogVisible"
               ref="adddish"
               :check-list="checkList"
               :seach-key="seachKey"
               :dish-list="dishList"
               @checkList="getCheckList" />
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary"
                   @click="addTableList">添 加</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import CateSelect from './components/CateSelect.vue'
import ImgUpload from './components/ImgUpload.vue'

import { submitGood } from '@/api/goods'

export default {
  name: 'GoodForm',
  components: {
    CateSelect,
    ImgUpload
  },
  props: [],
  data() {
    return {
      ruleForm: {
        name: '',
        desc: '',
        cate: '',
        price: 0,
        hot: true,
        img: ''
      },
      rules: {
        // 对name这个字段进行校验

        desc: [
          { required: true, message: '请填写商品介绍', trigger: 'blur' },
          {
            min: 0,
            max: 30,
            message: '商品名称最多30个字符',
            trigger: 'blur'
          }
        ],
        cate: [
          { required: true, message: '请选择商品品类', trigger: 'change' }
        ],
        price: [
          { required: true, message: '请填写商品价格', trigger: 'change' }
        ]
        // img: [{ required: true, message: '请上传商品图片', trigger: 'change' }]
      }
    }
  },
  methods: {
    submitForm(formName) {
      console.log(this.ruleForm)

      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!')
          // 此处进行提交操作
          const data = { ...this.ruleForm }
          submitGood(data).then(res => {
            if (res.data.info) {
              this.$message({
                type: 'success',
                message: '添加成功',
                duration: 1500,
                onClose: () => {
                  this.$router.back('/good/good-list')
                }
              })
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style lang="scss" scoped>
.good-form {
  box-sizing: border-box;
  padding: 20px;
}
</style>
