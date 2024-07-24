<template>
	<view>
		<view class="mask-bg" @touchmove.stop.prevent :class="modalShow?'bg-show':'bg-hidden'">
			<!-- #ifdef H5 -->
			<view class="fake-area"></view>
			<!-- #endif -->
			<view class="main-area" :class="modalShow?'modal-show':'modal-hidden'">
				<view class="modal-area">
					<image src="/static/images/share.png" class="handle-btn" style="right: 86rpx;" @tap="shareGoods"></image>
					<image src="/static/images/close.png" class="handle-btn" style="right: 20rpx;" @tap="closeModal"></image>
					<view class="good-image-box">
						<image :src="dataObj.img?dataObj.img:'/static/images/default.png'" mode="widthFix"></image>
					</view>

					<scroll-view scroll-y="true">
						<view class="scroll-inner_box">
							<text class="good-name">{{dataObj.name || ''}}</text>

							<view class="tags-box" v-if="dataObj.tags">
								<text class="tag-item" v-for="(item,index) in dataObj.tags" :key="index">{{item}}</text>
							</view>
							<view style="font-size: 28rpx; color: #555;margin: 10rpx 0;">产品描述</view>
							<text class="description-text">{{dataObj.description || ''}}</text>
							<view class="status-item" v-for="(item,index) in dataObj.chooseOptions" :key="index">
								<view class="status-title">
									{{item.statuText}}
								</view>
								<view class="status-tags">
									<block v-for="(item1,index1) in item.statusItem" :key="item1.id + index1.toString()">
										<view class="tags-item2" @tap="chooseTag(index,index1)"  :style="{color:item1.isDefault?activeTextColor:normalTextColor,backgroundColor:item1.isDefault?activeBgColor:normalBgColor}">{{item1.name}}<text class="tags-pri" :style="{color:item1.isDefault?activeTextColor:activeBgColor}" v-if="item1.pri>0" :class="{'active-text':item1.isDefault}">￥{{item1.pri}}</text>
											 <text class="iconfont star-shrink icon-recommand" :style="{color:item1.isDefault?activeStarColor:normalStarColor}" :class="item1.isDefault?' ':''" v-if="item1.isPopular"></text>
										</view>
									</block>
								</view>
							</view>
						</view>
					</scroll-view>

					<view class="bottom-btn-box">
						<view class="status-box">
							<view class="left-status">
								<text class="pri-text" :style="{color:activeBgColor}">￥{{shownPrice}}</text>
								<text class="status-text">{{choosedText}}</text>
							</view>
							<view class="count-box">
								<text class="iconfont sign icon-decrease" :style="{color:activeBgColor}" @tap="decrease"></text>
								<text>{{count}}</text>
								<text class="iconfont sign icon-plus" :style="{color:activeBgColor}" @tap="increase"></text>
							</view>
						</view>
						<view class="btn-box">
							<x-button :bgColor="activeBgColor" :textColor="activeTextColor" btnWidth="600rpx" @click="addCart">加入购物袋</x-button>
						</view>
					</view>
				</view>
			</view>
			<view class="fake-area2"></view>
		</view>
	</view>
</template>

<script>
	import xButton from '@/components/x-button/x-button'
	export default {
		props: {
			modalShow: {
				type: Boolean,
				default: false
			},
			dataObj: {
				type: Object,
				default: () => {
					return {}
				}
			},
			// 底色
			activeBgColor:{
				type:String,
				default:'#dba871'
			},
			normalBgColor:{
				type:String,
				default:'#efefef'
			},
			// 普通文字色
			normalTextColor:{
				type:String,
				default:'#333333'
			},
			// 选中文字色
			activeTextColor:{
				type:String,
				default:'#ffffff'
			},
			// 普通星星色
			normalStarColor:{
				type:String,
				default:'#dba871'
			},
			// 选中星星色
			activeStarColor:{
				type:String,
				default:'#fdf292'
			}
		},
		components: {
			xButton
		},
		watch: {
			modalShow(newValue, oldValue) {
				this.count = this.dataObj.leastBuy
				this.shownPrice = this.dataObj.priNum
			}
		},
		data() {
			return {
				count: 1,
				shownPrice: 0,
				choosedText:''
			};
		},
		updated() {
			this.updateChoosedText()
		},
		methods: {
			// 关闭modal
			closeModal() {
				this.$emit('close', {})
			},
			shareGoods(){
				this.$emit('share', {})
			},
			decrease() {
				if (this.count == this.dataObj.leastBuy) return
				this.count--
				this.calcOverprice()
			},
			increase() {
				if (this.count == this.dataObj.mostBuy || this.count == this.dataObj.storage) return
				this.count++
				this.calcOverprice()
			},
			chooseTag(rowIndex, itemIndex) {
				this.dataObj.chooseOptions[rowIndex].statusItem.map(item => {
					item.isDefault = false
				})
				this.$set(this.dataObj.chooseOptions[rowIndex].statusItem[itemIndex],'isDefault',true)
				
				this.calcOverprice()
			},
			updateChoosedText(){
				let tempArr = []
				this.dataObj.chooseOptions.map(item=>{
					item.statusItem.map(item1=>{
						if(item1.isDefault){
							if(item1.isPopular){
								tempArr.push(item1.name + '(推荐)')
							}else{
								tempArr.push(item1.name)
							}
						}
					})
				})
				this.choosedText = tempArr.join(' , ')
			},
			// 计算总价（商品+加料）
			calcOverprice() {
				let pri = 0
				this.dataObj.chooseOptions.forEach(item => {
					item.statusItem.forEach(item1 => {
						if (item1.isDefault) {
							pri += item1.pri
						}
					})
				})
				this.shownPrice = this.count * (this.dataObj.priNum + pri)
			},
			// 加入购物车
			addCart() {
				this.$emit('addCart', {
					materialId:this.dataObj.materialId,
					count:this.count
					// this.shownPrice
				})
				this.closeModal()
			}
		}
	}
</script>

<style>
	.mask-bg {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		display: flex;
		flex-direction: column;
		z-index: 999;
		transition: all .2s linear;
	}

	.modal-show {
		visibility: visible;
		transform: scale(1);
	}

	.bg-show {
		background-color: rgba(0, 0, 0, .5);
		visibility: visible;
	}

	.bg-hidden {
		background-color: rgba(255, 255, 255, 0);
		visibility: hidden;
	}

	.modal-hidden {
		visibility: hidden;
		transform: scale(0);
	}

	.fake-area {
		width: 100%;
		height: 128rpx;
	}

	.fake-area2 {
		height: 98rpx;
		width: 100%;
	}

	.main-area {
		width: 100%;
		flex-grow: 1;
		display: flex;
		align-items: center;
		justify-content: center;
		transition: all .2s linear;
	}

	.modal-area {
		width: 88%;
		background-color: #FFFFFF;
		border-radius: 8rpx;
		overflow: hidden;
		position: relative;
	}

	.handle-btn {
		width: 42rpx;
		height: 42rpx;
		display: block;
		position: absolute;
		top: 20rpx;
		z-index: 99;
	}

	.bottom-btn-box {
		width: 100%;
	}

	.btn-box {
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: center;
		padding: 20rpx 0;
		box-sizing: border-box;
	}

	.status-box {
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 20rpx 30rpx 0 30rpx;
		box-sizing: border-box;
	}

	.left-status {
		display: flex;
		flex-direction: column;
	}

	.pri-text {
		font-size: 34rpx;
	}

	.status-text {
		color: #9a9a9a;
		font-size: 30rpx;
		overflow: hidden;
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-box-orient: vertical;
		-webkit-line-clamp: 2;
	}

	.count-box {
		display: flex;
		align-items: center;
	}

	.count-box image {
		width: 56rpx;
		height: 56rpx;
		display: block;
	}

	.count-box text {
		display: inline-flex;
		width: 60rpx;
		height: 48rpx;
		align-items: center;
		justify-content: center;
		font-size: 34rpx;
	}

	.good-image-box image {
		width: 100%;
		max-height: 440rpx;
		display: block;
	}

	.scroll-inner_box {
		padding: 10rpx 30rpx;
		box-sizing: border-box;
		/* #ifndef H5 */
		max-height: 320rpx;
		/* #endif */
		/* #ifdef H5 */
		max-height: 400rpx;
		/* #endif */
	}

	.good-name {
		font-size: 36rpx;
	}

	.tag-item {
		display: inline-flex;
		align-items: center;
		justify-content: center;
		font-size: 24rpx;
		line-height: 24rpx;
		color: #909090;
		background-color: #dadada;
		border-radius: 4rpx;
		padding: 10rpx 20rpx;
		transform: scale(0.8);
		transform-origin: left;
		margin-right: -20rpx;
	}

	.description-text {
		display: inline-block;
		color: #555555;
		font-size: 28rpx;
		line-height: 32rpx;
	}

	.status-item {
		display: flex;
		flex-direction: column;
	}

	.status-title {
		font-size: 28rpx;
		color: #555555;
		padding: 10rpx 0;
		box-sizing: border-box;
	}

	.status-tags {
		display: flex;
		flex-wrap: wrap;
		font-size: 24rpx;
	}

	.tags-item2 {
		display: inline-flex;
		align-items: center;
		justify-content: center;
		font-size: 24rpx;
		line-height: 24rpx;
		color: #333;
		/* background-color: #efefef; */
		border-radius: 4rpx;
		padding: 10rpx 24rpx;
		margin-right: 16rpx;
		margin-bottom: 16rpx;
		position: relative;
	}

	.tags-pri {
		color: #DCA371;
		padding-left: 8rpx;
		transform: scale(0.8);
	}

	.recommend {
		position: absolute;
		top: 4rpx;
		right: 4rpx;
		width: 24rpx;
		height: 24rpx;
		display: block;
	}

	.active-tag {
		background-color: #dba871 !important;
		color: #FFFFFF !important;
	}
	
	.active-text{
		color: #FFFFFF;
	}
	.star-shrink{
		transform: scale(.7);
		transform-origin: right;
	}
	.sign{
		font-size: 46rpx!important;
	}
</style>
