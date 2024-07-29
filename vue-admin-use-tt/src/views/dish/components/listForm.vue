<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" width="550" destroy-on-close>
    <el-form ref="ruleForm" :model="formData" :rules="formRules" label-width="100px">
      <!-- 菜品名称输入框 -->
      <el-form-item label="菜品名称" prop="name">
        <el-input v-model="formData.name" />
      </el-form-item>
      <!-- 菜品描述输入框 -->
      <el-form-item label="菜品描述" prop="description">
        <el-input v-model="formData.description" type="textarea" rows="3" />
      </el-form-item>
      <!-- 菜品分类选择框 -->
      <el-form-item label="菜品分类" prop="categoryId">
        <el-select v-model="formData.categoryId" clearable placeholder="请选择" style="width: 200px">
          <el-option v-for="item in formData.cate" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <!-- 价格输入框 -->
      <el-form-item label="价格" prop="price">
        <el-input-number v-model="formData.price" :min="0" :step="1" :precision="2" />
      </el-form-item>
      <!-- 是否热销开关 -->
      <el-form-item label="是否热销" prop="isHot">
        <el-switch v-model="formData.isHot" inactive-value="0" />
      </el-form-item>
      <!-- 菜品图片上传 -->
      <el-form-item label="菜品图片" prop="image">
        <el-upload class="avatar-uploader" action="/api/common-fileupload/file/upload"
                   :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar" style="max-height: 250px"/>
          <div class="box" v-else>
            <i class="el-icon-plus avatar-uploader-icon" style="font-size: 40px"></i>
          </div>
        </el-upload>
      </el-form-item>
      <!-- 口味选择 -->
      <el-form-item label="口味选择">
        <div v-if="formData.flavors.length === 0">
          <el-button type="primary" @click="addFlavor">添加口味类别</el-button>
        </div>
        <div v-else>
          <div v-for="(flavor, index) in formData.flavors" :key="index" class="flavor">
            <!-- 口味标签 -->
            <el-form-item :label="flavor.name" :prop="'flavors[' + index + '].value'">
              <el-tag
                v-for="tag in flavor.value"
                :key="tag"
                closable
                @close="handleClose(flavor, tag)">
                {{tag}}
              </el-tag>
              <!-- 口味输入框 -->
              <el-input
                class="input-new-tag"
                v-if="flavor.inputVisible"
                v-model="flavor.inputValue"
                ref="saveTagInput"
                size="small"
                @keyup.enter.native="handleInputConfirm(flavor, index)"
                @blur="handleInputConfirm(flavor, index)"
              />
              <!-- 新增口味按钮 -->
              <el-button v-else class="button-new-tag" size="small" type="primary" @click="showInput(flavor, index)">新增口味</el-button>
            </el-form-item>
            <!-- 删除口味类别按钮 -->
            <el-button type="danger" size="small"  @click="removeFlavor(index)">删除口味类别</el-button>
            <!-- 编辑名称按钮 -->
            <el-button class="button-edit-name" size="small" @click="editFlavorName(flavor)">编辑类别名称</el-button>
          </div>
          <el-button type="primary" @click="addFlavor">添加口味类别</el-button>
        </div>
      </el-form-item>
    </el-form>
    <slot name="footer">
      <!-- 提交按钮 -->
      <el-button @click="submitForm('ruleForm')" type="primary">提交</el-button>
      <!-- 取消按钮 -->
      <el-button @click="dialogVisible = false">取消</el-button>
    </slot>
  </el-dialog>
</template>

<script>
import { getAllCate, addDish, updateDish, getDishDetail } from "@/api/dish";

export default {
  data() {
    return {
      dialogVisible: false, // 对话框显示状态
      formData: {
        type: 0, // 1新增 2修改
        price: 0,
        cate: [], // 菜品分类列表
        cateValue: "",
        name: '',
        description: '',
        categoryId: null,
        isHot: 0,
        image: '',
        flavors: [], // 口味列表
      },
      imageUrl: '', // 预览图片URL
      title: "", // 对话框标题
      formRules: {
        name: [
          { required: true, message: '请输入菜品名称', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请填写菜品介绍', trigger: 'blur' },
          { min: 0, max: 30, message: '菜品名称最多30个字符', trigger: 'blur' }
        ],
        categoryId: [
          { required: true, message: '请选择菜品品类', trigger: 'change' }
        ],
        price: [
          { required: true, message: '请填写菜品价格', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    // 确认口味输入
    handleInputConfirm(item, index) {
      const inputValue = item.inputValue.trim();
      if (inputValue && !item.value.includes(inputValue)) {
        item.value.push(inputValue);
      }
      item.inputVisible = false;
      item.inputValue = '';
      this.$set(this.formData.flavors, index, item); // 更新数组中的元素
    },

    // 显示口味输入框
    showInput(item, index) {
      const obj = { ...item, inputVisible: true, inputValue: '' };
      this.$nextTick(() => {
        this.$refs.saveTagInput[index]?.$refs.input?.focus();
      });
      this.$set(this.formData.flavors, index, obj); // 更新数组中的元素
    },

    // 添加口味类别
    addFlavor() {
      const newFlavor = { name: '默认口味类别', value: [], inputVisible: true, inputValue: '' };
      this.formData.flavors.push(newFlavor);
      this.$nextTick(() => {
        const index = this.formData.flavors.length - 1;
        this.$refs.saveTagInput[index]?.$refs.input?.focus();
      });
    },

    // 删除口味类别
    removeFlavor(index) {
      this.formData.flavors.splice(index, 1);
    },

    // 处理口味标签的关闭事件
    handleClose(flavor, tag) {
      const index = flavor.value.indexOf(tag);
      if (index > -1) {
        flavor.value.splice(index, 1);
      }
    },

    // 提交表单
    submitForm(formName) {
      console.log("formName",formName )
      this.$refs[formName].validate((valid) => {
        console.log("valid",valid)
        if (valid) {
          console.log("this.formData",this.formData)
          if (this.formData.type === 1) {
            this.addDishByFormData(); // 新增菜品
          } else if (this.formData.type === 2) {
            this.updateDishByFormData(); // 更新菜品
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    // 处理头像上传成功
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      this.formData.image = res.data.url;
    },

    // 验证头像上传
    beforeAvatarUpload(file) {
      const isImage = file.type === 'image/jpeg' || file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isImage) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isImage && isLt2M;
    },

    // 重置表单数据
    resetForm() {
      this.formData = {
        price: 0,
        cate: [],
        cateValue: "",
        name: '',
        description: '',
        categoryId: null,
        isHot: 0,
        image: '',
        flavors: []
      };
    },

    // 打开对话框并获取菜品详情
    open(id) {
      this.dialogVisible = true;
      this.getListCate();
      if (id) {
        this.formData.type = 2; // 修改
        this.title = "编辑菜品";
        this.getDishDetailById(id);
      } else {
        this.resetForm();
        this.formData.type = 1; // 新增
        this.title = "新增菜品";

      }
    },

    // 获取菜品详情
    async getDishDetailById(id) {
      try {
        await getDishDetail(id).then(res=>{
          if (res.code===20000){
            // 处理 flavors 数据，将 value 字段从 JSON 字符串转换为数组
            res.data.flavors.forEach(flavor => {
              flavor.value = JSON.parse(flavor.value);
            });
            this.formData = { ...res.data, type: 2 };
            this.imageUrl =  res.data.image;
          }
          console.log("this.formData",this.formData);

        });

      } catch (error) {
        console.error(error);
      }
    },

    // 新增菜品
    async addDishByFormData() {
      console.log("新增菜品");
      try {
        const data = {
          name: this.formData.name,
          categoryId: this.formData.categoryId,
          price: this.formData.price,
          image: this.formData.image,
          isHot: this.formData.isHot,
          description: this.formData.description,
          flavors: this.formData.flavors.map(flavor => {
            // 创建一个新对象，排除 inputVisible 和 inputValue 属性
            return {
              name: flavor.name,
              value: JSON.stringify(flavor.value)
            };
          })
        };

        console.log("type data.flavors", typeof data.flavors);
        await addDish(data).then(res => {
          console.log("res", res);
        });
        this.$message.success('新增成功');
        this.dialogVisible = false;
        this.$emit('refresh'); // 刷新列表
      } catch (error) {
        console.error(error);
        this.$message.error('新增失败');
      }
    },

    // 更新菜品
    async updateDishByFormData() {
      try {
        console.log("updateDishByFormData this.formData",this.formData)
        await updateDish(this.formData).then(res=>{
           console.log("res",res)
         });
        // this.$message.success('更新成功');
        // this.dialogVisible = false;
        this.$emit('refresh'); // 刷新列表
      } catch (error) {
        console.error(error);
        this.$message.error('更新失败');
      }
    },

    // 编辑口味名称
    editFlavorName(flavor) {
      const newName = prompt('请输入新的口味名称', flavor.name);
      if (newName) {
        flavor.name = newName;
      }
    },
    //获取全部菜品类别
    getListCate(){
      console.log("获取全部类别")
      getAllCate(1).then(res => {
        this.formData.cate = res.data;
      })
    },
  },
}
</script>

<style scoped>
.avatar {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.box {
  height: 100px;
  width: 100%;
  border: 1px dashed #dcdfe6;
  display: flex;
  justify-content: center;
  align-items: center;
}
.input-new-tag {
  width: 100px;
}
.button-new-tag {
  margin: 10px 0;
}
.button-edit-name {
  margin: 10px;
}
.flavor {
  margin-bottom: 20px;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
