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
          <div class="text">店铺名称 ： XXXX</div>
          <div class="text">当前权限 ： XXXX</div>
          <div class="text">联系方式 ： XXXX</div>
          <div class="text">地址信息 ： XXXX</div>
          <el-button type="primary" style="margin-left: 200px;margin-top: 60px;" @click="dialogFormVisible = true">修改信息</el-button>
        </div>
      </div>

      <el-dialog title="信息修改" :visible.sync="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="店铺名称" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="联系方式" :label-width="formLabelWidth">
            <el-input v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="地址信息" :label-width="formLabelWidth">
            <el-input v-model="form.address" autocomplete="off"></el-input>
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
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>





      <div class="bigsubcard">

        <div>
          <el-image
            style="width: 60%;    border-radius: 30px; margin-left: 20%;margin-top: 10%;"
            :src="url"
            :fit="fit"></el-image>

        </div>
        <el-button type="primary" style="margin-left: 40%;margin-top: 10%;">修改logo</el-button>
      </div>

      <div class="midcard"></div>
      <div class="midcard"></div>
    </div>



  </div>
</template>
<script>
import mapView from './MapContainer.vue'

export default {
  components:{
     mapView
  },
  data() {
    return {
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      fit: 'cover',
      dialogFormVisible: false,
        form: {
          name: '',
          phone: '',
          address: '',
        },
      formLabelWidth: '120px',

      // zoom: 12,
      // // center: [121.59996, 31.197646],
      // visible: true,
      // needAddress:true,
    }
  },

  methods: {
    // getLocation(e) {
    //   console.log('getLocation: ', e)
    // }
    getAddress(msg){
      // console.log("获取到地址: ",msg.address)
      // this.form.address=msg.formattedAddress
      this.form.address=msg.address
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

</style>
