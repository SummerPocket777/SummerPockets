"use strict";
const common_vendor = require("../../common/vendor.js");
const common_assets = require("../../common/assets.js");
const _sfc_main = {
  data() {
    return {
      // 轮播图图片地址
      swiperList: [
        "https://env-00jxgrtsee4i.normal.cloudstatic.cn/微信图片_20240701165424.jpg?expire_at=1719827771&er_sign=4a5bf17c70117b2f577525966764d5db",
        "https://env-00jxgrtsee4i.normal.cloudstatic.cn/微信图片_20240701165433.jpg?expire_at=1719827864&er_sign=da2615285babc93e3b6d178d55aaa993",
        "https://env-00jxgrtsee4i.normal.cloudstatic.cn/微信图片_20240701165436.jpg?expire_at=1719827871&er_sign=ddfb4cc5507557b9e29d1de3e6691ff1"
        // 	require('@/static/images/1.jpg'),
        // 	require('@/static/images/2.jpg'),
        // 	require('@/static/images/3.jpg'),
      ],
      // 状态-信息
      status: /* @__PURE__ */ new Map([
        [10, "进行中"],
        [20, "已完成"]
      ]),
      // 按钮的样式
      btn: {
        width: "160rpx",
        height: "68rpx",
        color: "#19be6b",
        borderRadius: "14rpx",
        fontSize: "34rpx",
        border: "2rpx solid #19be6b"
      },
      btn2: {
        width: "160rpx",
        height: "68rpx",
        fontSize: "28rpx",
        borderRadius: "14rpx",
        fontSize: "34rpx"
      },
      // 订单列表信息
      List: [
        {
          name: "店铺名称1",
          status: 10,
          // 进行中
          product: ["红烧茄子", "清蒸鲈鱼", "黄焖鸡"],
          time: "2024-09-01 12:00:00",
          price: "288.00"
        },
        {
          name: "店铺名称2",
          status: 20,
          // 已完成
          product: ["红烧茄子2", "清蒸鲈鱼2", "黄焖鸡2"],
          time: "2024-09-01 10:00:00",
          price: "266.00"
        },
        {
          name: "店铺名称3",
          status: 20,
          // 已完成
          product: ["红烧茄子2", "清蒸鲈鱼2", "黄焖鸡2"],
          time: "2024-09-01 10:30:00",
          price: "266.00"
        },
        {
          name: "店铺名称4",
          status: 20,
          // 已完成
          product: ["红烧茄子2", "清蒸鲈鱼2", "黄焖鸡2"],
          time: "2024-09-01 11:00:00",
          price: "266.00"
        },
        {
          name: "店铺名称5",
          status: 20,
          // 已完成
          product: ["红烧茄子2", "清蒸鲈鱼2", "黄焖鸡2"],
          time: "2024-09-01 12:30:00",
          price: "266.00"
        }
      ]
    };
  },
  onLoad() {
  },
  methods: {}
};
if (!Array) {
  const _easycom_u_swiper2 = common_vendor.resolveComponent("u-swiper");
  const _easycom_u_button2 = common_vendor.resolveComponent("u-button");
  const _easycom_u_empty2 = common_vendor.resolveComponent("u-empty");
  (_easycom_u_swiper2 + _easycom_u_button2 + _easycom_u_empty2)();
}
const _easycom_u_swiper = () => "../../uni_modules/uview-plus/components/u-swiper/u-swiper.js";
const _easycom_u_button = () => "../../uni_modules/uview-plus/components/u-button/u-button.js";
const _easycom_u_empty = () => "../../uni_modules/uview-plus/components/u-empty/u-empty.js";
if (!Math) {
  (_easycom_u_swiper + _easycom_u_button + _easycom_u_empty)();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: common_vendor.p({
      keyName: "image",
      list: $data.swiperList,
      indicator: true,
      indicatorMode: "line",
      circular: true
    }),
    b: common_vendor.f($data.List, (item, index, i0) => {
      return common_vendor.e({
        a: common_vendor.t(item.name),
        b: common_vendor.t($data.status.get(item.status)),
        c: item.status == 10 ? "#19be6b" : "black",
        d: common_vendor.f(item.product, (data, k1, i1) => {
          return {
            a: common_vendor.t(data)
          };
        }),
        e: common_vendor.t(item.time),
        f: common_vendor.t(item.price),
        g: item.status == 10
      }, item.status == 10 ? {
        h: "93207a4f-1-" + i0,
        i: common_vendor.p({
          text: "催单",
          customStyle: $data.btn2
        }),
        j: "93207a4f-2-" + i0,
        k: common_vendor.p({
          text: "结算",
          customStyle: $data.btn2
        }),
        l: "93207a4f-3-" + i0,
        m: common_vendor.p({
          text: "加餐",
          customStyle: $data.btn
        })
      } : {}, {
        n: item
      });
    }),
    c: common_assets._imports_0,
    d: $data.List.length < 1
  }, $data.List.length < 1 ? {
    e: common_vendor.p({
      mode: "order"
    })
  } : {});
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-93207a4f"], ["__file", "C:/Users/cc129/IdeaProjects/SummerPockets/uniapp-consumer-ui/pages/order/order.vue"]]);
wx.createPage(MiniProgramPage);
