<template>
	<view class="page">
		<view class="shop">
			<image class="shop-photo" src="https://picsum.photos/200"></image>
			<view class="shop-bd">
				<view style="display: flex; align-items: center; justify-content: space-between; margin-bottom: 20rpx;">
					<view class="shop-name">商家名称</view>
					<view class="shop-desc">
						查看详情<image class="shop-icon-arrow-right" src="~@/static/icon-arrow-right.png"></image>
					</view>
				</view>
				<view class="shop-star">
					<image v-for="item in 5" class="shop-star__image" src="~@/static/icon-star.png"></image>
					<view class="shop-star__score">5.0分</view>
				</view>
				<view class="shop-price">人均 88 元</view>
				<view class="shop-address">
					山东省临沂市兰山区
					<image class="shop-icon-position" src="~@/static/icon-position.png"></image>
				</view>

			</view>
		</view>
		<view class="st-tabs">
			<view @click="onClickTab(item)" v-for="item in tabs.list" :key="item.value"
				:class="['st-tab', { 'is-active': item.value === tabs.active }]">
				<view class="st-tab__text">{{ item.label }}</view>
			</view>
		</view>
		<view class="page-main">
			<view class="page-body">
				<scroll-view class="nav-left" style="height: 100%;" scroll-y :show-scrollbar="false" :scroll-top="scrollLeftTop"
					scroll-with-animation>
					<view class="nav-left-item" @click="categoryClickMain(index)" :key="index"
						:class="index == categoryActive ? 'active' : ''" v-for="(item, index) in classifyData">
						{{ item.name }}
					</view>
				</scroll-view>
				<scroll-view class="nav-right" style="height: 100%;" scroll-y :scroll-top="scrollTop" @scroll="scroll"
					scroll-with-animation>
					<view v-for="(foods, index) in classifyData" :key="index" class="box">
						<view :id="i == 0 ? 'first' : ''" class="product" v-for="(item, i) in foods.data" :key="i">
							<image class="product-image" src="https://picsum.photos/200" />
							<view style="width: 100%;">
								<view class="product-name">{{ item.name }}</view>
								<view class="product-desc">
									<text class="">销量214</text>
									<text style="margin-left: 23rpx;">好评率90%</text>
								</view>
								<view style="margin-top: 20rpx; display: flex; justify-content: space-between;">
									<view class="product-price">
										<text class="product-price__prefix">¥</text>
										<text class="product-price__value">{{ item.price }}</text>
									</view>
									<view class="product-handle">
										<image @click="onTabGoods(item, 'minus')" v-show="item._cartNumber" class="product-icon-minus" src="~@/static/icon-minus.png"></image>
										<view v-show="item._cartNumber" class="product-handle__number">{{ item._cartNumber }}</view>
										<image @click="onTabGoods(item, 'plus')" class="product-icon-plus" src="~@/static/icon-plus.png"></image>
									</view>
								</view>
							</view>
						</view>
					</view>
				</scroll-view>
			</view>
		</view>
		<!-- 底部操作栏 -->
		<view class="cart-handle">
			<view class="cart-inner">
				<view @click="onTogglePopup" class="cart-inner__hd">
					<image class="icon-cart" src="~@/static/icon-cart.png"></image>
					<view v-show="cartGoodsCount.totalNumber" class="cart-inner__number">{{ cartGoodsCount.totalNumber }}</view>
				</view>
				<view @click="onTogglePopup" class="cart-inner__totalPrice">
					<text v-show="cartGoodsCount.totalNumber" class="cart-inner__totalPrice__prefix">¥</text>
					<text v-show="cartGoodsCount.totalNumber" class="cart-inner__totalPrice__value">{{ cartGoodsCount.totalPrice
						}}</text>
				</view>
				<button class="cart-inner__button">去结算</button>
			</view>
		</view>
		<uni-popup ref="popup" type="bottom" background-color="#fff" border-radius="10px 10px 0 0">
			<view class="cart-info">
				<view v-for="item in cartGoods" class="product">
					<image class="product-image" src="https://picsum.photos/200" />
					<view style="width: 100%;">
						<view class="product-name">{{ item.name }}</view>
						<view class="product-desc">
							<text class="">销量{{ item.sales }}</text>
							<text style="margin-left: 23rpx;">好评率{{ item.comment }}%</text>
						</view>
						<view style="margin-top: 20rpx; display: flex; justify-content: space-between;">
							<view class="product-price">
								<text class="product-price__prefix">¥</text>
								<text class="product-price__value">{{ item.price }}</text>
							</view>
							<view class="product-handle">
								<image @click="onTabGoods(item, 'minus')" class="product-icon-minus"
									src="~@/static/icon-minus.png"></image>
								<view class="product-handle__number">{{ item._cartNumber }}</view>
								<image @click="onTabGoods(item, 'plus')" class="product-icon-plus"
									src="~@/static/icon-plus.png"></image>
							</view>
						</view>

					</view>
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
import data from "./data";
import Big from 'big.js';

export default {
	data() {
		return {
      storeInfo: null,
      storeCart: {
        list: [],
        showPopup: false,
      },
			tabs: {
				list: [
					{ label: '商品', value: '1' },
					{ label: '评价', value: '2' },
					{ label: '商家', value: '3' },
				],
				active: '1'
			},
			height: 0,
			categoryActive: 0,
			scrollTop: 0,
			scrollLeftTop: 0,
			classifyData: [],
			arr: [], 						//	初始值，后边计算会根据手机适配覆盖
			leftItemHeight: 41,	//	49行会计算出新值进行覆盖
			navLeftHeight: 0,		//	左边scroll-view 内层nav的总高度
			diff: 0, 						//	左边scroll-view 内层nav的总高度与视口之差
		}
	},
	onLoad: function () {
		this.getShopMenu()
	},
	computed: {
		cartGoods() {
			return this.classifyData.map(item => item.data).flat(Infinity)
				.filter(item => item._cartNumber && item._cartNumber > 0)
		},
		cartGoodsCount() {
			if (!this.cartGoods.length) {
				return {
					totalNumber: 0,
					totalPrice: 0,
				}
			}
			return this.cartGoods.reduce((cart, item) => {
				cart.totalNumber += item._cartNumber
				cart.totalPrice = Big(item._cartNumber).times(item.price).plus(cart.totalPrice).toString()
				return cart
			}, {
				totalNumber: 0,
				totalPrice: 0,
			})
		},
	},
	methods: {
		getShopMenu() {
			setTimeout(() => {
				// 异步获取时
				data.forEach(item => {
					item.data.forEach(goods => goods._cartNumber = 0)
				})
				this.classifyData = data
				this.$nextTick(() => {
					this.getHeightList();
				})
			}, 1000);
		},

		onTabGoods(item, type) {
      let number = 1
      if (type === 'plus') {
        item._cartNumber = (item._cartNumber || 0) + number
      } else if (type === 'minus') {
        number = -number
        item._cartNumber = (item._cartNumber || 0) + number
        item._cartNumber = item._cartNumber > 0 ? item._cartNumber : 0
      }
			
      if (this.storeCart.showPopup) {
        this.cartGoods.length === 0 && this.$refs.popup.close('bottom')
      }
    },
		onTogglePopup() {
      if (this.cartGoods.length === 0) return
      this.storeCart.showPopup = !this.storeCart.showPopup
      this.storeCart.showPopup ? this.$refs.popup.open('bottom') : this.$refs.popup.close('bottom')
    },

		onClickTab(item) {
			this.tabs.active = item.value
		},
		getHeightList() {
			let _this = this;
			let selectorQuery = uni.createSelectorQuery();
			selectorQuery.selectAll('.nav-left-item').boundingClientRect(function (rects) {
				_this.leftItemHeight = rects[0].height;
				_this.navLeftHeight = _this.leftItemHeight * _this.classifyData.length;
				_this.diff = _this.navLeftHeight;
			});
			selectorQuery.selectAll('.box').boundingClientRect(function (rects) {
				let arr = [0];
				let top = 0;
				console.log('rects', rects);
				rects.forEach(function (rect) {
					// rect.id      // 节点的ID
					// rect.dataset // 节点的dataset
					// rect.left    // 节点的左边界坐标
					// rect.right   // 节点的右边界坐标
					// rect.top     // 节点的上边界坐标
					// rect.bottom  // 节点的下边界坐标
					// rect.width   // 节点的宽度
					// rect.height  // 节点的高度
					top += rect.height;
					arr.push(top)
				})
				_this.arr = arr
			}).exec()
		},
		scroll(e) {
			let _this = this
			if (this.timeoutId) {
				clearTimeout(this.timeoutId);
			}
			this.timeoutId = setTimeout(function () { //节流
				_this.scrollHeight = e.detail.scrollTop + 1;
				//+1不要删除，解决最后一项某种情况下翻到底部，左边按钮并不会切换至最后一个
				//若想使切换参考线为屏幕顶部请删除 _this.height/2
				for (let i = 0; i < _this.arr.length; i++) {
					let height1 = _this.arr[i];
					let height2 = _this.arr[i + 1];
					if (!height2 || (_this.scrollHeight >= height1 && _this.scrollHeight < height2)) {
						_this.categoryActive = i;
						(_this.diff > 0) && (_this.scrollLeftTop = Math.round((_this.categoryActive * _this.diff) / (_this.classifyData.length - 1)));
						return false;
					}
				}
				_this.categoryActive = 0;
				_this.timeoutId = undefined;
			}, 10)
		},
		categoryClickMain(index) {
			this.categoryActive = index;
			this.scrollTop == this.arr[index] ? this.scrollTop = this.scrollTop + 1 : this.scrollTop = this.arr[index]//防止两次相等造成点击不触发滚动时间
		},
	},
}
</script>

<style lang="scss">
uni-page-body {
	height: 100%;
}

.cart {
	&-handle {
		position: absolute;
		left: 32rpx;
		right: 26rpx;
		height: 92rpx;
		border-radius: 46rpx;
		bottom: calc(constant(safe-area-inset-bottom) / 2 + 17rpx);
		bottom: calc(env(safe-area-inset-bottom) / 2 + 17rpx);
		z-index: 9999;
	}

	&-inner {
		width: 100%;
		display: flex;

		.icon-cart {
			width: 59.72rpx;
			height: 59.72rpx;
			vertical-align: top;
			margin-left: 36rpx;
		}

		&__hd {
			flex-shrink: 0;
			padding: 17.36rpx 0 15.28rpx 0;
			background: #000000;
			border-radius: 46rpx 0 0 46rpx;
			padding-right: 29rpx;
			position: relative;

		}

		&__number {
			position: absolute;
			top: 16rpx;
			right: 18rpx;
			font-family: Source Han Sans CN;
			font-weight: 600;
			font-size: 23rpx;
			min-width: 33rpx;
			text-align: center;
			height: 33rpx;
			background: #FF230C;
			border-radius: 50%;
			border: 1px solid #FFFFFF;
			color: #fff;
		}

		&__totalPrice {
			flex: 1;
			display: flex;
			align-items: center;
			background: #000000;
			font-size: 30rpx;
			font-weight: 600;
			color: #FFFFFF;

			&__prefix {
				align-self: self-end;
				margin-bottom: 20rpx;
			}

			&__value {
				font-family: Source Han Sans CN;
				font-size: 43rpx;
			}
		}

		&__button {
			flex-shrink: 0;
			border-radius: 0 46rpx 46rpx 0;
			font-family: Source Han Sans CN;
			font-weight: 600;
			font-size: 25rpx;
			color: #000000;
			width: 172rpx;
			line-height: 92rpx;
			background: linear-gradient(90deg, #FFDD32, #FDC034);
			;

			&::after {
				display: none;
			}
		}
	}

	&-info {
		max-height: 325px;
		overflow-y: auto;
		padding: 26rpx 26rpx 0 32rpx;
		padding-bottom: calc(constant(safe-area-inset-bottom) / 2 + 120rpx);
		padding-bottom: calc(env(safe-area-inset-bottom) / 2 + 120rpx);
	}
}

.page {
	padding: 16rpx 0 0 0;
	background: #f0f0f0;
	height: 100%;
	display: flex;
	flex-direction: column;
	box-sizing: border-box;

	&-main {
		height: 0;
		flex: 1;
	}
}

.shop {
	background: #fff;
	display: flex;
	padding: 20rpx 47.22rpx 20rpx 22.22rpx;
	border-radius: 14rpx;
	margin-bottom: 16rpx;

	&-bd {
		width: 100%;
	}

	&-icon-arrow-right {
		width: 8.33rpx;
		height: 14.58rpx;
		vertical-align: top;
		margin-left: 5rpx;
	}

	&-icon-position {
		width: 26.59rpx;
		height: 27.78rpx;
		vertical-align: top;
		margin-left: 9.7rpx;
	}

	&-photo {
		width: 175rpx;
		height: 164.58rpx;
		vertical-align: top;
		margin-right: 22rpx;
		flex-shrink: 0;
	}

	&-name {
		font-family: Source Han Sans CN;
		font-weight: 600;
		font-size: 33rpx;
		color: #000000;
		flex: 1;
	}

	&-desc {
		display: flex;
		align-items: center;
		font-family: Source Han Sans CN;
		font-weight: 300;
		font-size: 21rpx;
		color: #8C8C8C;
		line-height: 33rpx;
		align-self: flex-start;
	}

	&-star {
		display: flex;
		align-items: center;

		&__image {
			width: 26.39rpx;
			height: 25rpx;
			vertical-align: top;

			&+& {
				margin-left: 7rpx;
			}
		}

		&__score {
			font-family: Source Han Sans CN;
			font-weight: 500;
			font-size: 25rpx;
			color: #252525;
			opacity: 0.82;
			margin-left: 15rpx;
		}
	}

	&-price,
	&-address {
		font-family: Source Han Sans CN;
		font-weight: 300;
		font-size: 21rpx;
		color: #8C8C8C;
		line-height: 33rpx;
	}
}

.st-tabs {
	display: flex;
	background: #fff;
	border-radius: 14rpx 14rpx 0 0;
	padding: 38rpx 0 0 0;
	border-bottom: solid 1px #f1f1f1;

	.st-tab {
		min-width: 162rpx;
		text-align: center;
		padding: 0 10rpx;
		box-sizing: border-box;
		font-family: Source Han Sans CN;
		font-weight: 400;
		font-size: 28rpx;
		color: rgba(47, 47, 47, .78);
		line-height: 33rpx;

		&.is-active {
			.st-tab__text::after {
				opacity: 1;
				transform: scaleX(1);
			}
		}

		&__text {
			display: inline-block;
			position: relative;
			padding-bottom: 13.19rpx;

			&::after {
				content: "";
				position: absolute;
				bottom: 0;
				left: 0;
				width: 54rpx;
				height: 4rpx;
				background: #FFAB09;
				border-radius: 2rpx;
				opacity: 0;
				transform: scaleX(0);
				transition: all .2s cubic-bezier(.18, .89, .17, .88), opacity .15s ease;
			}
		}


	}
}

.page-body {
	display: flex;
	overflow: hidden;
	height: 100%;
}

.nav {
	display: flex;
	width: 100%;
}

.nav-left {
	margin-top: 10rpx;
	width: 162rpx;
	flex-shrink: 0;
}

.nav-left-item {
	height: 82rpx;
	line-height: 82rpx;
	font-family: Source Han Sans CN;
	font-weight: 400;
	font-size: 28rpx;
	color: #2F2F2F;
	display: flex;
	align-items: center;
	justify-content: center;
}

.nav-right-item {
	padding: 11rpx;
	font-size: 28rpx;
}

.nav-right-item image {
	width: 150upx;
	height: 150upx;
}

.box {
	background: #fff;
	padding: 16rpx 48rpx 0 20rpx;
	display: block;
	overflow: hidden;
	border-bottom: 20upx solid #f3f3f3;
	// min-height: 100vh;
	/* 若您的子分类过少想使得每个子分类占满屏请放开上边注释 */
}

.box:last-child {
	border: none;
	padding-bottom: 200rpx;
	
}

.product {
	display: flex;
	padding: 16rpx 0;
	&-image {
		width: 150.9rpx;
		height: 141.94rpx;
		vertical-align: top;
		flex-shrink: 0;
		border-radius: 4rpx;
		margin-right: 18rpx;
	}

	&-name {
		font-family: Source Han Sans CN;
		font-weight: 400;
		font-size: 28rpx;
		color: #000000;
		margin-bottom: 20rpx;
	}

	&-desc {
		font-family: Source Han Sans CN;
		font-weight: 300;
		font-size: 21rpx;
		color: #535353;
	}

	&-price {
		font-family: Source Han Sans CN;
		font-weight: 600;
		color: #F55A06;
		font-size: 21rpx;

		&__value {
			font-size: 28rpx;
		}
	}

	&-handle {
		display: flex;
		align-items: center;

		&__number {
			font-family: Source Han Sans CN;
			font-weight: 400;
			font-size: 25rpx;
			color: #3E3E3E;
			line-height: 25rpx;
			text-align: center;
			min-width: 62rpx;
		}
	}

	&-icon-plus,
	&-icon-minus {
		width: 29.86rpx;
		height: 29.86rpx;
		vertical-align: top;
	}
}

.active {
	background: #fff;
}

::-webkit-scrollbar {
	/*取消小程序的默认导航条样式*/
	width: 0;
	height: 0;
	color: transparent;
}
</style>
