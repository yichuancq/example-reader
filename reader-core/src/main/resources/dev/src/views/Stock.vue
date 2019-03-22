<template>
    <div class="main">
        <!--搜索-->
        <div @keyup.enter="search">
            <el-row :gutter="10">
                <el-col :xs="24" :sm="12" :lg="4" class="margin-top-10">
                    <div class="el-input">
                        <input v-model="params.isbn" placeholder="请输入书号" type="text" class="el-input__inner">
                    </div>
                </el-col>
                <el-col :xs="24" :sm="12" :lg="4" class="margin-top-10">
                    <div class="el-input">
                        <input v-model="params.hOutPutPrice" placeholder="请输入价格" type="text" class="el-input__inner">
                    </div>
                </el-col>
                <el-col :xs="24" :sm="12" :lg="4" class="margin-top-10">
                    <div class="el-input">
                        <input v-model="params.hName" placeholder="请输入书名" type="text" class="el-input__inner">
                    </div>
                </el-col>
                <el-col :xs="24" :sm="12" :lg="4" class="margin-top-10">
                    <div class="el-input">
                        <input v-model="params.hWriter" placeholder="请输入作者" type="text" class="el-input__inner">
                    </div>
                </el-col>
                <el-col :xs="24" :lg="5" class="text-center margin-top-10">
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
            <el-table-column prop="saleStatus" label="销售状态" width="100px" align="center">
                <template scope="scope">
                    <el-tag :type="scope.row.saleStatus === 'is_sale' ? 'success' : ''" close-transition>
                        {{scope.row.saleStatus==='is_sale'? '在售':'售罄' }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="hIsbn" label="书号" sortable width="220px">
                <template scope="scope">
                    {{scope.row.hIsbn}}
                </template>
            </el-table-column>
            <el-table-column prop="hName" label="书名" sortable min-width="200px">
                <template scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small" style="text-decoration: underline;">
                        <strong>{{scope.row.hName}}</strong>
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column prop="hOutPutPrice" label="价格" sortable width="120px">
                <template scope="scope">
                    ¥{{parseFloat(scope.row.hOutPutPrice).toFixed(2)}}
                </template>
            </el-table-column>
            <el-table-column prop="fourthTypeName" label="类别" width="130px">
                <template scope="scope">
                    {{scope.row.fourthTypeName}}
                </template>
            </el-table-column>
            <el-table-column prop="stockAmount" label="库存" width="120px" v-if="!isReader">
                <template scope="scope">
                    <span style="color: red;">{{scope.row.stockAmount}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="fourthTypeName" label="架位" width="120px">
                <template scope="scope">
                    {{scope.row.atName}}
                </template>
            </el-table-column>
            <el-table-column prop="fourthTypeName" label="作者" width="150px">
                <template scope="scope">
                    {{scope.row.hWriter}}
                </template>
            </el-table-column>
            <el-table-column prop="fourthTypeName" label="出版社" width="200px">
                <template scope="scope">
                    {{scope.row.pubName}}
                </template>
            </el-table-column>
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
                        <el-form-item label="书　号：" class="margin-bottom-0">
                            {{productDetails.baseInfo.hIsbn}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="价　格：" class="margin-bottom-0">
                            {{productDetails.baseInfo.hOutPutPrice}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="书　名：" class="margin-bottom-0">
                            {{productDetails.baseInfo.hName}}
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="出版社：" class="margin-bottom-0">
                            {{productDetails.baseInfo.pubName}}
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div class="line"></div>
            <p style="margin-top:10px;"><b>分类信息</b></p>
            <el-form ref="form" label-width="100px">
                <el-row>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="一级分类：" class="margin-bottom-0">
                            {{productDetails.productType.firstTypeName}}({{productDetails.productType.firstTypeId}})
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="二级分类：" class="margin-bottom-0">
                            {{productDetails.productType.secondTypeName}}({{productDetails.productType.secondTypeId}})
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="三级分类：" class="margin-bottom-0">
                            {{productDetails.productType.thirdTypeName}}({{productDetails.productType.thirdTypeId}})
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :lg="12">
                        <el-form-item label="四级分类：" class="margin-bottom-0">
                            {{productDetails.productType.fourthTypeName}}({{productDetails.productType.fourthTypeId}})
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
                isbn: "",
                hName: "",
                hOutPutPrice: "",
                hWriter: ""
            },
            page: {
                currentPage: 1,
                pageSize: 50,
                pageTotal: 0
            },
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
            this.page.currentPage = 1;
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
                        "/api/reader/productController/queryByCondition",
                        data
                    )
                    .then(res => {
                        if (res.data.code === "000") {
                            this.page.pageTotal = res.data.result.totalPages;
                            this.productList = res.data.result.productList;
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
                this.$alert("书号、价格、书名、作者必须至少填写一个", "提示");
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