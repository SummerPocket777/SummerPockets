<template>
  <view class="container">
    <view class="coupon-info">
      <image class="coupon-img" :src="coupon.imageUrl" mode="widthFix"></image>
      <view class="coupon-details">
        <text class="coupon-title">{{ coupon.title }}</text>
        <text class="coupon-description">{{ coupon.description }}</text>
        <text class="coupon-validity">有效期至: {{ coupon.validity }}</text>
      </view>
    </view>
    <view class="redeem-section">
      <input class="redeem-input" type="text" v-model="redeemCode" placeholder="请输入核销码" />
      <button class="redeem-btn" @click="redeemCoupon">核销</button>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      coupon: {
        id: 1,
        title: '满100减20',
        description: '在本店消费满100元即可使用',
        validity: '2024-12-31',
        imageUrl: '../../static/icon/优惠券.png'
      },
      redeemCode: ''
    };
  },
  methods: {
    redeemCoupon() {
      if (!this.redeemCode) {
        uni.showToast({
          title: '请输入核销码',
          icon: 'none'
        });
        return;
      }
      // 模拟核销请求
      uni.request({
        url: 'example',
        method: 'POST',
        data: {
          code: this.redeemCode,
          couponId: this.coupon.id
        },
        success: (res) => {
          if (res.data.success) {
            uni.showToast({
              title: '核销成功',
              icon: 'success'
            });
            // 清空核销码
            this.redeemCode = '';
          } else {
            uni.showToast({
              title: res.data.message || '核销失败',
              icon: 'none'
            });
          }
        },
        fail: () => {
          uni.showToast({
            title: '网络错误，请稍后重试',
            icon: 'none'
          });
        }
      });
    }
  }
}
</script>

<style scoped lang="scss">
.container {
  padding: 40rpx;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.coupon-info {
  width: 100%;
  background-color: #fff;
  padding: 30rpx;
  margin-bottom: 40rpx;
  display: flex;
  align-items: center;
  border-radius: 20rpx;
  box-shadow: 0 4rpx 15rpx rgba(0, 0, 0, 0.1);
}

.coupon-img {
  width: 180rpx;
  height: 180rpx;
  margin-right: 30rpx;
  border-radius: 10rpx;
}

.coupon-details {
  flex: 1;
}

.coupon-title {
  font-size: 40rpx;
  font-weight: bold;
  color: #333;
  margin-bottom: 15rpx;
}

.coupon-description,
.coupon-validity {
  font-size: 32rpx;
  color: #666;
}

.redeem-section {
  width: 100%;
  background-color: #fff;
  padding: 30rpx;
  border-radius: 20rpx;
  box-shadow: 0 4rpx 15rpx rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
}

.redeem-input {
  width: 90%;
  height: 80rpx;
  border: 1rpx solid #ddd;
  border-radius: 10rpx;
  padding: 0 20rpx;
  font-size: 32rpx;
  margin-bottom: 20rpx;
}

.redeem-btn {
  width: 90%;
  height: 80rpx;
  background-color: #ff5722;
  color: #fff;
  font-size: 32rpx;
  text-align: center;
  line-height: 80rpx;
  border-radius: 10rpx;
}
</style>
