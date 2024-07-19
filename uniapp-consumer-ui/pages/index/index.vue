<template>
	<view class="content" style="background-image: url(../../static/index-back.jpg);">
		<!-- 小程序基础库需配置为 2.22.1 -->
		<view class="uni-list">
			<view class="uni-list-cell">
				<view class="uni-list-cell-left">
					当前选择的餐厅
				</view>
				<view>
					<picker :value="index" :range="arr" @change="bindChange">
						<view>{{arr[index]}}</view>
					</picker>
				</view>
			</view>
		</view>
		<button size="mini" open-type="getUserInfo" @tap="getUserProfile">预约</button>
		<button @click="getAllBusinesses">是否登录</button>
		<view :class="yuyueClass">
			<view class="yuyue-title">
				<text>预约</text>
			</view>

			<view class="yuyue-view">
				<text style="width: 150rpx;padding-top: 13rpx;">用餐人数</text>
				<input class="yuyue-input" v-model="yuyueInfo.peopleNumber" placeholder="请输入用餐人数" type="number"
					maxlength="2" />
			</view>
			<view class="yuyue-view">
				<text style="width: 150rpx;padding-top: 13rpx;">预计时间</text>
				<uni-datetime-picker type="datetime" :clear-icon="false" :start="today"
					v-model="yuyueInfo.datetimesingle" @change="handleChange">
				</uni-datetime-picker>
			</view>
			<view class="yuyue-view">
				<text style="width: 150rpx;text-align: center;letter-spacing: 40rpx;padding-top: 13rpx;">姓名</text>
				<input class="yuyue-input" v-model="yuyueInfo.userName" placeholder="请输入姓名" />
			</view>
			<view class="yuyue-view">
				<text style="width: 150rpx;text-align: center;letter-spacing: 40rpx;padding-top: 13rpx;">电话</text>
				<input class="yuyue-input" v-model="yuyueInfo.userPhone" placeholder="请输入电话" type="number"
					maxlength="11" />
			</view>
			<view class="yuyue-sure">
				<button @click="yuyueCancel">取消</button>
				<button type="primary" @click="yuyueSure">确认预约</button>
			</view>

		</view>
	</view>
</template>

<script>
	import {
		mapState,
		mapStores,
		mapActions
	} from 'pinia'
	import {
		useCounterStore
	} from '@/store/yuyue.js'
	import {
		useUserStore
	} from '@/store/user.js'

	export default {
		data() {
			return {
				loading: false,
				yuyueClass: 'yuyue-box1',
				yuyueInfo: {
					peopleNumber: '',
					userName: '',
					userPhone: '',
					datetimesingle: ''
				},
				today: '',
				userInfo: {
					code: ''
				},
				index: 0,
				arr: [1,2,3,4]

			}
		},
		onLoad() {
			this.setToday()
console.log(11111111111111)
			this.getAllBusinesses()
			console.log(222222222222)
			
		},
		onShow() {
			this.setToday()
			
		},
		onReady() {


		},
		computed: {
			...mapStores(useCounterStore, useUserStore),
			...mapState(useUserStore, ['myUserInfo']),
			...mapState(useCounterStore, ['businessInfo']),
			// ...mapState(useCounterStore, ['count', 'double'])
		},
		methods: {
			...mapActions(useCounterStore, ['insertYuyue',,'getAllBusinessin']),
			...mapActions(useUserStore, ['login', 'islogin']),
			setToday() {
				const now = new Date();
				const year = now.getFullYear();
				const month = String(now.getMonth() + 1).padStart(2, '0');
				const date = String(now.getDate()).padStart(2, '0');
				const hours = String(now.getHours()).padStart(2, '0');
				const minutes = String(now.getMinutes()).padStart(2, '0');
				this.today = `${year}-${month}-${date} ${hours}:${minutes}`;
			},
			handleChange(e) {
				console.log("日期" + e)
			},
			yuyueSure(e) {
				console.log(this.yuyueInfo)
				console.log(11111111111111111)
				console.log(this.businessInfo.name)
				console.log(this.myUserInfo.id)
				console.log(11111111111111111)
				const selectedDateTime = new Date(this.yuyueInfo.datetimesingle).getTime();
				const currentDateTime = new Date().getTime();
				const sevenDaysLaterDateTime = currentDateTime + (7 * 24 * 60 * 60 * 1000); // 7天后的时间戳
				if (this.yuyueInfo.datetimesingle == "") {
					uni.showToast({
						title: '请选择日期',
						icon: 'error',
					})
				} else if (this.yuyueInfo.userPhone == "") {
					uni.showToast({
						title: '请输入电话',
						icon: 'error',

					})
				} else if (this.yuyueInfo.userName == "") {
					uni.showToast({
						title: '请输入姓名',
						icon: 'error',
					})
				} else if (this.yuyueInfo.userName.length > 10) {
					uni.showToast({
						title: '请输入正确的昵称',
						icon: 'error',
					})
				} else if (this.yuyueInfo.peopleNumber == "") {
					uni.showToast({
						title: '请选择人数',
						icon: 'error',

					})
				} else if (this.yuyueInfo.userPhone.length < 11) {
					uni.showToast({
						title: '请输入正确的电话号码',
						icon: 'none',

					})
				} else if (selectedDateTime < currentDateTime) {
					uni.showToast({
						title: '选择有误',
						icon: 'none'
					})
				} else if (selectedDateTime > sevenDaysLaterDateTime) {
					uni.showToast({
						title: '请选择7天内的日期',
						icon: 'none'
					})
				} else {
					this.insertYuyue({
							"bookNumber": this.yuyueInfo.peopleNumber,
							"bookDate": this.yuyueInfo.datetimesingle,
							"bookName": this.yuyueInfo.userName,
							"bookPhone": this.yuyueInfo.userPhone,
							"businessName": this.businessInfo.name,
							"userId": this.myUserInfo.id
						})
						.then(res => {
							console.log("预约结果", res);
							if (res.code == 20000) {
								uni.showToast({
									title: '预约成功',
									icon: 'success'

								})
							} else {
								uni.showToast({
									title: '网络请请求失败',
									icon: 'loading'

								})
							}
						}).catch(err => {
							console.error("预约失败", err);
							uni.showToast({
								title: '网络请求失败',
								icon: 'loading'

							})
						});

				}
				this.yuyueInfo = {
					peopleNumber: '',
					userName: '',
					userPhone: '',
					datetimesingle: ''
				}
				this.yuyueClass = "yuyue-box1"
			},
			yuyueCancel() {
				this.yuyueInfo = {
					peopleNumber: '',
					userName: '',
					userPhone: '',
					datetimesingle: ''
				}
				this.yuyueClass = "yuyue-box1"
			},
			getUserProfile() {
				console.log(this.myUserInfo)
				if (this.myUserInfo.name == '' || this.myUserInfo.image == '') {

					console.log("未登录")
					wx.login({
						success: (res) => {
							console.log("res", res)
							console.log("this ", this)
							this.userInfo.code = res.code
							this.login(this.userInfo)
								.then(res => {
									console.log("登录结果", res)
									this.myUserInfo.id = res.data.id
									console.log(this.myUserInfo.id)
								}).catch(err => {
									console.error("登录失败", err)
								})

						}
					})
					wx.getUserProfile({
						desc: '测试',
						success: (res) => {
							console.log("进来了")
							console.log(res)
							console.log(res.userInfo.nickName)
							console.log(res.userInfo.avatarUrl)
							
							this.myUserInfo.name = res.userInfo.nickName
							this.myUserInfo.image = res.userInfo.avatarUrl
							console.log(this.myUserInfo)

							this.yuyueClass = "yuyue-box"
						},
						fail: (err) => {
							console.log(err)
							//拒绝授权
							wx.showToast({
								title: '拒绝授权',
								icon: 'error',
								duration: 2000
							})
						}
					})
				} else {
					console.log("一登陆")
					this.yuyueClass = "yuyue-box"
				}

			},
			testtes() {
				this.userStore.islogin()
			},
			bindChange(e) {
				console.log('picker发送选择改变，携带值为', e.detail.value)
				this.index = e.detail.value
				this.businessInfo.name = this.arr[this.index]
				console.log(this.businessInfo.name)
			},
			getAllBusinesses(){
				this.getAllBusinessin().then(res => {
					console.log(11111111111111)
					console.log(res.data)
					console.log(222222222222)
					for(let i = 0;i<res.data.length;i++){
						
						this.arr[i] = res.data[i].businessName
					}
					console.log(this.arr)
					this.businessInfo.name = this.arr[this.index]
					
				}).catch(err => {
					console.log(err)
				})
			}
		}
	}
</script>

<style lang="scss">
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		width: 100vw;
		height: 100vh;
		overflow: hidden;
		position: relative;

		background-size: cover;
		background-position: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}

	.yuyue-box {
		background-color: whitesmoke;
		width: 600rpx;
		height: 650rpx;
		display: block;
		position: fixed;
		top: 200rpx;
	}

	.yuyue-box1 {
		background-color: whitesmoke;
		width: 600rpx;
		height: 500rpx;
		position: fixed;
		top: 200rpx;
		display: none;
	}

	.yuyue-view {
		padding-top: 10rpx;
		display: flex;
		height: 80rpx;
		border-bottom: 2rpx dashed gray;
		padding-left: 20rpx;
		padding-right: 15px;
	}

	.yuyue-title {
		letter-spacing: 10rpx;
		display: flex;
		height: 100rpx;
		align-items: center;
		justify-content: center;
	}

	.yuyue-input {
		width: 387rpx;
		padding-top: 5rpx;
		height: 60rpx;
		border: 1rpx solid #e5e5e5;
		border-radius: 10rpx;
		padding-left: 10rpx;
		background-color: white;
	}

	.yuyue-sure {
		display: flex;
		position: absolute;
		bottom: 0;
		width: 100%;
	}

	.yuyue-sure button {
		width: 350rpx;
	}
	.uni-list{
		margin-bottom: 300rpx;
	}
	.uni-list-cell{
		text-align: center;
		background-color: #e5e5e5;
		border-radius: 20rpx;
		width: 550rpx;
		height: 145rpx;
		font-size: 50rpx;
	}
</style>