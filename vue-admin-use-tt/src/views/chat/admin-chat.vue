<template>
  <body>



<div class="abs cover contaniner" id="app">
    <div class="abs cover pnl">
        <div class="top pnl-head" style="padding: 20px ; color: white;">
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
        <div class="abs cover pnl-body" id="pnlBody">
            <div class="abs cover pnl-left" id="initBackground" style="background-color: white; width: 100%">
                <div class="abs cover pnl-left" id="chatArea" v-show="isShowChat">
                    <div class="abs cover pnl-msgs scroll" id="show">
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
                                    <div class="msg-host photo"
                                        style="background-image: url(@/assets/img/avatar/Member002.jpg)"></div>
                                    <div class="msg-ball">{{message.message}}</div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="abs bottom pnl-text">
                        <div class="abs cover pnl-input">
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
                <div class="abs right pnl-right">
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


</body>
</template>

<script>



export default {

  data() {
        return {
                    isShowChat: true,
                    chatMes: true,
                    isOnline: true,
                    username: "xixi",
                    toName: "",
                    sendMessage: {
                        toName: "",
                        message: ""
                    },
                    inputMessage: "",
                    historyMessage: [
                        /*{toName: "李四", message: "你好,张三"},
                        {toName: "李四", message: "在吗"},
                        {toName: "李四", message: "怎么不说话"},
                        {fromName: "张三", message: "你好,李四"}*/
                    ],
                    friendsList: [
                        /* "李四",
                         "王五"*/
                    ],
                    systemMessages: [
                        /*"张三",
                        "李四"*/
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
                    ws = new WebSocket("ws://localhost:5455/chat/xixi");

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

<style lang="scss" scoped>
html {
    font-size: small;
    height: 100%
}

body {
    font-family: 'Microsoft Yahei', '微软雅黑', 'Hiragino Sans GB', Tahoma, Helvetica, Arial, '宋体', nimbussansl, liberationsans, freesans, clean, sans-serif, 'Segoe UI Emoji', 'Segoe UI Symbol';
    min-height: 100%;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    background-color: #fff;
    margin: 0;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    display: fl;
}

div {
    background-position: center;
    background-repeat: no-repeat
}

h4 {
    margin: 0;
    padding: .5em;
    font-size: 1.1em
}

p {
    font-size: 1em;
    padding: .5em;
    margin: 0
}

</style>
