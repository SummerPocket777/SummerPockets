// voucherrequest.js

import request from '@/http/request.js';

export const article = {
  fetchCoupons(data) {
    return request({
      url: 'consumer-voucher/voucher/query/user',
      method: 'POST',
      data,
    });
  },
};
