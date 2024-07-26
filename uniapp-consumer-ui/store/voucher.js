import { defineStore } from 'pinia';
import { article } from '@/api/voucherrequest.js';
import { handlePromise } from '@/util/promise-pro.js';

export const useVoucherStore = defineStore('voucher', {
  state: () => ({
    availableCoupons: [], // 待使用优惠券
    usedCoupons: [],      // 已使用优惠券
    expiredCoupons: []    // 已过期优惠券
  }),
}
  actions: {
    async fetchCoupons(status) {
      try {
        const response = await handlePromise(article.fetchCoupons, { status });
        if (response.code === 1031) {
          const coupons = Array.isArray(response.data) ? response.data : [response.data];
          if (status === 0) {
            this.availableCoupons = coupons;
          } else if (status === 1) {
            this.usedCoupons = coupons;
          } else if (status === 2) {
            this.expiredCoupons = coupons;
          }
        } else {
          console.error('获取优惠券失败:', response.msg);
        }
      } catch (error) {
        console.error('获取优惠券失败，大失败:', error);
      }
    },
}