<template>
	<view>
		点餐页面
		<view class="a">

			<dishbox class="b"></dishbox>
			
			
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
	.a{
		display: flex;
		 flex-wrap: wrap;
		 height: 1400rpx;
		
		width: 100%;
		.b{
			margin-top: auto;
			width: 100%;
			height: 200rpx;
		}
	}
</style>
