<template>
  <view class="container">
    <view class="tabs">
      <view :class="['tab', currentTab === 0 ? 'active' : '']" @click="changeTab(0)">待使用</view>
      <view :class="['tab', currentTab === 1 ? 'active' : '']" @click="changeTab(1)">已使用</view>
      <view :class="['tab', currentTab === 2 ? 'active' : '']" @click="changeTab(2)">已过期</view>
    </view>
    
    <view class="coupon-list">
      <view v-if="currentTab === 0">
        <view class="coupon" v-for="coupon in availableCoupons" :key="coupon.id" @click="viewCouponDetail(coupon.id)">
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
          <view class="coupon-tag" v-if="coupon.tag">{{ coupon.tag }}</view>
        </view>
      </view>
      
      <view v-else-if="currentTab === 1">
        <view class="coupon used" v-for="coupon in usedCoupons" :key="coupon.id">
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
			<view><image  class="nav" src="../../static/icon/已使用.png"></image></view>
          </view>
          
        </view>
		
      </view>

      <view v-else-if="currentTab === 2">
        <view class="coupon expired" v-for="coupon in expiredCoupons" :key="coupon.id">
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
			<view><image class="nav2" src="../../static/icon/已过期.png"></image></view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      currentTab: 0,
      coupons: [
        { id: 1, title: '满100减20', description: '在本店消费满100元即可使用', validity: '2024-12-31', status: 'available', tag: '限时优惠' },
        { id: 2, title: '满200减50', description: '在本店消费满200元即可使用', validity: '2024-12-31', status: 'used', tag: '热门' },
        { id: 3, title: '满300减100',description: '在本店消费满300元即可使用', validity: '2023-12-31', status: 'expired', tag: '限时优惠' },
        // 更多优惠券数据
      ]
    }
  },
  computed: {
    availableCoupons() {
      return this.coupons.filter(coupon => coupon.status === 'available');
    },
    usedCoupons() {
      return this.coupons.filter(coupon => coupon.status === 'used');
    },
    expiredCoupons() {
      return this.coupons.filter(coupon => coupon.status === 'expired');
    }
  },
  methods: {
    changeTab(index) {
      this.currentTab = index;
    },
    viewCouponDetail(id) {
      uni.navigateTo({
        url: `/pages/user/mycoupons?id=${id}`
      });
    }
  }
}
</script>

<style lang="scss">
.nav{
	width: 200rpx;
	height: 200rpx;
	padding: 0rpx;
	margin-top: -200rpx;
	margin-left: 460rpx;
	color: #ddd;
}
.nav2{
	width: 200rpx;
	height: 200rpx;
	padding: 0rpx;
	margin-top: -200rpx;
	margin-left: 460rpx;
	color: #ddd;
}

.container {
  padding: 20rpx;
  background-color: #f5f5f5;
}

.tabs {
  display: flex;
  justify-content: space-around;
  background-color: #fff;
  border-bottom: 1rpx solid #ddd;
}

.tab {
  padding: 20rpx 0;
  font-size: 32rpx;
  color: #666;
}

.tab.active {
  color: #ff5722;
  border-bottom: 2rpx solid #ff5722;
}

.coupon-list {
  padding: 20rpx;
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

.coupon.used {
  background-color: #fff3e0;
  color: #ff9800;
}

.coupon.expired {
  background-color: #f5f5f5;
  color: #9e9e9e;
}

.coupon-bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  border-radius: 20rpx;
  opacity: 0.05;
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
  color: inherit;
}

.coupon-discount {
  font-size: 32rpx;
  color: inherit;
}

.coupon-body {
  font-size: 28rpx;
  color: inherit;
}

.coupon-validity {
  font-size: 24rpx;
  color: inherit;
  margin-top: 10rpx;
}

.coupon-tag {
  position: absolute;
  top: 20rpx;
  right: 20rpx;
  background-color: #ff5722;
  color: #fff;
  padding: 5rpx 10rpx;
  border-radius: 10rpx;
  font-size: 24rpx;
  z-index: 2;
}

.coupon-stamp {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 36rpx;
  font-weight: bold;
  color: #fff;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 10rpx 20rpx;
  border-radius: 10rpx;
  z-index: 2;
}

.used-stamp {
  background-color: #ff9800;
}

.expired-stamp {
  background-color: #9e9e9e;
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
