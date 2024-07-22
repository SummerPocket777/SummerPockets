<!-- src/components/QrCode.vue -->
<template>
  <div :id="id" :ref="id"></div>
</template>

<script>
import QRCode from 'qrcodejs2';
import { onMounted, watch } from 'vue';

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
  setup(props) {
    const createQrcode = () => {
      const qrElement = document.getElementById(props.id);
      qrElement.innerHTML = '';
      new QRCode(qrElement, {
        text: props.text,
        width: props.width,
        height: props.height,
        colorDark: props.colorDark,
        colorLight: props.colorLight,
        correctLevel: QRCode.CorrectLevel.H
      });
    };

    onMounted(createQrcode);
    watch(() => props.text, createQrcode);

    return {};
  }
};
</script>

<style scoped>
/* Add any necessary styling here */
</style>
