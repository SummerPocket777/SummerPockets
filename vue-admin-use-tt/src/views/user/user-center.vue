<template>
  <div class="body">
    <div id='title' >
    </div>
    <div id='title-bar'>
      <div class="wallet">
        <div class="wallet-title">我的钱包 :</div>
        <div class="money">￥77.00 CNY</div>
      </div>
      <div class="btns" style="  margin-top: 75px;">
        <el-button type="primary">提现</el-button>
        <el-button type="danger">开店</el-button>
      </div>
    </div>
    <div class="cards">
      <div class="bigcard" style="display: flex;">
        <div style="margin-left: 50px;margin-top: 50px;" >
          <div class="text">店铺名称 ： {{this.dataForm.businessName}}</div>
          <div class="text">当前权限 ： </div>
          <div class="text">联系方式 ： {{this.dataForm.phone}}</div>
          <div class="text">地址信息 ： {{this.dataForm.address}}</div>
          <el-button type="primary" style="margin-left: 200px;margin-top: 60px;" @click="dialogFormVisible = true">修改信息</el-button>
        </div>
      </div>

      <el-dialog title="信息修改" :visible.sync="dialogFormVisible">
        <el-form :model="dataForm">
          <el-form-item label="店铺名称" :label-width="formLabelWidth">
            <el-input v-model="dataForm.businessName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="联系方式" :label-width="formLabelWidth">
            <el-input v-model="dataForm.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="地址信息" :label-width="formLabelWidth">
            <el-input v-model="dataForm.address" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <!-- <el-amap  :zoom="zoom"  class="amap-demo">
              <el-amap-control-geolocation
              position="RB"
              :offset="offset"
              :panToLocation="panToLocation"
              :zoomToAccuracy="zoomToAccuracy"
              :needAddress="needAddress"
              :extensions="extensions"
              :enableHighAccuracy="enableHighAccuracy"
              :visible="visible"
              @complete="getLocation"></el-amap-control-geolocation>
              <el-amap-control-geolocation :visible="visible" @complete="getLocation"></el-amap-control-geolocation>

            </el-amap> -->
            <map-view @sendAddress="getAddress"></map-view>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateUserDetail">确 定</el-button>
        </div>
      </el-dialog>





      <div class="bigsubcard">

        <div>
          <el-image
            style="width: 60%;    border-radius: 30px; margin-left: 20%;margin-top: 10%;"
            :src="url"
            :fit="fit"></el-image>

        </div>
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          multiple
          :limit="1">
          <el-button type="primary" style="margin-top: 10%;">修改logo</el-button>
        </el-upload>
      </div>

      <div class="midcard" style="display: flex; justify-content: center; align-items: center;">
          <el-button type="primary" style="width: 200px;" @click="merchantInit">点击进行商家认证</el-button>
      </div>
      <el-dialog
        title="第一步: 身份认证"
        :visible.sync="firstDialogVisible"
        width="700px"

        >
        <template class="dialog-upload">
           <div class="firstImage" height="250px">
            <el-upload
              class="upload-demo"
              :action="uploadAction"
              multiple
              drag>
              <el-button type="text">上传身份证人像面</el-button>
            </el-upload>
           </div>
           <div class="firstImage" height="250px">
            <el-upload
              class="upload-demo"
              :action="uploadAction"
              multiple
              drag>
              <el-button type="text">上传身份证国徽面</el-button>

            </el-upload>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
           </div>
        </template>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" icon="el-icon-arrow-right"  @click="setSecondDialog">下一步</el-button>
        </span>
      </el-dialog>

      <el-dialog
        title="第二步: 商家资质认证"
        :visible.sync="secondDialogVisible"
        width="700px"
        >
        <template class="dialog-upload">
           <div class="firstImage" >
            <el-upload
              class="upload-demo"
              :action="uploadAction"
              multiple
              drag>
              <el-button type="text">上传营业执照</el-button>
            </el-upload>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
           </div>
        </template>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" icon="el-icon-arrow-left"  @click="setSecondDialog">上一步</el-button>
          <el-button type="primary" icon="el-icon-arrow-right"  @click="setThirdDialog">下一步</el-button>
        </span>
      </el-dialog>

      <el-dialog
        title="第三步: 人脸识别"
        :visible.sync="thirdDialogVisible"
        width="700px"
        >
        <template class="dialog-upload">
           <div class="firstImage" >
            <el-upload
              class="upload-demo"
              :action="uploadAction"
              multiple
              drag>
              <el-button type="text">上传营业执照</el-button>
            </el-upload>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
           </div>
        </template>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" icon="el-icon-arrow-left"  @click="setThirdDialog">上一步</el-button>
          <el-button type="primary" icon="el-icon-arrow-right"  @click="confirmDialog">提交</el-button>
        </span>
      </el-dialog>
      <div class="midcard"></div>
    </div>



  </div>
</template>
<script>
import mapView from './MapContainer.vue'
import { mapState,mapActions } from 'vuex'
// import { getUserDetail, updateUserCenter } from "@/api/userCenter";

export default {
  components:{
     mapView
  },
  data() {
    return {
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      fit: 'cover',
      dialogFormVisible: false,
      dataForm: {
        businessName: '',
        phone: '',
        address: '',
        id:''
      },
      formLabelWidth: '120px',

      firstDialogVisible:false,
      uploadAction:'',
      secondDialogVisible:false,
      thirdDialogVisible:false
      // zoom: 12,
      // // center: [121.59996, 31.197646],
      // visible: true,
      // needAddress:true,
    }
  },
  computed:{
    ...mapState('userCenter',['userDetail','userInfo'])
  },
  created(){
    
  },
  mounted() {
    this.getUserDetail().then(()=>{
      this.getInfo()
    })
    



    // getUserDetail().then(res => {
    //             console.log("获取到用户信息: ", res.data)
    //             // commit('getUserDetail', res.data)
    //             // console.log("getUserDetail: ", state.userDetail)
    //             this.dataForm = res.data


    //         }).catch(error => {
    //             console.log("error: ", error)

    //         })
  },
  methods: {
    ...mapActions('userCenter',['updateUserInfo','getUserDetail']),
    getInfo(){
      console.log("detail: ",this.userDetail)
      this.dataForm.businessName = this.userDetail.businessName
      this.dataForm.phone = this.userDetail.phone
      this.dataForm.address = this.userDetail.address
      this.dataForm.id = this.userDetail.id
      console.log("被赋值后的form: ",this.dataForm)
    },
    updateUserDetail(){
      // var form = {
      //   businessName: this.dataForm.businessName,
      //   id: this.dataForm.id,
      //   phone:this.dataForm.phone,
      //   address:this.dataForm.address
      // };
      // updateUserCenter(form).then((res)=>{
      //    this.dataForm = form
      // })
        console.log("form1: ",this.dataForm)
        this.updateUserInfo(this.dataForm).then(() =>{
          console.log('-------------')
              this.getUserDetail().then(()=>{
              this.$nextTick(()=>{
                this.getInfo()
              })

            }).catch(error =>{
              console.log(error)
            })

          // console.log("form2: ",this.dataForm)
          this.$message({
            type:'success',
            message: '信息修改成功',
            duration: 1000,
            center:true,
            showClose: true,
            onClose: (()=>{
              this.dialogFormVisible=false
            })
          })

          // else{
          //   this.$message({
          //       type:'error',
          //       message: '信息修改失败,请重试',
          //       duration: 1000,
          //       center,
          //       showClose,
          //    })
          // }
        }).catch(error =>{
          console.log(error)
        })
    },
    getAddress(msg){
      // console.log("获取到地址: ",msg.address)
      // this.form.address=msg.
      // console.log("获取到用户信息: ",this.getUserDetail())
      this.dataForm.address=msg.address

    },
    merchantInit(){
        this.firstDialogVisible=true
    },
    setSecondDialog(){
      this.firstDialogVisible=!this.firstDialogVisible
      this.secondDialogVisible=!this.secondDialogVisible

    },
    setThirdDialog(){
      this.secondDialogVisible = !this.secondDialogVisible
      this.thirdDialogVisible = !this.thirdDialogVisible

    },
    confirmDialog(){
      this.thirdDialogVisible = !this.thirdDialogVisible
    }
  },


}
</script>

<style scoped>
  *{
    margin: 0; /*外边距*/
    padding: 0; /*内边距*/
    box-sizing: border-box; /*盒子大小规则*/
    font-family: sans-serif; /*字体:非衬线*/
  }
  .amap-demo {
      height: 300px;
    }

  #title{
    position: absolute; /*绝对定位*/
    top: 3%; /*距上部*/
    left: 5%;
    right: 5%;
    bottom: 75%;
    border-radius: 10px; /*圆角边框*/
    display: flex; /*弹性盒模型*/
    justify-content: center; /*主轴对齐方式*/
    align-items: center; /*交叉轴对齐方式*/
    background: url('../../assets/images/bg.jpg'), #151729; /*背景图片，颜色*/
    box-shadow: 0 15px 30px rgba(0, 0, 0, 0.5); /*背景阴影*/
  }


  #title-bar{
    position: absolute; /*绝对定位*/
    top: 20%; /*距上部*/
    left: 8%;
    right: 8%;
    bottom: 60%;
    border-radius: 10px; /*圆角边框*/
    display: flex; /*弹性盒模型*/


    background-color: whitesmoke; /*背景图片，颜色*/
    box-shadow: 0 15px 30px rgba(0, 0, 0, 0.5); /*背景阴影*/
  }
  .wallet{
    margin-left: 50px;
  }
  .wallet-title{
    margin-top: 35px;
    font-size: 20px;
    color: gray;
  }
  .money{
    margin-top: 20px;
    margin-left:20px;
    font-size: 35px;
    color:black;
  }
.btns{
  margin-left: auto;
  margin-right: 50px;
}
.el-button{
  width: 100px;
  height: 50px;
}

.bigcard{
  display: block;
  box-sizing: border-box;
  width: 46%;
  height: 360px;
  background-color: whitesmoke;
  margin: 2%;
  border-radius: 30px;

}

.bigsubcard{
  display: block;
  box-sizing: border-box;
  width: 25%;
  height: 360px;
  background-color: whitesmoke;
  margin: 2%;
  border-radius: 30px;

}


.midcard{
  display: block;
  box-sizing: border-box;
  width: 40%;
  height: 100px;
  background-color: whitesmoke;
  margin: 10px;
  border-radius: 30px;

}
.cards{
  margin-top: 25%;
  margin-left: 5%;
  display: flex;
  flex-wrap: wrap;
}


.text{
  font-size: 18px;
  margin: 15px;
}

.el-dialog__body{
  height: 500px;
}

.upload-demo{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
  /* justify-content: center; */
}

</style>
