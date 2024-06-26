<template>
	<view>
		<view >
			订单页面
			<view @click="toShop">跳到点餐页面</view>
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
			toShop(){
				uni.switchTab({
					url:"/pages/shop/shop"
				})
			}
		}
	}
</script>

<style lang="scss">

</style>
