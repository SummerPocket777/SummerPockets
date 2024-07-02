<template>
	<view class="order" >
		<u-swiper keyName="image" :list="swiperList" class="swiper"
			indicator indicatorMode="line" circular ></u-swiper>
		<view class="list" >
			<view class="item" v-for="(item,index) in List" :key="item">
				<view class="title">
					<view class="name" >
						<image src="@/static/shop.png"></image>
						{{item.name}}
					</view>
					<view class="status" :style="{'color': item.status == 10 ? '#19be6b':'black'}">
						{{status.get(item.status)}} <!-- 状态转换文字 -->
					</view>
				</view>
				<view class="content">
					<view class="box">
						<view> 
							<view v-for="data in item.product">{{data}}</view>  <!-- 循环订单菜品 -->
						</view>
						<view class="open">
							查看更多···
						</view>
					</view>
					<view class="time">
						下单时间
						{{item.time}}
					</view>
					<view class="price">
						合计&nbsp;&nbsp;¥{{item.price}}
					</view>
				</view>
				
				<view class="action" v-if="item.status == 10">
					<view>
						<u-button text="催单" :customStyle="btn2"></u-button>
					</view>
					<view>
						<u-button text="结算" :customStyle="btn2"></u-button>
					</view>
					<view>
						<u-button text="加餐" :customStyle="btn"></u-button>
					</view>
				</view>
			</view>
		</view>
		<!-- 没有订单信息时显示 -->
		<u-empty v-if="List.length < 1" mode="order">
		</u-empty>


	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 轮播图图片地址
				// swiperList: [
				// 	require('@/static/images/1.jpg'),
				// 	require('@/static/images/2.jpg'),
				// 	require('@/static/images/3.jpg'),
				// ],
				// 状态-信息
				status: new Map([
					[10, '进行中'],
					[20, '已完成'],
				]),
                // 按钮的样式
				btn: {
					width: "160rpx",
					height: "68rpx",
					color: '#19be6b',
					borderRadius: '14rpx',
					fontSize: '34rpx',
					border:'2rpx solid #19be6b'
				},
				btn2: {
					width: "160rpx",
					height: "68rpx",
					fontSize: '28rpx',
					borderRadius: '14rpx',
					fontSize: '34rpx'
				},
				// 订单列表信息
				List: [
					{
					   name:'店铺名称1',
					   status: 10,   // 进行中
					   product:["红烧茄子","清蒸鲈鱼","黄焖鸡"],
					   time:'2024-09-01 12:00:00',
					   price:'288.00'
				    },
					{
					   name:'店铺名称2',
					   status: 20,   // 已完成
					   product:["红烧茄子2","清蒸鲈鱼2","黄焖鸡2"],
					   time:'2024-09-01 10:00:00',
					   price:'266.00'
					},
					{
					   name:'店铺名称3',
					   status: 20,   // 已完成
					   product:["红烧茄子2","清蒸鲈鱼2","黄焖鸡2"],
					   time:'2024-09-01 10:30:00',
					   price:'266.00'
					},
					{
					   name:'店铺名称4',
					   status: 20,   // 已完成
					   product:["红烧茄子2","清蒸鲈鱼2","黄焖鸡2"],
					   time:'2024-09-01 11:00:00',
					   price:'266.00'
					},
					{
					   name:'店铺名称5',
					   status: 20,   // 已完成
					   product:["红烧茄子2","清蒸鲈鱼2","黄焖鸡2"],
					   time:'2024-09-01 12:30:00',
					   price:'266.00'
					},
				],
			};
		},
		onLoad() {
		},
		methods: {
		}
	}
</script>

<style lang="scss" scoped>
	.order {
		width: 100%;
		overflow: hidden;
		display: flex;
		justify-content: center;
		flex-wrap: wrap;

		.list {
			width: 100%;
			display: flex;
			flex-wrap: wrap;
			justify-content: center;
			margin-bottom: 200rpx;

			.item {
				width: 702rpx;
				background: #FFFFFF;
				border-radius: 23rpx;
				margin-top: 20rpx;
				box-shadow: 0 0 40rpx -10rpx gainsboro;

				.status {
					font-size: 30rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: green;
					margin-right: 22rpx;
				}

				.title {
					display: flex;
					justify-content: space-between;
					align-items: center;
					height: 80rpx;
					line-height: 80rpx;
					font-size: 30rpx;

					.name {
						width: 520rpx;
						color: #543E13;
						font-size: 32rpx;
						font-weight: 600;
						font-family: PingFangSC-Medium, PingFang SC;
						border-radius: 23rpx 23rpx 10rpx 0rpx;
						overflow: hidden;

						image {
							width: 36rpx;
							height: 34rpx;
							margin: 10rpx 10rpx 0 30rpx;
						}
					}
				}

				.content {
					display: flex;
					justify-content: center;
					flex-wrap: wrap;
					padding: 0 60rpx 30rpx 60rpx;
				    .box{
						padding: 20rpx;
						width: 100%;
						background-color: coral;
						display: flex;
						>view{
							width: 50%;
							font-size: 34rpx;
						}
						.open{
							display: flex;
							align-items: center;
							justify-content: flex-end;
							font-size: 26rpx;
						}
					}
					.time{
						width: 100%;
						margin: 20rpx 0 20rpx 0;
					}
					.price{
						width: 100%;
						text-align: right;
						font-size: 34rpx;
					}
				}

				.action {
					width: 100%;
					height: 90rpx;
					display: flex;
					justify-content: flex-end;

					>view {
						margin-right: 20rpx;
					}
				}
			}
		}
	}
	::v-deep .u-swiper__wrapper {
		width: 702rpx;
		height: 224rpx !important;
		margin-top: 22rpx;
		background-color: white !important;
	}
	
	::v-deep .u-swiper__wrapper__item__wrapper__image {
		height: 220rpx !important;
	}
</style>