"use strict";
const common_vendor = require("../../common/vendor.js");
const store_yuyue = require("../../store/yuyue.js");
const store_user = require("../../store/user.js");
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
      today: "",
      userInfo: {
        code: ""
      },
      index: 0,
      arr: [1, 2, 3, 4]
    };
  },
  onLoad() {
    this.setToday();
    console.log(11111111111111);
    this.getAllBusinesses();
    console.log(222222222222);
  },
  onShow() {
    this.setToday();
  },
  onReady() {
  },
  computed: {
    ...common_vendor.mapStores(store_yuyue.useCounterStore, store_user.useUserStore),
    ...common_vendor.mapState(store_user.useUserStore, ["myUserInfo"]),
    ...common_vendor.mapState(store_yuyue.useCounterStore, ["businessInfo"])
    // ...mapState(useCounterStore, ['count', 'double'])
  },
  methods: {
    ...common_vendor.mapActions(store_yuyue.useCounterStore, ["insertYuyue", , "getAllBusinessin"]),
    ...common_vendor.mapActions(store_user.useUserStore, ["login", "islogin"]),
    setToday() {
      const now = /* @__PURE__ */ new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, "0");
      const date = String(now.getDate()).padStart(2, "0");
      const hours = String(now.getHours()).padStart(2, "0");
      const minutes = String(now.getMinutes()).padStart(2, "0");
      this.today = `${year}-${month}-${date} ${hours}:${minutes}`;
    },
    handleChange(e) {
      console.log("日期" + e);
    },
    yuyueSure(e) {
      console.log(this.yuyueInfo);
      console.log(11111111111111112);
      console.log(this.businessInfo.name);
      console.log(this.myUserInfo.id);
      console.log(11111111111111112);
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
          "bookPhone": this.yuyueInfo.userPhone,
          "businessName": this.businessInfo.name,
          "userId": this.myUserInfo.id
        }).then((res) => {
          console.log("预约结果", res);
          if (res.code == 2e4) {
            common_vendor.index.showToast({
              title: "预约成功",
              icon: "success"
            });
          } else {
            common_vendor.index.showToast({
              title: "网络请请求失败",
              icon: "loading"
            });
          }
        }).catch((err) => {
          console.error("预约失败", err);
          common_vendor.index.showToast({
            title: "网络请求失败",
            icon: "loading"
          });
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
      console.log(this.myUserInfo);
      if (this.myUserInfo.name == "" || this.myUserInfo.image == "") {
        console.log("未登录");
        common_vendor.wx$1.login({
          success: (res) => {
            console.log("res", res);
            console.log("this ", this);
            this.userInfo.code = res.code;
            this.login(this.userInfo).then((res2) => {
              console.log("登录结果", res2);
              this.myUserInfo.id = res2.data.id;
              console.log(this.myUserInfo.id);
            }).catch((err) => {
              console.error("登录失败", err);
            });
          }
        });
        common_vendor.wx$1.getUserProfile({
          desc: "测试",
          success: (res) => {
            console.log("进来了");
            console.log(res);
            console.log(res.userInfo.nickName);
            console.log(res.userInfo.avatarUrl);
            this.myUserInfo.name = res.userInfo.nickName;
            this.myUserInfo.image = res.userInfo.avatarUrl;
            console.log(this.myUserInfo);
            this.yuyueClass = "yuyue-box";
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
      } else {
        console.log("一登陆");
        this.yuyueClass = "yuyue-box";
      }
    },
    testtes() {
      this.userStore.islogin();
    },
    bindChange(e) {
      console.log("picker发送选择改变，携带值为", e.detail.value);
      this.index = e.detail.value;
      this.businessInfo.name = this.arr[this.index];
      console.log(this.businessInfo.name);
    },
    getAllBusinesses() {
      this.getAllBusinessin().then((res) => {
        console.log(11111111111111);
        console.log(res.data);
        console.log(222222222222);
        for (let i = 0; i < res.data.length; i++) {
          this.arr[i] = res.data[i].businessName;
        }
        console.log(this.arr);
        this.businessInfo.name = this.arr[this.index];
      }).catch((err) => {
        console.log(err);
      });
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
    a: common_vendor.t($data.arr[$data.index]),
    b: $data.index,
    c: $data.arr,
    d: common_vendor.o((...args) => $options.bindChange && $options.bindChange(...args)),
    e: common_vendor.o((...args) => $options.getUserProfile && $options.getUserProfile(...args)),
    f: common_vendor.o((...args) => $options.getAllBusinesses && $options.getAllBusinesses(...args)),
    g: $data.yuyueInfo.peopleNumber,
    h: common_vendor.o(($event) => $data.yuyueInfo.peopleNumber = $event.detail.value),
    i: common_vendor.o($options.handleChange),
    j: common_vendor.o(($event) => $data.yuyueInfo.datetimesingle = $event),
    k: common_vendor.p({
      type: "datetime",
      ["clear-icon"]: false,
      start: $data.today,
      modelValue: $data.yuyueInfo.datetimesingle
    }),
    l: $data.yuyueInfo.userName,
    m: common_vendor.o(($event) => $data.yuyueInfo.userName = $event.detail.value),
    n: $data.yuyueInfo.userPhone,
    o: common_vendor.o(($event) => $data.yuyueInfo.userPhone = $event.detail.value),
    p: common_vendor.o((...args) => $options.yuyueCancel && $options.yuyueCancel(...args)),
    q: common_vendor.o((...args) => $options.yuyueSure && $options.yuyueSure(...args)),
    r: common_vendor.n($data.yuyueClass)
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/lesson4/new/SummerPockets/uniapp-consumer-ui/pages/index/index.vue"]]);
wx.createPage(MiniProgramPage);
