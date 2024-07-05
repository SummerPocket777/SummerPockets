<template>
	<view class="content">
		<view class="top">
			<image class="user-img" :src="userImage"></image>
			<text class="user-name">{{userName}}</text>
		</view>
		<view class="container">
			<view class="text-f" @click="gotocoupons">
				<text class="description">绑定手机号获取更多优惠推荐</text>
				<view class="right-icon">
					<up-icon name="arrow-right"></up-icon>
				</view>
			</view>
			<view class="text-s">
				<text class="description">关于我们</text>
				<view class="right-icon">
					<up-icon name="arrow-right"></up-icon>
				</view>
			</view>
			<view class="text-t" @click="gotomyyuyue">
				<text class="description">我的预约</text>
				<view class="right-icon">
					<up-icon name="arrow-right"></up-icon>
				</view>
			</view>
		</view>
	</view>
	
	
</template>

<script>
	export default {
		data() {
			return {
				userName:"用户名称",
				userImage:''
			}
		},
		onLoad() {
			this.userName = getApp().globalData.userName
			this.userImage = getApp().globalData.userImage
			console.log(this.userImage)
		},
		methods: {
			gotomyyuyue(){
				uni.navigateTo({
					url:'../yuyue/yuyue'
				})
			},
			gotocoupons(){
				uni.navigateTo({
					url:'/pages/user/coupons',
					
				})
				console.log("点击量")
			}
			
		},
		   
	}
</script>

<style lang="scss">
.top{
	background: linear-gradient(186deg, #84F9B9 0%,#8fd3f4 100%);
	width: 750rpx;
	height: 400rpx;
	display: flex;
	border-top: #ffffff solid 1px;
	flex-direction: column;
	text-align: center;
	align-items: center;
	.user-img{
		width: 200rpx;
		height: 200rpx;
		margin-top: 50rpx;
		border-radius: $uni-border-radius-circle;
		background: linear-gradient(0deg, #f3e7e9 0%,#e3eeff 99%,#e3eeff 100%);
	}
	.user-name{
		margin-top: 20rpx;
		color: #00aaff;
	}
}
.container{
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	.text-f,.text-s,.text-t{
			width: 100%;
			height: 120rpx;
			background: repeating-linear-gradient(272deg, #5ee7df 0%,#DEB9F4 100%);
			margin-top: 20rpx;
			border-radius: 10px;
			display: flex;
			align-items: center;
			.description{
				// text-align: center;
				// line-height: 120rpx;
				margin-left: 50rpx;
				width: 600rpx;
			}
			.right-icon{
			    // line-height: 120rpx;	
				margin-left: 20rpx;
			}
	}
	
	
}

</style>
