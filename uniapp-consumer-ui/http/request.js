// import useUserStore from '@/store/user.js'
import config from './config.js';

const request = (options) => {
	const {
		method,
		url,
		data,
		timeout = config.timeOut,
		header = {}
	} = options;
	// const userStore = useUserStore();
	// const token = userStore.token;
	let contentType = 'application/json;charset=UTF-8';
	// if (token.length > 0) {
	// 	header['token'] = token;
	// }
	header['content-type'] = contentType;
	uni.showLoading({
		title: '加载中...',
	});
	return new Promise((resolve, reject) => {
		// console.log("request url", config.baseUrl + url);
		uni.request({
			header: header,
			method: method,
			timeout: timeout,
			url: config.baseUrl + url,
			data: data,
			success: (res) => {
				// console.log("request data", res);
				// console.log("request data.statusCode", res.statusCode);
				if (res.statusCode === 200) {
					resolve(res.data);
				} else {
					// 可以根据具体的业务逻辑处理不同的状态码
					uni.showToast({
						title: res.errMsg || '请求失败',
						duration: 2000,
						icon: 'none',
					});
					reject(res.data);
				}
			},
			fail: (err) => {
				console.log("fail err", err);
				uni.showToast({
					title: '网络请求失败',
					duration: 2000,
					icon: 'none',
				});
				reject(err);
			},
			complete: () => {
				uni.hideLoading();
				uni.stopPullDownRefresh();
			},
		});
	});
};

export default request;
