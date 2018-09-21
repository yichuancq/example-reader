<template>
    <div class="main">
        <!--搜索-->
        <div @keyup.enter="search">
            <el-row :gutter="10">
                <el-col :xs="24" :sm="12" :lg="4" class="margin-top-10">
                    <el-input v-model="params.hIsbn" placeholder="请输入书号"></el-input>
                </el-col>
                <el-col :xs="24" :sm="12" :lg="4" class="margin-top-10">
                    <el-input v-model="params.hName" placeholder="请输入书名"></el-input>
                </el-col>
                <el-col :xs="24" :sm="12" :lg="4" class="margin-top-10">
                    <el-date-picker v-model="params.startTime" type="date" placeholder="操作开始日期"
                                    :editable="false"></el-date-picker>
                </el-col>
                <el-col :xs="24" :sm="12" :lg="4" class="margin-top-10">
                    <el-date-picker v-model="params.endTime" type="date" placeholder="操作结束日期"
                                    :editable="false"></el-date-picker>
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
            <el-table-column prop="hIsbn" label="书号" width="180px" align="center" sortable></el-table-column>
            <el-table-column prop="hName" label="书名" width="315px" align="center" sortable>
                <template scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small"
                               style="text-decoration: underline;">
                        <strong>{{scope.row.hName}}</strong>
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column prop="shelfId" label="架号" width="95px" align="center"></el-table-column>
            <el-table-column prop="paNameFrom" label="当前分区" width="175px" align="center"></el-table-column>
            <el-table-column prop="paNameTo" label="移至分区" width="95px" align="center"></el-table-column>
            <el-table-column prop="hId" label="唯一码" width="190px" align="center"></el-table-column>
            <el-table-column prop="typeName" label="一级分类 " width="95px" align="center"></el-table-column>
            <el-table-column prop="hTypeRealName" label="三级分类" width="95px" align="center"></el-table-column>
            <el-table-column prop="stationId" label="站点" width="95px" align="center"></el-table-column>
            <el-table-column prop="totalAmount" label="进入数量" width="95px" align="center"></el-table-column>
            <el-table-column prop="stockHAmount" label="当前库存" width="95px" align="center"></el-table-column>
            <el-table-column prop="cfDateTime" label="操作时间" width="120px" align="center"></el-table-column>
            <el-table-column prop="memo" label="备注" width="250"></el-table-column>
        </el-table>
        <!--表格end-->
        <!--分页-->
        <div class="pagination">
            <el-pagination layout="total, sizes, prev, pager, next, jumper"
                           :total="page.pageTotal"
                           :page-size="page.pageSize"
                           :current-page="page.currentPage"
                           @current-change="pageChange"
                           @size-change="sizeChange"
            ></el-pagination>
        </div>
        <!--分页end-->
        <!--详情弹出层-->
        <el-dialog :title="productDetails.baseInfo.hName" :visible.sync="dialogTableVisible"
                   custom-class="custom-dialog">
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
                    hIsbn: '',
                    hName: '',
                    startTime: '',
                    endTime: ''
                },
                page: {
                    currentPage: 1,
                    pageSize: 50,
                    pageTotal: 0
                },
                areaOptions: [
                    {label: '新品', value: '新品'},
                    {label: '畅销', value: '畅销'},
                    {label: '常销', value: '常销'},
                    {label: '断货', value: '断货'},
                    {label: '下架', value: '下架'}
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
            search(){
                this.loadData(this.params, this.page);
            },
            reset(){
                for (let i in this.params) {
                    this.params[i] = '';
                }
            },
            loadData(params, page){
                let isEmpty = _.find(params, function (item) {
                    return item !== ''
                });
                if (isEmpty) {
                    let newParams = {
                        hIsbn: params.hIsbn,
                        hName: params.hName
                    };
                    if (typeof params.startTime === 'object') {
                        newParams.startTime = this.getDateFormat(params.startTime, 'yyyy-MM-dd');
                    } else {
                        newParams.startTime = params.startTime;
                    }
                    if (typeof params.endTime === 'object') {
                        newParams.endTime = this.getDateFormat(params.endTime, 'yyyy-MM-dd');
                    } else {
                        newParams.endTime = params.endTime;
                    }
                    let data = _.extend({}, newParams, page);
                    this.$http.post('/api/reader/GoodsCirculationController/queryLastGoodsCirculationByCondition', data).then((res) => {
                        if (res.data.code === '000') {
                            this.page.pageTotal = res.data.result.totalPages;
                            this.productList = res.data.result.lastGoodsCirculationList;
                            if (this.productList.length === 0) {
                                this.$notify({
                                    title: '提示',
                                    message: '没有数据',
                                    type: 'warning',
                                    duration: 4000
                                });
                            }
                        }
                    });
                } else {
                    this.$alert('查询条件至少需要填写一个', '提示');
                }
            },
            pageChange(val){
                this.page.currentPage = val;
                this.loadData(this.params, this.page);
            },
            sizeChange(val){
                this.page.pageSize = val;
                this.loadData(this.params, this.page);
            },
            getDateFormat(date, fmt){
                let o = {
                    "M+": date.getMonth() + 1, //月份
                    "d+": date.getDate(), //日
                    "h+": date.getHours() % 12 == 0 ? 12 : date.getHours() % 12, //小时
                    "H+": date.getHours(), //小时
                    "m+": date.getMinutes(), //分
                    "s+": date.getSeconds(), //秒
                    "q+": Math.floor((date.getMonth() + 3) / 3), //季度
                    "S": date.getMilliseconds() //毫秒
                };
                let week = {
                    "0": "/u65e5",
                    "1": "/u4e00",
                    "2": "/u4e8c",
                    "3": "/u4e09",
                    "4": "/u56db",
                    "5": "/u4e94",
                    "6": "/u516d"
                };
                if (/(y+)/.test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
                }
                if (/(E+)/.test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[date.getDay() + ""]);
                }
                for (let k in o) {
                    if (new RegExp("(" + k + ")").test(fmt)) {
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                    }
                }
                return fmt;
            },
            handleClick(row) {
                this.$http.post('/api/reader/productController/showDetailsById?hId=' + row.hId).then((res) => {
                    if (res.data.code === '000') {
                        this.productDetails.baseInfo = row;
                        this.productDetails.productType = res.data.result.productType;
                        this.productDetails.stockList = res.data.result.stockList;
                        console.log(this.productDetails.baseInfo);

                        this.dialogTableVisible = true;
                    }
                });
            }
        }
    }
</script>