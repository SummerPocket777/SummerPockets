<template>
    <div class="body">
      <div  class="login-box">
        <p style="margin-left: 250px;">注册</p>
      <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="账号">
      <el-input v-model="userVo.account"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="userVo.password" show-password></el-input>
    </el-form-item>

    <el-form-item label="验证码">
      <el-col :span="8" style="margin-right: 120px;" >    <el-input v-model="userVo.imgCode" size="small" > </el-input></el-col>

      <el-col :span="8">    <img :src="codeImg" @click="changeCode"></el-col>
    </el-form-item>

    <el-form-item label="手机号：">
        <el-input v-model="userVo.phone" />
      </el-form-item>
      <el-form-item label="校验码：">
        <el-col :span="10" style="margin-right: 100px;"><el-input v-model="userVo.code" /></el-col>

        <el-button type="warning" @click="sendVerificationCode (userVo.phone,userVo.imgCode)" >
          {{'发送验证码' }}
        </el-button>
      </el-form-item>


    <el-form-item label="">

    <el-button style="margin-left: 100px;" @click="doReg(userVo)">注册</el-button>
    <el-button @click="docancel()">取消</el-button>


    </el-form-item>


  </el-form>
    </div>
  </div>

  </template>

  <style>
  .login-box {
    width: 500px;
    height: 600px;
    margin: auto;
    padding-right: 50px;
    background-color: rgba(255, 255, 255, 0.7); /* 透明度为0.7的白色背景 */
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);

  }
  .body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: blanchedalmond;
        background-size: cover;
        background-position: center;
        height: 100vh;

        display: flex;
        justify-content: center;
        align-items: center;

      }
  </style>


  <script>
import { getCode } from '@/api/user'
import { register } from '@/api/user'
// import {reactive, ref} from 'vue'
// import Axios from '../api/axios';
// import {ElMessage} from "element-plus";
//   import { setToken } from '@/util/cookieUtil';

    export default {
      data() {
        return {
            codeImg:"/api/auth/captcha/getCode?"+Math.random(),

            form: {
            code: '',
            date2: '',
            delivery: false,
            type: [],
            resource: '',
            desc: ''
          },

            userVo:{
              account:"",
              password:"",
              phone: "",
              imgCode: "",
              code:"",

          },
        }
      },
      methods: {
        onSubmit() {
          console.log('submit!');
        },
        changeCode(){
          this.codeImg="/api/auth/captcha/getCode?"+Math.random()
        },
        docancel(){
            this.$router.push({
              path:"/login"
            })
        },
        doReg(userVo) {
        //   this.$axios.post(
        //     "/api/auth/user/register",
        //     this.userVo
        //   ).then(res => {
        //     console.log(res)


        //       this.$message('注册成功');

        //       this.$router.push({
        //       path: '/login',  //通过path跳转是以get的方式跳转的，传递方式是query
        // })


        //   }).catch(e =>{

        //   })
          register(userVo).then(res =>{
            console.log(res);
          })

        },
        sendVerificationCode(phone,imgCode){

            getCode(phone,imgCode).then(res=>{
              console.log(res);
            })
        }
      },
    }
  </script>
