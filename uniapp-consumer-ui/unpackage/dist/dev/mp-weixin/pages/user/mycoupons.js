"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      coupon: null,
      details: [],
      hasEnoughPoints: false
    };
  },
  async onLoad(options) {
    const id = options.id;
    const couponData = {
      1: { imageUrl: "https://example.com/coupon1.jpg", amount: 20, points: 200, period: "即日起 至 2024-12-31", threshold: "满100元可用", applicableProducts: "全场通用", applicableStores: "所有门店", usageScenario: "仅限线下使用", description: "此优惠券可在所有商品上使用", instructions: "使用时请出示此优惠券", remark: "此优惠券不可与其他优惠叠加使用" },
      2: { imageUrl: "https://example.com/coupon2.jpg", amount: 50, points: 500, period: "即日起 至 2024-12-31", threshold: "满200元可用", applicableProducts: "全场通用", applicableStores: "所有门店", usageScenario: "仅限线下使用", description: "此优惠券可在所有商品上使用", instructions: "使用时请出示此优惠券", remark: "此优惠券不可与其他优惠叠加使用" }
      // 更多优惠券数据
    };
    this.coupon = couponData[id];
    this.hasEnoughPoints = this.checkPoints(this.coupon.points);
    this.details = [
      { label: "有效期限", value: this.coupon.period },
      { label: "卡券金额", value: this.coupon.amount + "元" },
      { label: "卡券门槛", value: this.coupon.threshold },
      { label: "可用商品", value: this.coupon.applicableProducts },
      { label: "可用门店", value: this.coupon.applicableStores },
      { label: "可用场景", value: this.coupon.usageScenario },
      { label: "卡券说明", value: this.coupon.description },
      { label: "卡券介绍", value: this.coupon.remark }
    ];
  },
  methods: {
    checkPoints(requiredPoints) {
      const userPoints = 1e3;
      return userPoints >= requiredPoints;
    },
    goToUse() {
      if (this.hasEnoughPoints) {
        common_vendor.index.switchTab({
          url: "/pages/shop/shop"
        });
      }
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: $data.coupon
  }, $data.coupon ? {
    b: $data.coupon.imageUrl,
    c: common_vendor.t($data.coupon.amount),
    d: common_vendor.f($data.details, (item, index, i0) => {
      return {
        a: common_vendor.t(item.label),
        b: common_vendor.t(item.value),
        c: index
      };
    }),
    e: common_vendor.t($data.hasEnoughPoints ? "去使用" : "积分不足"),
    f: !$data.hasEnoughPoints,
    g: common_vendor.o((...args) => $options.goToUse && $options.goToUse(...args))
  } : {});
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-af8c9a08"], ["__file", "C:/Users/cc129/IdeaProjects/SummerPockets/uniapp-consumer-ui/pages/user/mycoupons.vue"]]);
wx.createPage(MiniProgramPage);
