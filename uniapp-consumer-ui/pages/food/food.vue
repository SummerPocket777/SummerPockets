<template>
	<view class="container">
		<!-- 顶部面板 -->
		<view class="top--panel">
			<!-- 顶部面板，可添加所需要放在页面顶部的内容代码。比如banner图 -->
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
					<u-image width="50rpx" height="50rpx" src="../../static/icon/定位.png" @tap="intoMap"></u-image>
				</view>
			</view>
		</view>
		<!-- 滚动区域 -->
		<view class="scroll-panel" id="scroll-panel">
			<view class="list-box">
				<view class="left">
					<scroll-view scroll-y="true" :style="{ 'height':scrollHeight + 'px' }"
						:scroll-into-view="leftIntoView" :scroll-with-animation="false">
						<view class="item" v-for="(item,index) in leftArray" :key="index"
							:class="{ 'active':index==leftIndex }" :id="'left-'+index" :data-index="index"
							@tap="leftTap">{{item}}</view>
					</scroll-view>
				</view>
				<view class="main">
					<scroll-view scroll-y="true" :style="{ 'height':scrollHeight + 'px' }" @scroll="mainScroll"
						:scroll-into-view="scrollInto" :scroll-with-animation="false">
						<view class="item main-item" v-for="(item,index) in mainArray" :key="index" :id="'item-'+index">
							<view class="title">
								<view>{{item.title}}</view>
							</view>
							<view class="goods" v-for="(item2,index2) in item.list" :key="index2">
								<image src="/static/logo.png" mode=""></image>
								<view>
									<view>第{{index2+1}}个商品标题</view>
									<view class="describe">第{{index2+1}}个商品的描述内容</view>
									<view class="money">第{{index2+1}}个商品的价格</view>
								</view>
								<view @click="addCar" :data-item="item2">加入购物车</view>
							</view>
						</view>
						<view class="fill-last" :style="{ 'height':fillHeight + 'px' }"></view>
					</scroll-view>
				</view>
			</view>
		</view>

	</view>
	<!-- 底部购物车 -->
	<view class="car" v-if="isShowCar">
		<view class="car-left">
			<view class="car-pice">¥{{totalPrice}}</view>
		</view>
		<view class="car-right">
			<view class="car-text">选好了</view>
		</view>
	</view>
	<!-- 凸起图标 -->
	<view class="car-img-back" v-if="isShowCar" @click="clickCar">
		<image class="car-img" src="../../static/icon/定位.png" mode=""></image>
		<!-- 角标 -->
		<view class="car-num">
			<text class="car-num-text">{{totalNum}}</text>
		</view>
	</view>
	<!-- 购物车列表 -->
	<view class="car-list" v-if="isShowList">
		<scroll-view scroll-y="true" style="height: 500rpx;">
			<view class="car-box" v-for="(item, index) in carData" :key="index">
				<view class="car-left-image" style="width: 23%;">
					<image class="car-menu-image" :src="item.imgUrl" mode=""></image>
				</view>
				<view class="car-right-text" style="width: 73%;">
					<view class="car-menu-name">{{item.name}}</view>
					<view class="car-menu-explain">{{item.explain}}</view>
					<view class="car-menu-ps">
						<view class="car-menu-price">¥{{item.price}}</view>
						<view class="car-num-select">
							<view @click="clickMinus(index)">
								<image style="width: 48rpx;height: 48rpx;" src="../../../static/icon/png/jian.png">
								</image>
							</view>
							<view style="margin: 0rpx 15rpx;font-size: 28rpx;"> {{item.num}} </view>
							<view @click="clickAdd(index)">
								<image style="width: 48rpx;height: 48rpx;" src="../../../static/icon/png/jia.png">
								</image>
							</view>
						</view>
					</view>
				</view>
			</view>
		</scroll-view>
	</view>
	<!-- 遮罩层 -->
	<view class="car-mark" v-if="isShowCarMark" @click="clickCarMark"></view>
</template>

<script>
	export default {
		data() {
			return {
				scrollHeight: 400,
				scrollTopSize: 0,
				fillHeight: 0, // 填充高度，用于最后一项低于滚动区域时使用
				leftArray: [],
				mainArray: [],
				topArr: [],
				leftIndex: 0,
				scrollInto: '',
				// 购物车
				carData: [],
				totalPrice: 0,
				totalNum: 0,
				isShowCar: true,
				isShowList: false,
				isShowCarMark: false,
				isAddHeight: true,
			}
		},
		computed: {
			/* 计算左侧滚动位置定位 */
			leftIntoView() {
				return `left-${this.leftIndex > 3 ? (this.leftIndex-3):0}`;
			}
		},
		onReady() {
			/* 在非H5平台，nextTick回调后有概率获取到错误的元素高度，则添加200ms的延迟来减少BUG的产生 */
			setTimeout(() => {
				/* 等待滚动区域初始化完成 */
				this.initScrollView().then(() => {
					/* 获取列表数据，你的代码从此处开始 */
					this.getListData();
				})
			}, 100);
		},
		methods: {
			/* 初始化滚动区域 */
			initScrollView() {
				return new Promise((resolve, reject) => {
					let view = uni.createSelectorQuery().select('#scroll-panel');
					view.boundingClientRect(res => {
						this.scrollTopSize = res.top;
						this.scrollHeight = res.height;
						setTimeout(() => {
							resolve();
						}, 100);
					}).exec();
				});
			},
			/* 获取列表数据 */
			getListData() {
				// Promise 为 ES6 新增的API ，有疑问的请自行学习该方法的使用。
				new Promise((resolve, reject) => {
					/* 因无真实数据，当前方法模拟数据。正式项目中将此处替换为 数据请求即可 */
					uni.showLoading();
					
					
					
					// 模拟数据
					let mockData = () => {
						let [left, main] = [
							[],
							[]
						];

						let size =5;
						for (let i = 0; i < size; i++) {
							left.push(`${i+1}类商品`);

							let list = [];
							let r = Math.floor(Math.random() * 10);
							r = r < 5 ? 5 : r;
							for (let j = 0; j < r; j++) {
								list.push(j);
							}
							main.push({
								title: `第${i+1}类商品标题`,
								list
							})
						}

						return {
							left,
							main
						}
					}
					setTimeout(() => {
						let res = mockData();
						let {
							left,
							main
						} = res;
						// 将请求接口返回的数据传递给 Promise 对象的 then 函数。
						resolve({
							left,
							main
						});
					}, 1000);
				}).then((res) => {
					console.log('-----------请求接口返回数据示例-------------');
					console.log(res);

					uni.hideLoading();
					this.leftArray = res.left;
					this.mainArray = res.main;

					// DOM 挂载后 再调用 getElementTop 获取高度的方法。
					setTimeout(() => {
						this.getElementTop();
					}, 100);
				});
			},
			/* 获取元素顶部信息 */
			getElementTop() {
				new Promise((resolve, reject) => {
					let view = uni.createSelectorQuery().selectAll('.main-item');
					view.boundingClientRect(data => {
						resolve(data);
					}).exec();
				}).then((res) => {
					let topArr = res.map((item) => {
						return item.top - this.scrollTopSize; /* 减去滚动容器距离顶部的距离 */
					});
					this.topArr = topArr;

					/* 获取最后一项的高度，设置填充高度。判断和填充时做了 +-20 的操作，是为了滚动时更好的定位 */
					let last = res[res.length - 1].height;
					if (last - 20 < this.scrollHeight) {
						this.fillHeight = this.scrollHeight - last + 20;
					}
				});
			},
			/* 主区域滚动监听 */
			mainScroll(e) {
				// 节流方法
				clearTimeout(this.mainThrottle);
				this.mainThrottle = setTimeout(() => {
					scrollFn();
				}, 10);

				let scrollFn = () => {
					let top = e.detail.scrollTop;
					let index = 0;
					/* 查找当前滚动距离 */
					for (let i = (this.topArr.length - 1); i >= 0; i--) {
						/* 在部分安卓设备上，因手机逻辑分辨率与rpx单位计算不是整数，滚动距离与有误差，增加2px来完善该问题 */
						if ((top + 2) >= this.topArr[i]) {
							index = i;
							break;
						}
					}
					this.leftIndex = (index < 0 ? 0 : index);
				}
			},
			intoMap() {
				uni.navigateTo({
					url: '../shop/map'
				})
			},
			/* 左侧导航点击 */
			leftTap(e) {
				let index = e.currentTarget.dataset.index;
				this.scrollInto = `item-${index}`;
			},
			// 购物车相关代码
			//记录滚动条位置
			scroll: function(e) {
				this.scrollTop = e.detail.scrollTop
			},

			//**********商品加入购物车 ***************
			// 商品加入购物车
			    addCar(e) {
					console.log("购物车的item",e.target.dataset.item)
			        let item = e.target.dataset.item;
			        let index = this.carData.findIndex(ev => ev.name === item.name);
			        if(index === -1) {  
			            item.num = 1; // 添加数量属性num，默认为1
			            this.carData.push(item); // 把商品追加进购物车
			        } else { 
			            this.carData[index].num++; // 存在相同的商品则数量叠加
			        }
			        this.allNum();
			        this.allPrice();
			        this.isShowCar = true;
			    },

			 // 增加数量
			    clickAdd(index) {                    
			        this.carData[index].num++;
			        this.allNum();
			        this.allPrice();
			    },

			 // 减少数量，小于1时删除元素
			    clickMinus(index) {
			        if(this.carData[index].num > 1){
			            this.carData[index].num--;                
			        } else {
			            this.carData.splice(index,1); // 从数组删除元素
			        }    
			        this.allNum();
			        this.allPrice();
			    },


			// 计算商品总数量
			    allNum() {                
			        let count = 0;
			        this.carData.forEach(item => {
			            count += item.num;
			        });
			        this.totalNum = count;
			
			        // 购物车有商品时，调整滚动区域高度
			        if(this.totalNum === 1 && this.isAddHeight){
			            this.scrollHeight -= 50;
			            this.isAddHeight = false;
			        } 
			        // 购物车没有商品时，隐藏组件，还原滚动区域高度
			        if (this.totalNum === 0){
			            this.scrollHeight += 50;
			            this.isAddHeight = true;
			            this.isShowCar = false;
			            this.clickCar();                    
			        }
			    },    

			 // 计算商品总价格
			    allPrice() {                
			        let Price = 0;
			        this.carData.forEach(item => {
			            Price += item.num * parseFloat(item.price);
			        });
			        this.totalPrice = Price.toFixed(2); // 保留两位小数
			    },
			
			    // 显示、隐藏购物车清单
			    clickCar() {
			        this.isShowList = !this.isShowList;
			        this.isShowCarMark = !this.isShowCarMark;
			    },
			
			    clickCarMark() {
			        this.isShowList = !this.isShowList;
			        this.isShowCarMark = !this.isShowCarMark;
			    },
		}
	}
</script>

<style lang="scss">
	page {
		height: 100vh;
	}

	.container {
		height: 100%;
	}

	.shop-top {
		width: 100%;
		height: 300rpx;
		background: linear-gradient(186deg, #84F9B9 0%, #8fd3f4 100%);
		border-top: #ffffff solid 1rpx;
		display: flex;
		flex-direction: column;

		// justify-content: center;
		// align-items: center;
		.top-name {
			width: 750rpx;
			height: 200rpx;
			display: flex;
			// justify-content: center;
			align-items: center;

			.top-img {
				margin: 0 50rpx;
			}

			.top-text {
				margin-top: 40rpx;

				.text-first {
					margin-bottom: 10rpx;
				}

				.text-second {}
			}
		}

		.top-search {
			margin-left: 50rpx;
			display: flex;
		}

	}

	/* 容器 */
	.container {
		display: flex;
		flex-direction: column;
		flex-wrap: nowrap;
		justify-content: flex-start;
		align-items: flex-start;
		align-content: flex-start;

		&>view {
			width: 100%;
		}

		.scroll-panel {
			flex-grow: 1;
			height: 0;
			overflow: hidden;
		}

		.bottom-panel {
			padding-bottom: 0;
			padding-bottom: constant(safe-area-inset-bottom);
			padding-bottom: env(safe-area-inset-bottom);
		}
	}


	.list-box {
		display: flex;
		flex-direction: row;
		flex-wrap: nowrap;
		justify-content: flex-start;
		align-items: flex-start;
		align-content: flex-start;
		font-size: 28rpx;

		.left {
			width: 200rpx;
			background-color: #f6f6f6;
			line-height: normal;
			box-sizing: border-box;
			font-size: 32rpx;

			.item {
				padding: 30rpx;
				position: relative;


				&+.item {
					margin-top: 1px;

					&::after {
						content: '';
						display: block;
						height: 0;
						border-top: #d6d6d6 solid 1px;
						width: 620upx;
						position: absolute;
						top: -1px;
						right: 0;
						transform: scaleY(0.5);
						/* 1px像素 */
					}
				}

				&.active {
					color: #42b983;
					background-color: #fff;
					position: relative;

					&::before {
						content: '';
						display: block;
						position: absolute;
						top: 0;
						left: 0;
						border-left: #42b983 solid 4px;
						height: 100%;
						width: 0;
					}
				}
			}

			.fill-last {
				height: 0;
				width: 100%;
				background: none;
			}
		}

		.main {
			background-color: #fff;
			padding-left: 20rpx;
			width: 0;
			flex-grow: 1;
			box-sizing: border-box;



			.title {
				line-height: normal;
				padding: 30rpx 0;
				font-size: 24rpx;
				font-weight: bold;
				color: #666;
				background-color: #fff;
				position: sticky;
				top: 0;
				z-index: 19;
			}

			.item {
				padding-bottom: 16rpx;
				border-bottom: #eee solid 1px;
			}

			.goods {
				display: flex;
				flex-direction: row;
				flex-wrap: nowrap;
				justify-content: flex-start;
				align-items: center;
				align-content: center;

				&+.goods {
					margin-top: 16rpx;
				}

				&>image {
					width: 120rpx;
					height: 120rpx;
					margin-right: 16rpx;
					margin-left: 2px;
				}

				.describe {
					font-size: 24rpx;
					color: #999;
				}

				.money {
					font-size: 24rpx;
					color: #efba21;
				}
			}
		}
	}

	/* 购物车 */
	.car {
		display: flex;
		flex-direction: row;
		align-items: center;
		height: 100rpx;
		width: 100%;
		position: fixed;
		bottom: 0rpx;
		background-color: #ffffff;
		z-index: 79;
	}

	.car-img-back {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		height: 100rpx;
		width: 100rpx;
		position: fixed;
		bottom: 35rpx;
		left: 30rpx;
		background-color: #A7D500;
		border-radius: 50%;
		z-index: 79;
	}

	.car-img {
		height: 60rpx;
		width: 60rpx;
	}

	.car-left {
		/* 平均分布元素,父元素display: flex; 子元素flex:1; */
		flex: 1;
	}

	.car-pice {
		padding-left: 160rpx;
		font-size: 34rpx;
		font-weight: 700;
	}

	.car-num {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		height: 30rpx;
		width: 30rpx;
		position: fixed;
		bottom: 100rpx;
		left: 110rpx;
		background-color: #ffea49;
		border-radius: 50%;
		z-index: 79;
	}

	.car-num-text {
		font-size: 20rpx;
	}

	.car-right {
		/* 平均分布元素,父元素display: flex; 子元素flex:1; */
		flex: 1;
	}

	.car-text {
		height: 100rpx;
		width: 200rpx;
		line-height: 100rpx;
		margin-left: 200rpx;
		text-align: center;
		background-color: #A7D500;
	}

	.car-list {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		height: 500rpx;
		width: 100%;
		position: fixed;
		bottom: 100rpx;
		background-color: #ffffff;
		border-radius: 10px 10px 0px 0px;
		z-index: 78;
	}

	.car-container {
		display: flex;
		flex-direction: column;
		width: 100%;
	}

	.car-box {
		width: 100%;
		height: 120rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		padding: 20rpx 10rpx;
		border-bottom: solid #f4f4f4 1px;
	}

	.car-menu-image {
		width: 100rpx;
		height: 100rpx;
		margin-left: 35rpx;
	}

	.car-menu-name {
		font-weight: normal;
		font-size: 28rpx;
		margin-bottom: 10rpx;
	}

	.car-menu-explain {
		font-size: 20rpx;
		margin-bottom: 10rpx;
		/* 下方四项配合使用,超出范围的显示... */
		width: 400rpx;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}

	.car-menu-ps {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		/*两边对齐*/
	}

	.car-menu-price {
		font-size: 26rpx;
		font-weight: 600;
	}

	.car-num-select {
		display: flex;
		flex-direction: row;
		width: 160rpx;
		height: 36rpx;
	}

	/* 遮罩层 */
	.car-mark {
		position: fixed;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		z-index: 77;
		background-color: rgba(0, 0, 0, 0.4);
		transition: all 0.3s ease-in 0s;
	}
</style>