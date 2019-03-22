<template>
    <div class="main">
        <!--搜索-->
        <div @keyup.enter="search">
            <el-row :gutter="10">
                <el-col :xs="24" :sm="12" :lg="5" class="margin-top-10">
                    <el-input v-model="params.hIsbn" placeholder="请输入书号"></el-input>
                </el-col>
                <el-col :xs="24" :sm="12" :lg="5" class="margin-top-10">
                    <el-input v-model="params.hName" placeholder="请输入书名"></el-input>
                </el-col>
                <el-col :xs="24" :sm="12" :lg="5" class="margin-top-10">
                    <el-select v-model="params.atName" placeholder="请选择所属区域">
                        <el-option v-for="item in areaOptions" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
                <el-col :xs="24" :sm="12" :lg="5" class="xs-text-center margin-top-10">
                    <el-button type="primary" @click="search">&nbsp;搜&nbsp;&nbsp;索&nbsp;</el-button>
                    <el-button @click="reset">&nbsp;重&nbsp;&nbsp;置&nbsp;</el-button>
                </el-col>
            </el-row>
        </div>
        <!--搜索end-->
        <!--表格-->
        <el-table class="product-table" :data="productList" border style="width: 100%">
            <el-table-column label="序号" width="70px" align="center">
                <template scope="scope"> {{ scope.$index+1 }}</template>
            </el-table-column>
            <el-table-column prop="hIsbn" label="书号" width="145px" align="center" sortable></el-table-column>
            <el-table-column prop="hName" label="书名" width="315px" align="center" sortable>
                <template scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small" style="text-decoration: underline;">
                        <strong>{{scope.row.hName}}</strong>
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column prop="atName" label="架号" width="95px" align="center"></el-table-column>
            <el-table-column prop="paName" label="区域名称" width="95px" align="center"></el-table-column>
            <el-table-column prop="hId" label="唯一码" width="200px" align="center"></el-table-column>
            <el-table-column prop="hTypeName" label="一级分类" width="95px" align="center"></el-table-column>
            <el-table-column prop="hTypeRealName" label="末级分类" width="95px" align="center"></el-table-column>
            <el-table-column prop="stationId" label="站点" width="95px" align="center"></el-table-column>
            <el-table-column prop="paId" label="区域编码" width="125px" align="center"></el-table-column>
            <el-table-column prop="inTime" label="进入时间" width="120px" align="center"></el-table-column>
            <el-table-column prop="hInputDate" label="建档时间" width="120px" align="center"></el-table-column>
            <el-table-column prop="saleAmount" label="销售数量" width="95px" align="center"></el-table-column>
            <el-table-column prop="lastTotalAmount" label="上次库存数量" width="125px" align="center"></el-table-column>
            <el-table-column prop="totalAmount" label="本次到货数量" width="125px" align="center"></el-table-column>
            <el-table-column prop="stockHAmount" label="库存数量" width="95px" align="center"></el-table-column>
            <el-table-column prop="isShelves" label="下架否" width="90px" align="center"></el-table-column>
            <el-table-column prop="isSaleOff" label="售完否" width="90px" align="center">
                <template scope="scope">
                    {{scope.row.isSaleOff === 'N' ? '否' : '是'}}
                </template>
            </el-table-column>
            <el-table-column prop="saleDate" label="最后零售时间" width="125px" align="center"></el-table-column>
            <el-table-column prop="lastModDate" label="最后修改时间" width="125px" align="center"></el-table-column>
            <el-table-column prop="memo" label="备注" width="250px" align="center"></el-table-column>
        </el-table>
        <!--表格end-->
        <!--分页-->
        <div class="pagination">
            <el-pagination layout="total, sizes, prev, pager, next, jumper" :total="page.pageTotal" :page-size="page.pageSize" :current-page="page.currentPage" @current-change="pageChange" @size-change="sizeChange"></el-pagination>
        </div>
        <!--分页end-->
        <!--详情弹出层-->
        <el-dialog :title="productDetails.baseInfo.hName" :visible.sync="dialogTableVisible" custom-class="custom-dialog">
            <p style="margin-top:10px;"><b>基础信息</b></p>
            <el-form ref="form" :model="productDetails" label-width="100px">
                <el-row>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="HID：" class="margin-bottom-0">
                            {{productDetails.baseInfo.hId}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="书号：" class="margin-bottom-0">
                            {{productDetails.baseInfo.hIsbn}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="书名：" class="margin-bottom-0">
                            {{productDetails.baseInfo.hName}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="备注：" class="margin-bottom-0">
                            {{productDetails.baseInfo.memo}}
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <p style="margin-top:10px;" v-if="false"><b>库存信息</b></p>
            <div v-if="!isReader">
                <el-table :data="productDetails.stockList">
                    <el-table-column property="stationName" label="站点"></el-table-column>
                    <el-table-column property="sumAmount" label="数量"></el-table-column>
                    <el-table-column property="stockAttribute" label="库存名称"></el-table-column>
                </el-table>
            </div>
        </el-dialog>
        <!--详情弹出层end-->
    </div>
</template>

<script>
export default {
    props: {
        isReader: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            params: {
                hIsbn: "",
                hName: "",
                atName: ""
            },
            page: {
                currentPage: 1,
                pageSize: 50,
                pageTotal: 0
            },
            areaOptions: [
                { label: "--", value: "" },
                { label: "新品", value: "新品" },
                { label: "畅销", value: "畅销" },
                { label: "常销", value: "常销" },
                { label: "断货", value: "断货" },
                { label: "下架", value: "下架" }
            ],
            productList: [],
            gridData: [],
            dialogTableVisible: false,
            productDetails: {
                baseInfo: {},
                productType: {},
                stockList: []
            }
        };
    },
    methods: {
        search() {
            this.loadData(this.params, this.page);
        },
        reset() {
            for (let i in this.params) {
                this.params[i] = "";
            }
        },
        loadData(params, page) {
            let isEmpty = false;
            for (let key in params) {
                if (params[key]) {
                    isEmpty = true;
                }
            }
            if (isEmpty) {
                let data = Object.assign({}, params, page);
                this.$http
                    .post(
                        "/api/reader/GoodsCirculationController/queryByCondition",
                        data
                    )
                    .then(res => {
                        if (res.data.code === "000") {
                            this.page.pageTotal = res.data.result.totalPages;
                            this.productList =
                                res.data.result.goodsCirculationList;
                            if (this.productList.length === 0) {
                                this.$notify({
                                    title: "提示",
                                    message: "没有数据",
                                    type: "warning",
                                    duration: 4000
                                });
                            }
                        }
                    });
            } else {
                this.$alert("查询条件至少需要填写一个", "提示");
            }
        },
        pageChange(val) {
            this.page.currentPage = val;
            this.loadData(this.params, this.page);
        },
        sizeChange(val) {
            this.page.pageSize = val;
            this.loadData(this.params, this.page);
        },
        handleClick(row) {
            this.$http
                .post(
                    "/api/reader/productController/showDetailsById?hId=" +
                        row.hId
                )
                .then(res => {
                    if (res.data.code === "000") {
                        this.productDetails.baseInfo = row;
                        this.productDetails.productType =
                            res.data.result.productType;
                        this.productDetails.stockList =
                            res.data.result.stockList;
                        console.log(this.productDetails.baseInfo);

                        this.dialogTableVisible = true;
                    }
                });
        }
    }
};
</script>