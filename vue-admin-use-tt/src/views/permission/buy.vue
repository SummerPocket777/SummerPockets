<template>
  <div>
    <div class="cards">
    <el-card class="box-card">
      <div class="title">基础版</div>
      <div class="money">￥68元<p class="money-sub">/每月</p></div>
      <div class="buy-button"><i class="el-icon-shopping-cart-2 ishop"></i>立即购买</div>
      <div class="text">
          <div class="text-item"><i class="el-icon-star-off"></i>基础版服务: 为您提供开店所需的基础服务</div>
          <div class="text-item"><i class="el-icon-star-off"></i>
            菜单管理：可以进行菜品的添加,设置热销标签
          </div>
          <div class="text-item"><i class="el-icon-star-off"></i>
            流水统计: 让您更清晰地了解您的店铺
          </div>
          <div class="text-item"><i class="el-icon-star-off"></i>
            预约服务: 能让顾客可以提前预订位置
          </div>
      </div>
    </el-card>
    <el-card class="box-card">

      <div class="title">专业版</div>
      <div class="money">￥128元<p class="money-sub">/每月</p></div>
      <div class="buy-button"><i class="el-icon-shopping-cart-2 ishop"></i>立即购买</div>
      <div class="text">
          <div class="text-item"><i class="el-icon-star-off"></i>专业版服务: 基础版所有服务和更专业的增值服务</div>
          <div class="text-item"><i class="el-icon-s-shop"></i>
            门店管理：为您的商业帝国新增门店
          </div>
          <div class="text-item"><i class="el-icon-s-data"></i>
            流水统计: 展示您门店的经营信息
          </div>
          <div class="text-item"><i class="el-icon-service"></i>
            专属客服: 7*24小时永远在线
          </div>
      </div>
    </el-card>
  </div>
</div>
</template>

<script>
export default {

}
</script>

<style scoped>
.text-item{
  margin: 18px;
  font-size: 18px;
}
  .cards{
    display: flex;
  }
  .ishop{
    margin-right: 10px;
  }
  .buy-button {
    color: rgb(206,44,44);
    text-align: center;
    line-height: 85px;
    font-size: 32px;
    width: 80%;
    margin: 0 auto;
    height: 85px;
    background-color: rgb(255, 177, 177);
    border-radius: 30px;
    border: 1px solid rgb(206, 44, 44);

  }
  .money-sub {
    font-size: 20px;
    color: rgb(119, 119, 119);
    margin-left: 5px;
    margin-top: 10px;
  }
  .money {
    color: red;
    width: 200px;
    height: 100px;
    margin: 0 auto;
    margin-top: 30px;
    font-size: 32px;
    display: flex;
  }
  .title {
    font-size: 32px;
  }
  .text {
    margin-top: 30px;
    font-size: 14px;
  }



  .box-card {
    width: 520px;
    height: 500px;
  }
</style>
