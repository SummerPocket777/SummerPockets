"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      coupons: [
        { id: 1, title: "满100减20", discount: "¥20", description: "在本店消费满100元即可使用", validity: "2024-12-31", imageUrl: "https://example.com/coupon1.jpg", amount: 20, points: 200 },
        { id: 2, title: "满200减50", discount: "¥50", description: "在本店消费满200元即可使用", validity: "2024-12-31", imageUrl: "https://example.com/coupon2.jpg", amount: 50, points: 500 }
        // 更多优惠券数据
      ]
    };
  },
  methods: {
    viewCouponDetail(id) {
      common_vendor.index.navigateTo({
        url: `/pages/user/mycoupons?id=${id}`
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.f($data.coupons, (coupon, k0, i0) => {
      return {
        a: common_vendor.t(coupon.title),
        b: common_vendor.t(coupon.discount),
        c: common_vendor.t(coupon.description),
        d: common_vendor.t(coupon.validity),
        e: coupon.id,
        f: common_vendor.o(($event) => $options.viewCouponDetail(coupon.id), coupon.id)
      };
    })
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/lesson4/SummerPockets/uniapp-consumer-ui/pages/user/coupons.vue"]]);
wx.createPage(MiniProgramPage);
