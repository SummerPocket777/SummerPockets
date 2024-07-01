<template>
  <view class="container">
    <view class="coupon" v-for="coupon in coupons" :key="coupon.id" @click="viewCouponDetail(coupon.id)">
      <image class="coupon-bg" src="../../static/icon/优惠券.png"></image>
      <view class="coupon-content">
        <view class="coupon-header">
          <text class="coupon-title">{{ coupon.title }}</text>
          <text class="coupon-discount">{{ coupon.discount }}</text>
        </view>
        <view class="coupon-body">
          <text class="coupon-description">{{ coupon.description }}</text>
          <text class="coupon-validity">有效期至: {{ coupon.validity }}</text>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      coupons: [
        { id: 1, title: '满100减20', discount: '¥20', description: '在本店消费满100元即可使用', validity: '2024-12-31', imageUrl: 'https://example.com/coupon1.jpg', amount: 20, points: 200 },
        { id: 2, title: '满200减50', discount: '¥50', description: '在本店消费满200元即可使用', validity: '2024-12-31', imageUrl: 'https://example.com/coupon2.jpg', amount: 50, points: 500 },
        // 更多优惠券数据
      ]
    }
  },
  methods: {
    viewCouponDetail(id) {
      uni.navigateTo({
        url: `/pages/user/mycoupons?id=${id}`
      });
    }
  }
}
</script>

<style lang="scss">
.container {
  padding: 20rpx;
  background-color: #f5f5f5;
}

.coupon {
  position: relative;
  background-color: #fff;
  border-radius: 20rpx;
  margin-bottom: 30rpx;
  padding: 20rpx;
  box-shadow: 0 4rpx 15rpx rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  animation: fadeIn 0.5s ease-in-out;
}

.coupon:hover {
  transform: scale(1.02);
  box-shadow: 0 8rpx 20rpx rgba(0, 0, 0, 0.2);
}

.coupon-bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  border-radius: 20rpx;
  opacity: 0.1;
}

.coupon-content {
  z-index: 1;
}

.coupon-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2rpx solid #f0f0f0;
  padding-bottom: 20rpx;
  margin-bottom: 20rpx;
}

.coupon-title {
  font-size: 36rpx;
  font-weight: bold;
  color: #333;
}

.coupon-discount {
  font-size: 32rpx;
  color: #ff5722;
}

.coupon-body {
  font-size: 28rpx;
  color: #666;
}

.coupon-validity {
  font-size: 24rpx;
  color: #999;
  margin-top: 10rpx;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20rpx);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
