<template>
	<view>
		<view class="myyuyue-box" @click="gotomyyuyueInfo">
			<text class="y-where">{{myyuyueInfo.myyuyueWhere}}</text>
			<text class="y-when">{{myyuyueInfo.myyuyueWhen}}</text>
			<text class="y-how">{{myyuyueInfo.myyuyueHow}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				myyuyueInfo:{
					myyuyueWhere:'胖哥俩',
					myyuyueWhen:'2024-07-01',
					myyuyueHow:'预约中'
				}
			}
		},
		methods: {
			gotomyyuyueInfo(){
				uni.navigateTo({
					url:'/pages/yuyue/yuyueInfo'
				})
				
			}
		}
	}
</script>

<style >
.myyuyue-box{
	display: flex;
	background: linear-gradient(186deg, #84F9B9 0%,#8fd3f4 100%);
	margin-top: 20rpx;
	margin-left: 25rpx;
	width: 700rpx;
	height: 200rpx;
	align-items: center;
	justify-content: space-between;
}
.y-where{
	font-size: 70rpx;
	margin-left: 20rpx;
}
.y-when{
	
	margin-left: 20rpx;
}
.y-how{
	font-size: 40rpx;
	margin-left:auto;
	margin-right: 10rpx;
}
</style>
