<template>
  <div class="" id="apptt">
        <div class="">
            <div class="top sphead" style="padding: 20px ; color: white;">
                <div id="userName">
                    用户：{{username}}
                    <span style='float: right;color: green' v-if="isOnline">在线</span>
                    <span style='float: right;color: red' v-else>离线</span>
                </div>
                <div id="chatMes" v-show="chatMes" style="text-align: center;color: #6fbdf3;font-family: 新宋体">
                    正在和 <font face="楷体">{{toName}}</font> 聊天
                </div>
            </div>
            <!--聊天区开始-->
            <div class="" id="pnlBody">
                <div class="talk-body" id="initBackground" style="background-color: white; width: 100%">
                    <div class="abs cover talk-left " id="chatArea" v-show="isShowChat">
                        <div class="talk-window" id="show">
                            <div class="pnl-list" id="hists"><!-- 历史消息 --></div>
                            <div class="pnl-list" id="msgs" v-for="message in historyMessage" :key="message">

                                <!-- 消息这展示区域 -->
                                <div class="msg guest" v-if="message.toName">
                                    <div class="msg-right">
                                        <div class="msg-host headDefault"></div>
                                        <div class="msg-ball">{{message.message}}</div>
                                    </div>
                                </div>
                                <div class="msg robot" v-else>
                                    <div class="msg-left" worker="">
                                        <!-- <div class="msg-host photo"
                                            style="background-image: url(img/avatar/Member002.jpg)"></div> -->
                                        <div class="msg-ball">{{message.message}}</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="abs bottom pnl-text talk-input post">
                            <div class="abs cover pnl-input ">
                                <textarea class="scroll" id="context_text" @keyup.enter="submit" wrap="hard"
                                    placeholder="在此输入文字信息..." v-model="sendMessage.message"></textarea>
                                <div class="abs atcom-pnl scroll hide" id="atcomPnl">
                                    <ul class="atcom" id="atcom"></ul>
                                </div>
                            </div>

                            <div class="abs br pnl-btn" id="submit" @click="submit"
                                style="background-color: rgb(32, 196, 202); color: rgb(255, 255, 255);">
                                发送
                            </div>
                        </div>
                    </div>
                    <!--聊天区 结束-->
                    <div class="abs right talk-right">
                        <div class="slider-container hide"></div>
                        <div class="pnl-right-content">
                            <div class="pnl-tabs">
                                <div class="tab-btn active" id="hot-tab">好友列表</div>
                            </div>
                            <div class="pnl-hot">
                                <ul class="rel-list unselect">
                                    <li class="rel-item" v-for="friend in friendsList" :key="friend"><a
                                            @click='showChat(friend)'>{{friend}}</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="pnl-right-content">
                            <div class="pnl-tabs">
                                <div class="tab-btn active">系统广播</div>
                            </div>
                            <div class="pnl-hot">
                                <ul class="rel-list unselect" id="broadcastList">
                                    <li class="rel-item" style="color: #9d9d9d;font-family: 宋体"
                                        v-for="name in systemMessages" :key="name">您的好友
                                        {{name}} 已上线</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>





        </div>
    </div>
</template>

<script>


let ws;
export default {

  data() {
        return {
                    isShowChat: true,
                    chatMes: true,
                    isOnline: true,
                    username: "admin",
                    toName: "",
                    sendMessage: {
                        toName: "",
                        message: ""
                    },
                    inputMessage: "",
                    historyMessage: [
                        // {toName: "李四", message: "你好,张三"},
                        // {toName: "李四", message: "在吗"},
                        // {toName: "李四", message: "怎么不说话"},
                        // {fromName: "张三", message: "你好,李四"}
                    ],
                    friendsList: [
                        //  "李四",
                        //  "王五"
                    ],
                    systemMessages: [
                        // "张三",
                        // "李四"
                    ]
                }
            },
            created() {
                this.init();
            },
            methods: {
                async init() {
                    // await axios.get("http://127.0.0.1:5455/user/getUsername").then(res => {
                    //     this.username = res.data;
                    // });

                    //创建webSocket对象
                    ws = new WebSocket("ws://localhost:5455/chat/admin");

                    //给ws绑定事件
                    ws.onopen = this.onOpen;
                    //接收到服务端推送的消息后触发
                    ws.onmessage = this.onMessage;

                    ws.onclose = this.onClose;
                },
                showChat(name) {
                    this.toName = name;
                    //清除聊天区的数据
                    let history = sessionStorage.getItem(this.toName);
                    if (!history) {
                        this.historyMessage = [];
                    } else {
                        this.historyMessage = JSON.parse(history);
                    }
                    //展示聊天对话框
                    this.isShowChat = true;
                    //显示“正在和谁聊天”
                    this.chatMes = true;
                },
                submit() {
                    this.sendMessage.toName = this.toName;
                    this.historyMessage.push(JSON.parse(JSON.stringify(this.sendMessage)));
                    sessionStorage.setItem(this.toName, JSON.stringify(this.historyMessage));

                    ws.send(JSON.stringify(this.sendMessage));
                    
                    this.sendMessage.message = "";
                },
                onOpen() {
                    this.isOnline = true;
                },
                onClose() {
                    this.isOnline = false;
                },
                onMessage(evt) {
                    //获取服务端推送过来的消息
                    var dataStr = evt.data;
                    //将dataStr 转换为json对象
                    var res = JSON.parse(dataStr);

                    //判断是否是系统消息
                    if (res.system) {
                        //系统消息  好友列表展示
                        var names = res.message;
                        this.friendsList = [];
                        this.systemMessages = [];
                        for (let i = 0; i < names.length; i++) {
                            if (names[i] != this.username) {
                                this.friendsList.push(names[i]);
                                this.systemMessages.push(names[i]);
                            }
                        }
                    } else {
                        //非系统消息
                        var history = sessionStorage.getItem(res.fromName);
                        if (!history) {
                            this.historyMessage = [res];
                        } else {
                            this.historyMessage.push(res);
                        }
                        sessionStorage.setItem(res.fromName, JSON.stringify(this.historyMessage));
                    }
                }
            }


}
</script>

<style  scoped>
  .sphead {
        background-color: rgb(72, 74, 75);
        border-top-left-radius: 15px;
        /* 左上角圆角 */
        border-top-right-radius: 15px;
        /* 右上角圆角 */
    }

    #apptt {
        margin: 0 auto;
        width: 900px;
        height: 700px;
        border-radius: 15px;
        border: 1px solid rgb(72, 74, 75);
        box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
    }

    .talk-body {
        display: flex;
    }

    .talk-left {
        width: 620px;
        height: 620px;
        border-right: 0.5px solid rgb(194, 194, 194);
    }

    .talk-right {
        width: 280px;
        height: 620px;

    }

    .talk-window {
        width: 620px;
        height: 490px;
        border-bottom: 0.5px solid rgb(194, 194, 194);

    }


    .talk-input {
        width: 610px;
        height: 125px;

        border-bottom-left-radius: 15px;
        /* 左上角圆角 */
        border-bottom-right-radius: 15px;
        /* 右上角圆角 */

    }


    .pnl-input textarea {
        padding: .4em 0;
        white-space: pre-wrap;
        word-wrap: break-word;
        overflow: hidden;
        line-height: 1.2;
        font-size: 1.2em;
        overflow-y: visible;
        outline: none
    }

    .pnl-input>textarea {
        width: 95%;
        height: 100%;
        display: block;
        border: 0;
        resize: none;
        color: #323232;
        padding: .5em .8em;
        font-size: 1em;
        line-height: 20px;
        background-color: #fff;
        overflow: auto;
        cursor: text
    }

    .post-pnl-btn {
        display: none;
        margin: .5em .5em 0 0;
        padding: .3em .5em;
        border-radius: .3em;
        color: #fff;
        background-color: #454545;
        float: right;
        cursor: pointer
    }

    .post:hover .post-pnl-btn {
        display: block
    }

    .pnl-btn {
        right: .5em !important;
        bottom: .5em !important;
        color: #fff;
        padding: .3em;
        width: 4em;
        text-align: center;
        background-color: #484a4b;
        border-radius: .2em;
        cursor: pointer;
        margin-left: auto;
    }

    .pnl-btn:hover {
        opacity: .8;
        -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=80)";
        filter: alpha(opacity=80)
    }


    .pnl-tabs {
        padding: 15px;
        margin-left: 25px;

        width: 75px;
        color: #6fbdf3;
        border-bottom: 2px solid #6fbdf3;
    }

    .pnl-hot {
        border-top: 0.5px solid rgb(194, 194, 194);
    }

    ul,
    li {
        list-style: none;
        margin: 0;
        padding: 0
    }


    .rel-item {
        margin: .5em 0;
        position: relative;
        counter-increment: rel;
        line-height: 1.5em;
        cursor: pointer;
        margin-left: 20px;
    }

    .rel-item:hover {
        opacity: .6;
        -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=60)";
        filter: alpha(opacity=60)
    }

    .msg.guest .ic {
        margin: 0
    }

    body.ifr.ifr2 .btn-head #guestBook .v5-btn-pnl {
        right: 305px !important
    }

    .headDefault {
        background-color: transparent !important;
        border-radius: 5px;
        /* background-image: url(@/assets/img/avatar/Member002.jpg); */
        margin-left: auto;
        margin-right: 20px;
        margin-top: 10px;
    }

    .msg-host {
        top: .5em;
        -webkit-border-radius: 2em;
        border-radius: 2em;
        width: 2.5em;
        height: 2.5em;
        background-color: #6fbdf3;
        color: #fff;
        overflow: hidden;
        text-align: center;
    }

    .msg-host>i {
        font-size: 2em;
        line-height: 1.25
    }

    .msg-ball {
        line-height: 1.3;
        padding: .7em 1em;
        margin: .5em 4em;
        border-radius: .4em;
        min-width: 2em;
        max-width: 100%;
        background-color: #efefef;
    }

    .msg-left {
        overflow: hidden;
    }

    .msg-left>.msg-host {
        left: .5em;
        top: .5em
    }

    .msg-left>.msg-ball {
        float: left;
    }

    .msg-left>.msg-ball:before {
        left: -.5em;
        border-color: transparent #f0f0f0 transparent transparent;
        border-width: .4em .6em .4em 0
    }

    .msg-left:before {
        content: attr(worker);
        display: block;
        color: #848484;
        margin-bottom: -.5em;
        margin-left: 3.8em;
        padding-top: .5em
    }

    .msg-left>.msg-host {
        left: .5em;
        top: .7em;
        background-color: #6fbdf3;
    }

    .msg-left>.msg-host .ic {
        font-size: 2.65em
    }

    .msg-right {
        overflow: hidden;
    }

    .msg-right>.msg-host {
        right: .5em;
        top: .5em
    }

    .msg-right>.msg-ball {
        float: right;
    }

    .msg-right>.msg-ball:before {
        right: -.55em;
        border-color: transparent transparent transparent #f0f0f0;
        border-width: .4em 0 .4em .6em
    }

    .photo {
        margin-left: 20px;
    }


</style>
