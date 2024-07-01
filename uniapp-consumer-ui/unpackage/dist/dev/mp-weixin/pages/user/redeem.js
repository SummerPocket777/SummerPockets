"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      coupon: {
        id: 1,
        title: "满100减20",
        description: "在本店消费满100元即可使用",
        validity: "2024-12-31",
        imageUrl: "../../static/icon/优惠券.png"
      },
      redeemCode: ""
    };
  },
  methods: {
    redeemCoupon() {
      if (!this.redeemCode) {
        common_vendor.index.showToast({
          title: "请输入核销码",
          icon: "none"
        });
        return;
      }
      common_vendor.index.request({
        url: "example",
        method: "POST",
        data: {
          code: this.redeemCode,
          couponId: this.coupon.id
        },
        success: (res) => {
          if (res.data.success) {
            common_vendor.index.showToast({
              title: "核销成功",
              icon: "success"
            });
            this.redeemCode = "";
          } else {
            common_vendor.index.showToast({
              title: res.data.message || "核销失败",
              icon: "none"
            });
          }
        },
        fail: () => {
          common_vendor.index.showToast({
            title: "网络错误，请稍后重试",
            icon: "none"
          });
        }
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: $data.coupon.imageUrl,
    b: common_vendor.t($data.coupon.title),
    c: common_vendor.t($data.coupon.description),
    d: common_vendor.t($data.coupon.validity),
    e: $data.redeemCode,
    f: common_vendor.o(($event) => $data.redeemCode = $event.detail.value),
    g: common_vendor.o((...args) => $options.redeemCoupon && $options.redeemCoupon(...args))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-c5290c67"], ["__file", "E:/lesson4/SummerPockets/uniapp-consumer-ui/pages/user/redeem.vue"]]);
wx.createPage(MiniProgramPage);
