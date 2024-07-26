<template>
  <div :id="id" :ref="id"></div>
</template>

<script>
import QRCode from 'qrcodejs2';

export default {
  name: 'QrCode',
  props: {
    id: {
      type: String,
      required: true
    },
    text: {
      type: String,
      required: true
    },
    width: {
      type: Number,
      default: 128
    },
    height: {
      type: Number,
      default: 128
    },
    colorDark: {
      type: String,
      default: '#000000'
    },
    colorLight: {
      type: String,
      default: '#ffffff'
    }
  },
  watch: {
    text() {
      this.createQrcode();
    }
  },
  mounted() {
    this.createQrcode();
  },
  methods: {
    createQrcode() {
      const qrElement = document.getElementById(this.id);
      qrElement.innerHTML = '';
      new QRCode(qrElement, {
        text: this.text,
        width: this.width,
        height: this.height,
        colorDark: this.colorDark,
        colorLight: this.colorLight,
        correctLevel: QRCode.CorrectLevel.H
      });
    }
  }
};
</script>

<style scoped>
/* Add any necessary styling here */
</style>
