<template>
  <view class="container" v-if="coupon">
    <image :src="coupon.imageUrl" class="coupon-image" mode="widthFix"></image>
    <view class="amount">{{ coupon.amount }}元</view>
    <view class="content">
      <view class="row" v-for="(item, index) in details" :key="index">
        <view class="label">{{ item.label }}</view>
        <view class="value">{{ item.value }}</view>
      </view>
    </view>
    <view class="footer">
      <button class="redeem-btn" :disabled="!hasEnoughPoints" @click="goToUse">{{ hasEnoughPoints ? '去使用' : '积分不足' }}</button>
    </view>
  </view>	
</template>

<script>
export default {
  data() {
    return {
      coupon: null,
      details: [],
      hasEnoughPoints: false
    }
  },
  async onLoad() {
    // Mock data for demonstration
    this.coupon = {
      imageUrl: 'https://example.com/coupon.jpg',
      amount: 50,
      points: 500,
      period: '即日起 至 2024-12-31',
      threshold: '满200元可用',
      applicableProducts: '全场通用',
      applicableStores: '所有门店',
      usageScenario: '仅限线下使用',
      description: '此优惠券可在所有商品上使用',
      instructions: '使用时请出示此优惠券',
      remark: '此优惠券不可与其他优惠叠加使用'
    }
    this.hasEnoughPoints = this.checkPoints(this.coupon.points)
    this.details = [
      { label: '有效期限', value: this.coupon.period },
      { label: '卡券金额', value: this.coupon.amount + '元' },
      { label: '卡券门槛', value: this.coupon.threshold },
      { label: '可用商品', value: this.coupon.applicableProducts },
      { label: '可用门店', value: this.coupon.applicableStores },
      { label: '可用场景', value: this.coupon.usageScenario },
      { label: '卡券说明', value: this.coupon.description },
      { label: '卡券介绍', value: this.coupon.remark }
    ]
  },
  methods: {
    checkPoints(requiredPoints) {
      // 假设用户积分从某个API获取，这里简单示例为1000
      const userPoints = 1000
      return userPoints >= requiredPoints
    },
    goToUse() {
      if (this.hasEnoughPoints) {
        uni.navigateTo({
          url: '/pages/order/order'
        })
      }
    }
  }
}
</script>

<style scoped lang="scss">
.container {
  padding: 20rpx;
  background-color: #FFFFFF;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.coupon-image {
  width: 90%;
  height: auto;
  margin-bottom: 20rpx;
  border-radius: 10rpx;
  box-shadow: 0 4rpx 8rpx rgba(0, 0, 0, 0.1);
}

.amount {
  font-size: 48rpx;
  font-weight: bold;
  color: #e51c23;
  text-align: center;
  margin-bottom: 20rpx;
}

.content {
  width: 90%;
  margin-bottom: 30rpx;
  
  .row {
    display: flex;
    justify-content: space-between;
    padding: 10rpx 0;
    border-bottom: 1rpx solid #eee;
    
    .label {
      font-size: 34rpx;
      color: #333;
      font-weight: bold;
    }
    
    .value {
      font-size: 34rpx;
      color: #666;
      text-align: right;
      flex-shrink: 0;
      max-width: 500rpx;
    }
  }
}

.footer {
  width: 90%;
  display: flex;
  justify-content: center;
  margin-bottom: 20rpx;
  
  .redeem-btn {
    width: 100%;
    height: 90rpx;
    font-size: 34rpx;
    color: #fff;
    background-color: #e51c23;
    border: none;
    border-radius: 10rpx;
    display: flex;
    justify-content: center;
    align-items: center;
    
    &:disabled {
      background-color: #e9d5bb;
    }
  }
}
</style>
