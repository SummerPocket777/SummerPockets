<template>
	<view>
		<view class="yuyueinfo-title">预约中</view>
		<view class="infobox">
			<text style="width: 170rpx;margin-right: 20rpx;margin-left: 20rpx;">用餐人数:</text>
			<text>{{myyuyueinfos.peopleNumber}} 人</text>
		</view>
		<view class="infobox">
			<text style="width: 170rpx;margin-right: 20rpx;margin-left: 20rpx;">预约时间:</text>
			<text>{{myyuyueinfos.yuyueTimer}}</text>
		</view>
		<view class="infobox">
			<text style="width: 170rpx;margin-right: 20rpx;margin-left: 20rpx;letter-spacing: 16rpx;">姓名:</text>
			<text>{{myyuyueinfos.yuyueName}}</text>
		</view>
		<view class="infobox">
			<text style="width: 170rpx;margin-right: 20rpx;margin-left: 20rpx;letter-spacing: 16rpx;">电话:</text>
			<text>{{myyuyueinfos.yueyuePhone}}</text>
		</view>
		<view>
			<text style="margin-left: 20rpx;">创建时间:</text>
			<text>{{myyuyueinfos.creatYuyueTime}}</text>
		</view>
		<view class="myyuyue-cancel">
			<button type="warn" @click="cancelYuyue">取消预约</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				myyuyueinfos:{
					peopleNumber: '6',
					yuyueTimer:'2024-06-29 12:00',
					yuyueName:'王先生',
					yueyuePhone:'12345678901',
					creatYuyueTime:'2024-06-25 12:00'
				}
			}
		},
		methods: {
			cancelYuyue(){
				uni.showModal({
					title:'取消预约',
					content:'您确定要取消预约？',
					success:function(res){
						if(res.confirm){
							uni.showToast({
								title:'您已成功取消',
								icon:'success',
							})
							setTimeout(()=>{
								
								uni.navigateTo({
									url:'/pages/yuyue/yuyue'
								})
							},500)
							
						}else if(res.cancel){
							uni.showToast({
								title:'取消本次操作',
								icon: 'success'
							})
						}
					}
				})
			}
		}
	}
</script>

<style>
	.infobox{
		display: flex;
		height: 125rpx;
		border-bottom: 2rpx dashed gray;
		align-items: center;
	
	}
	.yuyueinfo-title{
		display: flex;
		background-color: lawngreen;
		height: 100rpx;
		width: 350rpx;
		font-size: 40rpx;
		align-items: center;
		justify-content: center;
		margin-left: 175rpx;
		margin-top: 20rpx;
		margin-bottom: 30rpx;
		border-radius: 20rpx;
	}
	.myyuyue-cancel{
		display: flex;
		position: absolute;
		bottom: 200rpx;
		width: 100%;
	}
</style>