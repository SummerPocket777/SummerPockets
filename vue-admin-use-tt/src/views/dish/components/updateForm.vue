<template>
    <el-dialog :title="title" :visible.sync="dialogVisible" width="550">
        <el-form ref="ruleForm" :model="formData" :rules="formRules" label-width="100px">
            <el-form-item label="菜品名称" prop="name">
                <el-input v-model="formData.name" />
            </el-form-item>
            <!-- 商品描述 -->
            <el-form-item label="菜品描述" prop="desc">
                <el-input v-model="formData.desc" type="textarea" rows="3" />
            </el-form-item>
            <!-- 商品分类 -->
            <el-form-item label="菜品分类" prop="cate">
                <!--        <CateSelect v-model="formData.cate" />-->
                <el-select v-model="formData.cate" placeholder="请选择" style="width: 200px">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />

                </el-select>
            </el-form-item>
            <!-- 商品价格 -->
            <el-form-item label="价格" prop="price">
                <el-input-number v-model="formData.price" :min="0" :step="0.1" :precision="2" />
            </el-form-item>

            <!-- 是否热销 -->
            <el-form-item label="是否热销" prop="hot">
                <el-switch v-model="formData.hot" />
            </el-form-item>

            <!-- 商品图片 -->
            <el-form-item label="菜品图片" prop="type">
                <!-- action 就是图片上传的接口 -->
                <!--        <ImgUpload />-->
                <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/"
                    :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                    <div class="box" v-else>
                        <i class="el-icon-plus avatar-uploader-icon" style="font-size: 40px"></i>
                    </div>
                </el-upload>
            </el-form-item>

        </el-form>
        <slot name="footer">
            <el-button @click="submitForm('ruleForm')" type="primary">提 交</el-button>
            <el-button @click="dialogVisible = false">取 消</el-button>
        </slot>
    </el-dialog>
</template>
<script>
export default {
    data() {
        return {
            dialogVisible: false,
            formData: {
                price: 0
            },
            options: [{
                value: '选项1',
                label: '黄金糕'
            }, {
                value: '选项2',
                label: '双皮奶'
            }, {
                value: '选项3',
                label: '蚵仔煎'
            }, {
                value: '选项4',
                label: '龙须面'
            }, {
                value: '选项5',
                label: '北京烤鸭'
            }],
            imageUrl: '',
            title: "",
            formRules: {
                name: [
                    { required: true, message: '请输入菜品名称', trigger: 'blur' }
                ],
                desc: [
                    { required: true, message: '请填写菜品介绍', trigger: 'blur' },
                    {
                        min: 0,
                        max: 30,
                        message: '菜品名称最多30个字符',
                        trigger: 'blur'
                    }
                ],
                cate: [
                    { required: true, message: '请选择菜品品类', trigger: 'change' }
                ],
                price: [
                    { required: true, message: '请填写菜品价格', trigger: 'change' }
                ]
            }
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        resetForm() {
            this.formData = { price: 0 }
        },
        open(id) {
            console.log('openA')
            this.dialogVisible = true
            if (id) {
                this.title = "商品编辑"
            } else {
                this.title = "商品添加"
            }
        }
    },
}
</script>
<style scoped>
.box{
    width: 100px;
    height: 100px;
    border: 2px solid gainsboro;
    border-radius: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
