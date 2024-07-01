<template>
	<view>
		<view class="shop-top">
			<view class="top-name">
				<view class="top-img">
					<u-image width="150rpx" height="150rpx" shape="square" radius="5px"></u-image>
				</view>
				<view class="top-text">
					<view class="text-first">
						<u-text text="桌名+桌号" bold size="17px"></u-text>
					</view>
					<view class="text-second">
						<u-text text="用餐人数: "></u-text>
					</view>
				</view>
				
			</view>
			<view class="top-search">
				<up-search placeholder="请输入菜品" input-align="center" action-text=""></up-search>
			</view>
		</view>
		<view class="container">
			<scrolle-view scroll-y="true">
				<view class="container-left">
					<view class="menu-left">
						<view class="all-menu">
							<text>全 部</text>
						</view>
						
					</view>
				</view>
			</scrolle-view>
			<scroll-view scroll-y="true" >
				<view class="container-right">
					<view class="info-right">
						<view class="image-info">
							<u-image width="150rpx" height="150rpx" shape="square" radius="5px"></u-image>
						</view>
						<view class="detail-info">
							<view class="info-name">
							    <u-text text="榴莲面包" bold></u-text>
							</view>
							<view class="intro-info">
								<u-text text="暂无介绍" size="13px"></u-text>
								<view class="sell-info">
									<u-text text="销量: " size="13px" ></u-text>
									<u-text text="0" size="13px"></u-text>
								</view>
							</view>
							<view class="price-info">
								<u-text prefix-icon="" mode="price" text="29.80" color="red"></u-text>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
.shop-top{
	width: 100%;
	height: 300rpx;
	background: linear-gradient(186deg, #84F9B9 0%,#8fd3f4 100%);
	border-top: #ffffff solid 1rpx;
	display: flex;
	flex-direction: column;
	// justify-content: center;
	// align-items: center;
	.top-name{
		width: 750rpx;
		height: 200rpx;
		display: flex;
		// justify-content: center;
		align-items: center;
		.top-img{
			margin:0 50rpx;
		}
		.top-text{
			margin-top: 40rpx;
			.text-first{
				margin-bottom: 10rpx;
			}
			.text-second{
				
			}
		}
	}
	.top-search{
		margin-left: 50rpx;
	}
	
}
.container{
	width: 100%;
	height: 900rpx;
	display: flex;
	.container-left{
		background-color: #e6e6e6;
		width: 200rpx;
		height: 100%;
		display: flex;
		// flex-direction: column;
		justify-content: center;
		.all-menu{
			width: 100rpx;
			height: 200rpx;
			text-align: center;
			margin-top: 50rpx;
		}
	}
	.container-right{
		width: 550rpx;
		height: 100%;
		display: flex;
		.info-right{
			width: 100%;
			height: 25%;
			display: flex;
			// flex-direction: column;
			// justify-content: center;
			.image-info{
				width: 200rpx;
				height: 100%;
				display: flex;
				justify-content: center;
				align-items: center;
			}
			.detail-info{
				width: 300rpx;
				height: 100%;
				display: flex;
				flex-direction: column;
				justify-content: center;
				// align-items: center;
				.intro-info{
					.sell-info{
						display: flex;
						align-items: center;
					}
				}
				
			}
			
		}
	}
}
</style>
