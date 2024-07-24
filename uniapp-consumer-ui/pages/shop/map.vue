<template>
	<view>
		<view class="container">
			<view class="page-map">
				<map style="width: 750rpx; height: 750rpx;" :latitude="latitude" 
			:longitude="longitude" @tap="openLocation"></map>
			</view>
			<view class="map-navigation">
				<text @tap="mapNavigation">地图导航</text>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				latitude:'',
				longitude:'',
			}
		},
		methods: {
			getLocation(){
				uni.getLocation({
					type:'gcj02',
					geocode:true,
					success:res=>{
						console.log("res: ",res)
						this.latitude=res.latitude
						this.longitude=res.longitude
					}
				})
			},
			openLocation(){
				uni.openLocation({
					latitude:this.latitude,
					longitude:this.longitude,
					success() {
						console.log("进入地图")
					}
				})
			},
			mapNavigation(){
				let plugin = requirePlugin('routePlan');
				let key = 'G4VBZ-WNQCJ-4XPFY-X6B35-BR32O-S7BF7';  //使用在腾讯位置服务申请的key
				let referer = 'Sass';   //调用插件的app的名称
				let endPoint = JSON.stringify({  //终点
				  'name': '厦门北站',
				  'latitude': 24.504737,
				  'longitude': 118.177197
				});
				wx.navigateTo({
				  url: 'plugin://routePlan/index?key=' + key + '&referer=' + referer + '&endPoint=' + endPoint
				});
			},
		},
		onLoad() {
			this.getLocation()
		}
	}
</script>

<style lang="scss">
.map-navigation{
	display: flex;
	justify-content: center;
	margin-top: 50rpx;
	// border: 1 solid black;
}
</style>
