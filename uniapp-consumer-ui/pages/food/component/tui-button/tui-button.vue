<template>
	<button
		 class="tui-btn"
		:class="[
			plain ? 'tui-' + type + '-outline' : 'tui-btn-' + (type || 'primary'),
			getDisabledClass(disabled, type, plain),
			getShapeClass(shape, plain),
			getShadowClass(type, shadow)
		]"
		:hover-class="getHoverClass(disabled, type, plain, hover)"
		:style="{ width: width, height: height, lineHeight: height, fontSize: size + 'rpx' }"
		:loading="loading"
		:disabled="disabled"
		@tap="handleClick"
	>
		<slot></slot>
	</button>
</template>

<script>
export default {
	name: 'dmButton',
	props: {
		//样式类型 primary, white,black,lightDark, danger, warning, green, gray,pink,milktea
		type: {
			type: String,
			default: 'primary'
		},
		//是否加阴影 type =primary和 danger有效
		shadow: {
			type: Boolean,
			default: false
		},
		// 宽度 rpx或 %
		width: {
			type: String,
			default: '100%'
		},
		//高度 rpx
		height: {
			type: String,
			default: '90rpx'
		},
		//字体大小 rpx
		size: {
			type: [Number, String],
			default: 32
		},
		//形状 circle(圆角), square(默认方形)，rightAngle(平角)
		shape: {
			type: String,
			default: 'square'
		},
		//是否镂空
		plain: {
			type: Boolean,
			default: false
		},
		//是否禁用
		disabled: {
			type: Boolean,
			default: false
		},
		//是否需要点击效果
		hover: {
			type: Boolean,
			default: true
		},
		//是否需要加载效果
		loading: {
			type: Boolean,
			default: false
		},
		index: {
			type: [Number, String],
			default: 0
		}
	},
	methods: {
		handleClick() {
			if (this.disabled) {
				return false;
			}
			this.$emit('click', {
				index: Number(this.index)
			});
		},
		getShadowClass: function(type, shadow) {
			let className = '';
			//primary和 danger有效
			if (shadow && (type == 'primary' || type == 'milktea' || type == 'danger' || type == 'gradual')) {
				className = 'tui-shadow-' + type;
			}
			return className;
		},
		getDisabledClass: function(disabled, type, plain) {
			let className = '';
			if (disabled && type != 'white' && type != 'gray') {
				className = plain ? 'tui-dark-disabled-outline' : 'tui-dark-disabled';
			}
			return className;
		},
		getShapeClass: function(shape, plain) {
			let className = '';
			if (shape == 'circle') {
				className = plain ? 'tui-outline-fillet' : 'tui-fillet';
			} else if (shape == 'rightAngle') {
				className = plain ? 'tui-outline-rightAngle' : 'tui-rightAngle';
			}
			return className;
		},
		getHoverClass: function(disabled, type, plain, hover) {
			let className = '';
			if (!disabled && hover) {
				className = plain ? 'tui-outline-hover' : 'tui-' + (type || 'primary') + '-hover';
			}
			return className;
		}
	}
};
</script>

<style>
.tui-btn-primary {
	background: #109fc7 !important;
	color: #fff;
}

.tui-shadow-primary {
	box-shadow: 0 10rpx 14rpx 0 rgba(16,159,199, 0.14);
}

.tui-btn-milktea {
	background: #dba871 !important;
	color: #fff;
}

.tui-shadow-milktea {
	box-shadow: 0 10rpx 14rpx 0 rgba(181,129,98,.14);
}

.tui-btn-danger {
	background: #eb0909 !important;
	color: #fff;
}

.tui-shadow-danger {
	box-shadow: 0 10rpx 14rpx 0 rgba(235, 9, 9, 0.2);
}

.tui-btn-pink {
	background: #fc5b5b !important;
	color: #fff;
}

.tui-shadow-pink {
	box-shadow: 0 10rpx 14rpx 0 rgba(21, 130, 173, 0.2);
}

.tui-btn-warning {
	background: #f77010 !important;
	color: #fff;
}

.tui-btn-green {
	background: #35b06a !important;
	color: #fff;
}

.tui-btn-white {
	background: #fff !important;
	color: #333 !important;
}

.tui-btn-black {
	background: #333 !important;
	color: #fff !important;
}

.tui-btn-gray {
	background: #bfbfbf !important;
	color: #fff !important;
}

.tui-btn-gradual {
	background: linear-gradient(90deg, rgb(255, 89, 38), rgb(240, 14, 44)) !important;
	color: #fff !important;
}

.tui-shadow-gradual {
	box-shadow: 0 10rpx 14rpx 0 rgba(235, 9, 9, 0.15);
}

.tui-hover-gray {
	background: #f7f7f9 !important;
}

/* button start*/

.tui-btn {
	width: 100%;
	position: relative;
	border: 0 !important;
	border-radius: 6rpx;
	padding-left: 0;
	padding-right: 0;
	overflow: visible;
	margin: 0;
}

.tui-btn::after {
	content: '';
	position: absolute;
	width: 200%;
	height: 200%;
	transform-origin: 0 0;
	transform: scale(0.5, 0.5) translateZ(0);
	box-sizing: border-box;
	left: 0;
	top: 0;
	border-radius: 12rpx;
	border: 0;
}

.tui-btn-white::after {
	border: 1px solid #bfbfbf;
}

.tui-white-hover {
	background: #e5e5e5 !important;
	color: #2e2e2e !important;
}

.tui-dark-disabled {
	background: #f3f3f3 !important;
	color: #919191 !important;
	box-shadow: none;
}
.tui-dark-disabled-outline {
	opacity: 0.5 !important;
}

.tui-outline-hover {
	opacity: 0.5;
}

.tui-primary-hover {
	background: #0e8caf !important;
	color: #e5e5e5 !important;
}

.tui-primary-outline::after {
	border: 1px solid #109fc7 !important;
}

.tui-primary-outline {
	color: #109fc7 !important;
	background: none;
}

.tui-danger-hover {
	background: #c80808 !important;
	color: #e5e5e5 !important;
}

.tui-danger-outline {
	color: #eb0909 !important;
	background: none;
}

.tui-danger-outline::after {
	border: 1px solid #eb0909 !important;
}

.tui-pink-hover {
	background: #d64d4d !important;
	color: #e5e5e5 !important;
}

.tui-pink-outline {
	color: #fc5b5b !important;
	background: none;
}

.tui-pink-outline::after {
	border: 1px solid #fc5b5b !important;
}

.tui-warning-hover {
	background: #d9620e !important;
	color: #e5e5e5 !important;
}

.tui-warning-outline {
	color: #f77010 !important;
	background: none;
}

.tui-warning-outline::after {
	border: 1px solid #f77010 !important;
}

.tui-green-hover {
	background: #2d965a !important;
	color: #e5e5e5 !important;
}

.tui-green-outline {
	color: #35b06a !important;
	background: none;
}

.tui-green-outline::after {
	border: 1px solid #35b06a !important;
}

.tui-gray-hover {
	background: #a3a3a3 !important;
	color: #898989;
}

.tui-gray-outline {
	color: #999 !important;
	background: none !important;
}

.tui-gray-outline::after {
	border: 1px solid #ccc !important;
}

.tui-white-outline {
	color: #fff !important;
	background: none !important;
}

.tui-white-outline::after {
	border: 1px solid #fff !important;
}

.tui-black-outline {
	color: #333 !important;
	background: none !important;
}

.tui-black-outline::after {
	border: 1px solid #333 !important;
}

.tui-lightDark-outline {
	color: #333 !important;
	background: none !important;
}

.tui-lightDark-outline::after {
	border: 1px solid #808080 !important;
}

.tui-gradual-hover {
	background: linear-gradient(90deg, #d74620, #cd1225) !important;
	color: #fff !important;
}

/*圆角 */

.tui-fillet {
	border-radius: 50rpx;
}

.tui-white.tui-fillet::after {
	border-radius: 98rpx;
}

.tui-outline-fillet::after {
	border-radius: 98rpx;
}

/*平角*/
.tui-rightAngle {
	border-radius: 0;
}

.tui-btn-white.tui-rightAngle::after {
	border-radius: 0;
}

.tui-outline-rightAngle::after {
	border-radius: 0;
}

.tui-milktea-hover {
	background: #ebb47a  !important;
	color: #e5e5e5 !important;
}

.tui-milktea-outline::after {
	border: 1px solid #ce926f !important;
}

.tui-milktea-outline {
	color: #ce926f !important;
	background: none;
}
</style>
