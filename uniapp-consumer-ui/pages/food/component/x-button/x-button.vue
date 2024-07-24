<template>
	<view class="btn-outer" :style="{backgroundColor:bgColor,color:textColor,fontSize:size,width:btnWidth,height:btnHeight}" @tap="tapBtn">
		<slot></slot>
	</view>
</template>

<script>
	export default {
		props:{
			// 100% or 500rpx
			btnWidth:{
				type:String,
				default:'100%'
			},
			btnHeight:{
				type:String,
				default:'84rpx'
			},
			bgColor:{
				type:String,
				default:'#dba871'
			},
			textColor:{
				type:String,
				default:'#ffffff'
			},
			size:{
				type:String,
				default:'32rpx'
			}
		},
		data() {
			return {
				
			};
		},
		methods:{
			tapBtn(){
				this.$emit('click',{})
			}
		}
	}
</script>

<style>
.btn-outer{
	display: flex;
	align-items: center;
	justify-content: center;
	border-radius: 6rpx;
}
</style>
