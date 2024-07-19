<template>
	<view>
		<view :class="titileClass">{{yuyuestatussss}}</view>
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
	import {
		mapStores,
		mapActions,
		mapState
	} from 'pinia'
	import {
		useCounterStore
	} from '@/store/yuyue.js'
	import {
		useUserStore
	} from '@/store/user.js'
	export default {
		data() {
			return {
				myyuyueinfos: {
					peopleNumber: '6',
					yuyueTimer: '2024-06-29 12:00',
					yuyueName: '王先生',
					yueyuePhone: '12345678901',
					creatYuyueTime: '2024-06-25 12:00'
				},
				index: '',
				titileClass:'yuyueinfo-title2',
				yuyuestatussss: '预约中'
			}
		},
		onLoad(options) {
			this.index = options.index;
			console.log("yuyueinfo ---",this.allYuyueInfo[this.index])
			console.log("myuserInfo ---",this.myUserInfo)
			this.myyuyueinfos .peopleNumber = this.allYuyueInfo[this.index].bookNumber;
			this.myyuyueinfos.yuyueTimer = this.allYuyueInfo[this.index].bookDate;
			this.myyuyueinfos.yuyueName = this.allYuyueInfo[this.index].bookName;
			this.myyuyueinfos.yueyuePhone = this.allYuyueInfo[this.index].bookPhone;
			this.myyuyueinfos.creatYuyueTime = this.formatTimestamp(this.allYuyueInfo[this.index].bookCreatedate) ;
			
		},
		onShow(){
			if(this.allYuyueInfo[this.index].isStatus == 1){
				this.yuyuestatussss= '预约中'
				this.titileClass='yuyueinfo-title'
			}else if(this.allYuyueInfo[this.index].isStatus == 2){
				this.yuyuestatussss= '已完成'
				this.titileClass = 'yuyueinfo-title2'
			}
		},
		computed:{
			...mapStores(useCounterStore,useUserStore),
			...mapState(useCounterStore,['allYuyueInfo']),
			...mapState(useUserStore,['myUserInfo'])
		},
		methods: {
			...mapActions(useCounterStore, ['cancelBooke']),
			cancelYuyue() {
				uni.showModal({
					title: '取消预约',
					content: '您确定要取消预约？',
					success: res => {
						if (res.confirm) {
							uni.showToast({
								title: '您已成功取消',
								icon: 'success',
							})
							this.cancelbooksss()
							setTimeout(() => {
								uni.navigateTo({
									url: '/pages/yuyue/yuyue'
								})
							}, 500)

						} else if (res.cancel) {
							uni.showToast({
								title: '取消本次操作',
								icon: 'success'
							})
						}
					}
				})
			},
			cancelbooksss(){
				console.log(this.allYuyueInfo[this.index].bookId)
				this.cancelBooke({
					bookId: this.allYuyueInfo[this.index].bookId
				}).then(res =>{
					console.log(res)
				}).catch(err =>{
					console.log(err)
				})
			},
			 formatTimestamp(timestamp) {
			    // 将时间戳转换为 Date 对象
			    const date = new Date(parseInt(timestamp));
			    
			    // 提取年、月、日、小时、分钟和秒
			    const year = date.getFullYear();
			    const month = ('0' + (date.getMonth() + 1)).slice(-2);
			    const day = ('0' + date.getDate()).slice(-2);
			    const hours = ('0' + date.getHours()).slice(-2);
			    const minutes = ('0' + date.getMinutes()).slice(-2);
			    const seconds = ('0' + date.getSeconds()).slice(-2);
			    
			    // 格式化为 YYYY-MM-DD HH:MM:SS
			    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
			},
		}
	}
</script>

<style>
	.infobox {
		display: flex;
		height: 125rpx;
		border-bottom: 2rpx dashed gray;
		align-items: center;

	}

	.yuyueinfo-title {
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
	.yuyueinfo-title2 {
		display: flex;
		background-color: crimson;
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

	.myyuyue-cancel {
		display: flex;
		position: absolute;
		bottom: 200rpx;
		width: 100%;
	}
</style>