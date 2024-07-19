<template>
	<view>
		<view v-for="(item, index) in myyuyueInfo" :key="index" class="myyuyue-box" @click="gotomyyuyueInfo(index)">
			<text class="y-where">{{ item.businessName }}</text>
			<text class="y-when">{{ item.bookDate }}</text>
			<text v-if="item.myyuyueHow == '预约中'" class="y-how">{{ item.myyuyueHow }}</text>
			<text v-else-if="item.myyuyueHow == '已完成'" class="y-how1">{{ item.myyuyueHow }}</text>
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
				myyuyueInfo: [{
						businessName: '胖哥俩',
						bookDate: '2024-07-01',
						myyuyueHow: '预约中'
					},
					{
						businessName: '太二',
						bookDate: '2024-03-12',
						myyuyueHow: '已完成'
					},
					{
						businessName: '太二',
						bookDate: '2024-03-12',
						myyuyueHow: '已完成'
					},
				]
			}
		},
		onShow() {
			this.getAllYuyueInfoById()
		},
		computed: {
			...mapStores(useCounterStore, useUserStore),
			...mapState(useCounterStore, ['allYuyueInfo']),
			...mapState(useUserStore, ['myUserInfo']),
		},
		methods: {
			...mapActions(useCounterStore, ['getAllYuyueInfoByIdd', 'setAllYuyueInfo']),
			gotomyyuyueInfo(index) {
				uni.navigateTo({
					url: `/pages/yuyue/yuyueInfo?index=${index}`
				})
			},
			getAllYuyueInfoById() {
				this.getAllYuyueInfoByIdd({
					"userID": this.myUserInfo.id
				}).then(res => {
					console.log("11222222222-----------",res.data)
					this.setAllYuyueInfo(res.data)
					console.log("111111111-----------",this.allYuyueInfo)
					for (let i = 0; i < this.allYuyueInfo.length; i++) {
						// 初始化 myyuyueInfo[i] 为对象
						if (!this.myyuyueInfo[i]) {
							this.myyuyueInfo[i] = {};
						}
						this.myyuyueInfo[i].businessName = this.allYuyueInfo[i].businessName
						this.myyuyueInfo[i].bookDate = this.allYuyueInfo[i].bookDate
						if (this.allYuyueInfo[i].isStatus == 1) {
							this.myyuyueInfo[i].myyuyueHow = '预约中'
						} else if (this.allYuyueInfo[i].isStatus == 2) {
							this.myyuyueInfo[i].myyuyueHow = '已完成'
						}
					}
					console.log(this.myyuyueInfo)
				}).catch(err => {
					console.log(err)
				})
			}
		}
	}
</script>

<style>
	.myyuyue-box {
		display: flex;
		background: linear-gradient(186deg, #84F9B9 0%, #8fd3f4 100%);
		margin-top: 20rpx;
		margin-left: 25rpx;
		width: 700rpx;
		height: 200rpx;
		align-items: center;
		justify-content: space-between;
		border-radius: 20rpx;
	}

	.y-where {
		font-size: 70rpx;
		margin-left: 20rpx;
	}

	.y-when {

		margin-left: 20rpx;
	}

	.y-how {
		font-size: 40rpx;
		margin-left: auto;
		margin-right: 10rpx;
		color: chartreuse;
		font-weight: bold;
	}

	.y-how1 {
		font-size: 40rpx;
		margin-left: auto;
		margin-right: 10rpx;
		color: beige;
		font-weight: bold;
	}
</style>