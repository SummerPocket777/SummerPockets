"use strict";
const common_vendor = require("../../common/vendor.js");
const store_yuyue = require("../../store/yuyue.js");
const _sfc_main = {
  data() {
    return {
      loading: false,
      yuyueClass: "yuyue-box1",
      yuyueInfo: {
        peopleNumber: "",
        userName: "",
        userPhone: "",
        datetimesingle: ""
      },
      today: ""
    };
  },
  onLoad() {
    this.setToday();
    this.increment();
    console.log(this.userNames);
    console.log(this.count);
  },
  onReady() {
  },
  computed: {
    ...common_vendor.mapStores(store_yuyue.useCounterStore),
    ...common_vendor.mapState(store_yuyue.useCounterStore, ["count", "double"])
  },
  methods: {
    ...common_vendor.mapActions(store_yuyue.useCounterStore, ["insertYuyue", "increment"]),
    setToday() {
      const now = /* @__PURE__ */ new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, "0");
      const date = String(now.getDate()).padStart(2, "0");
      const hours = String(now.getHours()).padStart(2, "0");
      const minutes = String(now.getMinutes()).padStart(2, "0");
      this.today = `${year}-${month}-${date} ${hours}:${minutes}`;
    },
    clike() {
      console.log(this.yuyueClass);
      this.loading = true;
      setTimeout(() => {
        this.loading = false;
        this.yuyueClass = "yuyue-box";
      }, 2e3);
    },
    handleChange(e) {
      console.log("日期" + e);
    },
    yuyueSure(e) {
      console.log(this.yuyueInfo);
      const selectedDateTime = new Date(this.yuyueInfo.datetimesingle).getTime();
      const currentDateTime = (/* @__PURE__ */ new Date()).getTime();
      const sevenDaysLaterDateTime = currentDateTime + 7 * 24 * 60 * 60 * 1e3;
      if (this.yuyueInfo.datetimesingle == "") {
        common_vendor.index.showToast({
          title: "请选择日期",
          icon: "error"
        });
      } else if (this.yuyueInfo.userPhone == "") {
        common_vendor.index.showToast({
          title: "请输入电话",
          icon: "error"
        });
      } else if (this.yuyueInfo.userName == "") {
        common_vendor.index.showToast({
          title: "请输入姓名",
          icon: "error"
        });
      } else if (this.yuyueInfo.userName.length > 10) {
        common_vendor.index.showToast({
          title: "请输入正确的昵称",
          icon: "error"
        });
      } else if (this.yuyueInfo.peopleNumber == "") {
        common_vendor.index.showToast({
          title: "请选择人数",
          icon: "error"
        });
      } else if (this.yuyueInfo.userPhone.length < 11) {
        common_vendor.index.showToast({
          title: "请输入正确的电话号码",
          icon: "none"
        });
      } else if (selectedDateTime < currentDateTime) {
        common_vendor.index.showToast({
          title: "选择有误",
          icon: "none"
        });
      } else if (selectedDateTime > sevenDaysLaterDateTime) {
        common_vendor.index.showToast({
          title: "请选择7天内的日期",
          icon: "none"
        });
      } else {
        this.insertYuyue({
          "bookNumber": this.yuyueInfo.peopleNumber,
          "bookDate": this.yuyueInfo.datetimesingle,
          "bookName": this.yuyueInfo.userName,
          "bookPhone": this.yuyueInfo.userPhone
        }).then((res) => {
          console.log("预约结果", res);
        }).catch((err) => {
          console.error("预约失败", err);
        });
      }
      this.yuyueInfo = {
        peopleNumber: "",
        userName: "",
        userPhone: "",
        datetimesingle: ""
      };
      this.yuyueClass = "yuyue-box1";
    },
    yuyueCancel() {
      this.yuyueInfo = {
        peopleNumber: "",
        userName: "",
        userPhone: "",
        datetimesingle: ""
      };
      this.yuyueClass = "yuyue-box1";
    },
    getUserProfile() {
      {
        console.log("未登录");
        common_vendor.wx$1.login({
          success: (res) => {
            console.log("res.code", res.code);
          }
        });
        common_vendor.wx$1.getUserProfile({
          desc: "测试",
          success: (res) => {
            console.log("进来了");
            console.log(res);
            console.log(res.userInfo.nickName);
            console.log(res.userInfo.avatarUrl);
            getApp().globalData.userName = res.userInfo.nickName;
            getApp().globalData.userImage = res.userInfo.avatarUrl;
            this.clike();
          },
          fail: (err) => {
            console.log(err);
            common_vendor.wx$1.showToast({
              title: "拒绝授权",
              icon: "error",
              duration: 2e3
            });
          }
        });
      }
    }
  }
};
if (!Array) {
  const _easycom_uni_datetime_picker2 = common_vendor.resolveComponent("uni-datetime-picker");
  _easycom_uni_datetime_picker2();
}
const _easycom_uni_datetime_picker = () => "../../uni_modules/uni-datetime-picker/components/uni-datetime-picker/uni-datetime-picker.js";
if (!Math) {
  _easycom_uni_datetime_picker();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o((...args) => $options.getUserProfile && $options.getUserProfile(...args)),
    b: $data.yuyueInfo.peopleNumber,
    c: common_vendor.o(($event) => $data.yuyueInfo.peopleNumber = $event.detail.value),
    d: common_vendor.o($options.handleChange),
    e: common_vendor.o(($event) => $data.yuyueInfo.datetimesingle = $event),
    f: common_vendor.p({
      type: "datetime",
      ["clear-icon"]: false,
      start: $data.today,
      modelValue: $data.yuyueInfo.datetimesingle
    }),
    g: $data.yuyueInfo.userName,
    h: common_vendor.o(($event) => $data.yuyueInfo.userName = $event.detail.value),
    i: $data.yuyueInfo.userPhone,
    j: common_vendor.o(($event) => $data.yuyueInfo.userPhone = $event.detail.value),
    k: common_vendor.o((...args) => $options.yuyueCancel && $options.yuyueCancel(...args)),
    l: common_vendor.o((...args) => $options.yuyueSure && $options.yuyueSure(...args)),
    m: common_vendor.n($data.yuyueClass)
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "G:/cykjWorkSpacrce/SummerPockets/uniapp-consumer-ui/pages/index/index.vue"]]);
wx.createPage(MiniProgramPage);
