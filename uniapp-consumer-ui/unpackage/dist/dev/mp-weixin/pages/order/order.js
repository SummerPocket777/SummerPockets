"use strict";
const common_vendor = require("../../common/vendor.js");
const common_assets = require("../../common/assets.js");
const _sfc_main = {
  data() {
    return {
      radioValue: "wx_lite",
      // 控制类型显示状态，默认隐藏 
      typeShow: false,
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
      btn4: {
        width: "600rpx",
        height: "80rpx",
        background: "red",
        borderRadius: "10rpx",
        border: "none",
        color: "#FFFFFF",
        marginTop: "200rpx"
      },
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
  methods: {
    // 确定
    payment() {
      console.log("类型", this.radioValue);
    },
    closePayment() {
      this.typeShow = false;
    },
    open() {
      this.typeShow = true;
    },
    clickRadio(val) {
      this.radioValue = val;
    }
  }
};
if (!Array) {
  const _easycom_u_swiper2 = common_vendor.resolveComponent("u-swiper");
  const _easycom_u_button2 = common_vendor.resolveComponent("u-button");
  const _easycom_u_empty2 = common_vendor.resolveComponent("u-empty");
  const _easycom_u_radio2 = common_vendor.resolveComponent("u-radio");
  const _easycom_u_radio_group2 = common_vendor.resolveComponent("u-radio-group");
  const _easycom_u_popup2 = common_vendor.resolveComponent("u-popup");
  (_easycom_u_swiper2 + _easycom_u_button2 + _easycom_u_empty2 + _easycom_u_radio2 + _easycom_u_radio_group2 + _easycom_u_popup2)();
}
const _easycom_u_swiper = () => "../../uni_modules/uview-plus/components/u-swiper/u-swiper.js";
const _easycom_u_button = () => "../../uni_modules/uview-plus/components/u-button/u-button.js";
const _easycom_u_empty = () => "../../uni_modules/uview-plus/components/u-empty/u-empty.js";
const _easycom_u_radio = () => "../../uni_modules/uview-plus/components/u-radio/u-radio.js";
const _easycom_u_radio_group = () => "../../uni_modules/uview-plus/components/u-radio-group/u-radio-group.js";
const _easycom_u_popup = () => "../../uni_modules/uview-plus/components/u-popup/u-popup.js";
if (!Math) {
  (_easycom_u_swiper + _easycom_u_button + _easycom_u_empty + _easycom_u_radio + _easycom_u_radio_group + _easycom_u_popup)();
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
        j: common_vendor.o($options.open, item),
        k: "93207a4f-2-" + i0,
        l: common_vendor.p({
          text: "结算",
          customStyle: $data.btn2
        }),
        m: "93207a4f-3-" + i0,
        n: common_vendor.p({
          text: "加餐",
          customStyle: $data.btn
        })
      } : {}, {
        o: item
      });
    }),
    c: common_assets._imports_0,
    d: $data.List.length < 1
  }, $data.List.length < 1 ? {
    e: common_vendor.p({
      mode: "order"
    })
  } : {}, {
    f: common_vendor.p({
      name: "wx_lite",
      activeColor: "#00CA5B",
      size: "22"
    }),
    g: common_vendor.o(($event) => $data.radioValue = $event),
    h: common_vendor.p({
      modelValue: $data.radioValue
    }),
    i: common_vendor.p({
      name: "member_amount",
      activeColor: "#00CA5B",
      size: "22"
    }),
    j: common_vendor.o(($event) => $data.radioValue = $event),
    k: common_vendor.p({
      modelValue: $data.radioValue
    }),
    l: common_vendor.o($options.payment),
    m: common_vendor.p({
      customStyle: $data.btn4,
      text: "确定",
      color: "linear-gradient(to bottom, #FFB176, #FF7942)"
    }),
    n: common_vendor.o($options.closePayment),
    o: common_vendor.p({
      show: $data.typeShow,
      closeable: true,
      round: 8,
      mode: "center"
    })
  });
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-93207a4f"], ["__file", "E:/4thSummerPockets/uniapp-consumer-ui/pages/order/order.vue"]]);
wx.createPage(MiniProgramPage);
