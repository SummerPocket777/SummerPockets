<template>
    <div style="margin: 30px;">
        <h1>优惠券管理</h1>
        <el-form :inline="true" :model="a">
            <el-form-item label="优惠券名称">
                <el-input></el-input>
            </el-form-item>
            <el-form-item label="优惠券类型" width="100px">
                <el-select v-model="b.c" placeholder="优惠券类型">
                    <el-option label="现金券" value="现金券"></el-option>
                    <el-option label="折扣券" value="折扣券"></el-option>
                    <el-option label="满减券" value="满减券"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="状态">
                <el-select v-model="b.c" placeholder="状态">
                    <el-option label="启用" value="启用"></el-option>
                    <el-option label="停用" value="停用"></el-option>
                    <el-option label="已过期" value="已过期"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="success" @click="addVoucher">新增</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="voucherTable" border style="width: 100%; line-height: 0;">
            <el-table-column align="center" label="优惠券名称" prop="title"></el-table-column>
            <el-table-column align="center" label="优惠券规则" prop="rules"></el-table-column>
            <el-table-column align="center" label="优惠券类型" prop="type"></el-table-column>
            <el-table-column align="center" label="有效期" prop="expiryDate"></el-table-column>
            <el-table-column align="center" label="状态" prop="status">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.status === '启用' ? 'primary' : 'warning'">
                        {{ scope.row.status }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column align="center" label="操作" width="250px">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger" @click="editVoucherButton(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="deleteVoucherButton(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog :title="voucherDialogTitle" center :visible.sync="voucherDialogShow" width="400px">
            <el-form :model="thisVoucher" label-width="90px">
                <el-form-item label="优惠券名称">
                    <el-input v-model="thisVoucher.title"></el-input>
                </el-form-item>
                <el-form-item label="优惠券规则">
                    <el-input v-model="thisVoucher.rules"></el-input>
                </el-form-item>
                <el-form-item label="优惠券类型">
                    <el-select v-model="thisVoucher.type" placeholder="优惠券类型">
                        <el-option label="现金券" value="现金券"></el-option>
                        <el-option label="折扣券" value="折扣券"></el-option>
                        <el-option label="满减券" value="满减券"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="thisVoucher.status" placeholder="状态">
                        <el-option label="启用" value="启用"></el-option>
                        <el-option label="停用" value="停用"></el-option>
                        <el-option label="已过期" value="已过期"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="开始时间">
                    <el-date-picker v-model="thisVoucher.createTime" type="date" placeholder="选择日期"></el-date-picker>
                </el-form-item>
                <el-form-item label="结束时间">
                    <el-date-picker v-model="thisVoucher.expirationTime" type="date" placeholder="选择日期"></el-date-picker>
                </el-form-item>
            </el-form>
            <div style="margin-left: 180px;">
                <el-button @click="addVoucherDialogShow = false">取消</el-button>
                <el-button type="primary" @click="addVoucherSureButton">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return {
            a: {},
            b: {
                c: {}
            },
            voucherDialogTitle: '',
            voucherDialogShow: false,
            thisVoucher: {},
            voucherTable: [
                {
                    title: '超值优惠券',
                    type: '满减券',
                    rules: '满100减20',
                    expiryDate: '2024-07-04至2024-12-04',
                    createTime: '2024-07-04',
                    expirationTime: '2024-12-04',
                    status: '启用'
                },
                {
                    title: '超值优惠券',
                    type: '满减券',
                    rules: '满150减40',
                    expiryDate: '2024-07-04至2024-12-04',
                    createTime: '2024-07-04',
                    expirationTime: '2024-12-04',
                    status: '停用'
                },
                {
                    title: '超值优惠券',
                    type: '满减券',
                    rules: '满150减40',
                    expiryDate: '2024-07-04至2024-12-04',
                    createTime: '2024-07-04',
                    expirationTime: '2024-12-04',
                    status: '已过期'
                }
            ]
        }
    },
    methods: {
        onSubmit() {

        },
        // 优惠券新增按钮
        addVoucher() {
            this.voucherDialogTitle = '新增优惠券'
            this.voucherDialogShow = true
        },
        // 优惠券新增确定按钮
        addVoucherSureButton() {

        },
        // 编辑按钮
        editVoucherButton(index, row) {
            this.voucherDialogTitle = '优惠券编辑'
            this.thisVoucher = row
            this.voucherDialogShow = true
        },
        // 删除按钮
        deleteVoucherButton(index, row) {
            console.log(row);
        }
    }
}
</script>

<style>

</style>
