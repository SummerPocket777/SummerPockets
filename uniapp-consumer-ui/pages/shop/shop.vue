<template>
	<view>
		<view class="shop-top">
			<view class="top-name">
				<view class="top-img">
					<image width="150rpx" height="150rpx" shape="square" radius="5px"></image>
				</view>
				<view class="top-text">
					<view class="text-first">
						<text text="桌名+桌号" bold size="17px"></text>
					</view>
					<view class="text-second">
						<text text="用餐人数: "></text>
					</view>
				</view>
				
			</view>
			<view class="top-search">
				<up-search placeholder="请输入菜品" input-align="center" action-text=""></up-search>
				<u-image width="50rpx" height="50rpx" src="../../static/icon/定位.png" @tap="intoMap"></u-image>
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
		},
		methods:{
			intoMap(){
				uni.navigateTo({
					url:'./map'
				})
			}
		}
	}
</script>

<style lang="scss">
@import './shop.scss'
</style>
