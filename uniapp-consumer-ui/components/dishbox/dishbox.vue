<template>
	<view>
		<view class="box">
			<image src="../../static/logo.png" class="img"></image>
			<view class="dish-box">
				<view class="dish-name">{{dishName}}</view>
				<view class="num">月售{{num}}+</view>
				<view class="bottom-box">
					<view class="price">￥{{price}}</view>
					<view class="choice-num"> <image src="../../static/icon/加.png" class="add-img"  @click="onCustomPlay($event)"> </image></view>
				</view>
				
			</view>
		
		</view>
	</view>
</template>


<script>

	export default {
		name:"dishbox",
		data() {
			return {
				
				busPos:{
				    x:uni.getSystemInfoSync().windowWidth - 190,
				    y:uni.getSystemInfoSync().windowHeight + 100
				}
				
			};
		},
		methods:{

		},
		props: {
			// 菜品名称
			dishName:{
				type:String,
				default:"这是一道菜的名字"
			},
			// 销量数据
			num:{
				type:Int16Array,
				default:1000
			},
			// 商品价格
			price:{
				type:Float32Array,
				default:77.7
			}
		},
		components:{
			
		}
	}
</script>

<style lang="scss">

.box{
	display: flex;
	width: 650rpx;
	image{
		width: 175rpx;
		height: 175rpx;
		
		}
		
	.dish-name{
		font-size: 45rpx;
		margin-bottom: 15rpx;
	}
	.num{
		font-size: 25rpx;
		color: gray;
		margin-bottom: 15rpx;
	}	
	
	.dish-box{
		width: 400rpx;
		padding-left: 30rpx;

		.bottom-box{
			display: flex;

			width: 350rpx;
			.price{
				width: 100rpx;
				font-size: 40rpx;
				float: left;
			}
			.choice-num{
				margin-left: auto;
				width: 100rpx;
				image{
					width: 60rpx;
					height: 60rpx;
					
					}
				}
			}
	}

}


</style>