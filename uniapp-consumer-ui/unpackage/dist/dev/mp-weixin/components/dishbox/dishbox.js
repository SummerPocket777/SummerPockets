"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  name: "dishbox",
  data() {
    return {
      busPos: {
        x: common_vendor.index.getSystemInfoSync().windowWidth - 190,
        y: common_vendor.index.getSystemInfoSync().windowHeight + 100
      }
    };
  },
  methods: {},
  props: {
    // 菜品名称
    dishName: {
      type: String,
      default: "这是一道菜的名字"
    },
    // 销量数据
    num: {
      type: Int16Array,
      default: 1e3
    },
    // 商品价格
    price: {
      type: Float32Array,
      default: 77.7
    }
  },
  components: {}
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($props.dishName),
    b: common_vendor.t($props.num),
    c: common_vendor.t($props.price),
    d: common_vendor.o(($event) => _ctx.onCustomPlay($event))
  };
}
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "C:/E/h/total/SummerPockets/uniapp-consumer-ui/components/dishbox/dishbox.vue"]]);
wx.createComponent(Component);
