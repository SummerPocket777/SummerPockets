<template>
	<view class="content">
		<view class="cell">
			<tui-button type="primary" width="560rpx" @click="showDetail">卡片式-商品详情</tui-button>
		</view>
		<x-shop-modal :modalShow="isShowDetail" @close="closeDetail" :dataObj="goodObj" @addCart="addToCart"></x-shop-modal>
	</view>
</template>

<script>
	import tuiButton from '@/components/tui-button/tui-button'
	import xShopModal from '@/components/x-shopModal/x-shopModal'
	import data from '../../static/data.js'
	export default {
		components:{
			tuiButton,
			xShopModal
		},
		data() {
			return {
				isShowDetail:false,
				itemDetail:[],
				goodObj:{}
			}
		},
		onLoad() {
			this.itemDetail = data[0].goodsArr[0]
		},
		methods: {
			showDetail(){
				this.isShowDetail = true
				this.goodObj = {
					img: this.itemDetail.image,
					name: this.itemDetail.name,
					tags: this.itemDetail.tags,
					priNum: this.itemDetail.priNum,
					showPrice: this.itemDetail.pri,
					description: this.itemDetail.desci,
					chooseOptions: this.itemDetail.chooseOptions,
					leastBuy: this.itemDetail.leastBuy,
					mostBuy: this.itemDetail.mostBuy,
					storage: this.itemDetail.storage,
					materialId: this.itemDetail.materialId
				}
			},
			closeDetail(){
				this.isShowDetail = false
			},
			addToCart(e) {
				console.log(e)
			}
		}
	}
</script>

<style>
	.content{
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.cell{
		padding: 24rpx 0;
		 box-sizing: border-box;
	}
</style>
