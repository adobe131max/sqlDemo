<template>
    <div class="home">
        <div class="dependency">
            <div class="search">
                <span style="padding-left: 12px;">Key</span>
                <el-input v-model="search.key" autocomplete="off"></el-input>
                <span>Value</span>
                <el-input v-model="search.value" autocomplete="off"></el-input>
                <el-button @click="searchDep" type="primary" plain>查 询</el-button>
                <el-button @click="clear" type="info" plain>清 空</el-button>
            </div>
            <el-table ref="multipleTable" :data="dependencies" style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="key" label="Key" width="200"></el-table-column>
                <el-table-column prop="value" label="Value" width="200"></el-table-column>
                <el-table-column prop="id" label="Operation">
                    <template slot-scope="scope">
                        <el-button style="margin-right: 10px;" size="mini" @click="modify(scope.row.id)">编辑</el-button>

                        <el-dialog title="编辑依赖" :visible.sync="dialogFormVisible">
                            <el-form :model="form">
                                <el-form-item label="Key" label-width="80px">
                                    <el-input v-model="form.key" autocomplete="off"></el-input>
                                </el-form-item>
                                <el-form-item label="Value" label-width="80px">
                                    <el-input v-model="form.value" autocomplete="off"></el-input>
                                </el-form-item>
                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">取 消</el-button>
                                <el-button type="primary" @click="modifyed">确 定</el-button>
                            </div>
                        </el-dialog>

                        <el-button size="mini" type="danger" @click="deleteById(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div style="margin-top: 20px">
                <el-button style="float: right;" @click="newmodify" type="success">新增依赖</el-button>

                <el-dialog title="新增依赖" :visible.sync="newdialogFormVisible">
                    <el-form :model="newform">
                        <el-form-item label="Key" label-width="80px">
                            <el-input v-model="newform.key" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="Value" label-width="80px">
                            <el-input v-model="newform.value" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="newdialogFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="add">确 定</el-button>
                    </div>
                </el-dialog>

                <el-button @click="deleteByIds" type="danger">一键删除</el-button>
                <el-button @click="toggleSelection" type="primary">取消选择</el-button>
            </div>
        </div>
        <div class="func">
            <el-input placeholder="请输入内容" v-model="input" clearable></el-input>
            <div class="res">{{ result }}</div>
            <el-button @click="calculate" type="primary" size="medium" round>计算闭包</el-button>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "MySql",
    data() {
        return {
            dependencies: [],
            multipleSelection: [],   // 多选删除选中项
            dialogFormVisible: false,
            newdialogFormVisible: false,
            form: {
                id: 0,
                key: "",
                value: "",
            },
            newform: {
                key: '',
                value: ''
            },
            search: {
                key: '',
                value: ''
            },
            input: '',
            result: ''
        };
    },
    methods: {
        toggleSelection() {
            this.$refs.multipleTable.clearSelection();
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        modify(id) {
            this.dialogFormVisible = true
            this.selectById(id)
        },
        newmodify() {
            this.newdialogFormVisible = true
        },
        modifyed() {
            this.dialogFormVisible = false
            let a = async () => {
                try {
                    const jsonData = JSON.stringify(this.form);
                    const response = await axios.put('http://localhost:9001/home', jsonData, {
                        headers: {
                            'Content-Type': 'application/json',
                            'token': localStorage.getItem('token')
                        }
                    });
                    if (response.data.code === 1) {
                        this.$message({ type: "success", message: "更新成功" });
                    } else {
                        this.$router.push({ name: 'denglu' })
                    }
                } catch (error) {
                    this.$message({ type: "error", message: "更新失败" });
                }
            }
            a().then(this.selectAll)
            this.form = { id: 0, key: '', value: '' }
        },
        selectAll() {
            // axios
            //     .get("http://localhost:9001/home", {
            //         headers: {
            //             'Content-Type': 'application/json',
            //             'token': localStorage.getItem('token')
            //         }
            //     })
            //     .then((response) => {
            //         if (response.data.code === 1) {
            //             this.dependencies = response.data.data;
            //         } else {
            //             this.$router.push({ name: 'denglu' })
            //         }
            //     })
            //     .catch((error) => {
            //         console.log(error);
            //     });
            axios.get("http://localhost:9001/home", {
                headers: {
                    'Content-Type': 'application/json',
                    'token': localStorage.getItem('token')
                }
            })
                .then((response) => {
                    if (response.data.code === 1) {
                        this.dependencies = response.data.data;
                    } else {
                        this.$router.push({ name: 'denglu' });
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        selectById(id) {
            axios
                .get(`http://localhost:9001/home/${id}`, {
                    headers: {
                        'Content-Type': 'application/json',
                        'token': localStorage.getItem('token')
                    }
                })
                .then((response) => {
                    if (response.data.code === 1) {
                        this.form.id = response.data.data.id
                        this.form.key = response.data.data.key
                        this.form.value = response.data.data.value
                    } else {
                        this.$router.push({ name: 'denglu' })
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        deleteById(id) {
            this.$confirm("此操作将永久删除该依赖", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    axios
                        .delete(`http://localhost:9001/home/${id}`, {
                            headers: {
                                'Content-Type': 'application/json',
                                'token': localStorage.getItem('token')
                            }
                        })
                        .then((response) => {
                            if (response.data.code === 1) {
                                this.selectAll();
                                this.$message({ type: "success", message: "删除成功!" });
                            } else {
                                this.$router.push({ name: 'denglu' })
                            }
                        })
                        .catch((error) => {
                            console.log(error);
                        });
                })
                .catch(() => {
                    this.$message({ type: "info", message: "已取消删除" });
                });
        },
        deleteByIds() {
            let ids = []
            this.multipleSelection.forEach(e => ids.push(e.id))
            if (ids.length > 0) {
                this.$confirm("此操作将永久删除该依赖", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() => {
                        axios
                            .delete(`http://localhost:9001/home/ids/${ids}`, {
                                headers: {
                                    'Content-Type': 'application/json',
                                    'token': localStorage.getItem('token')
                                }
                            })    // 拼接数组以逗号分隔
                            .then((response) => {
                                if (response.data.code === 1) {
                                    this.selectAll();
                                    this.$message({ type: "success", message: "删除成功" });
                                } else {
                                    this.$router.push({ name: 'denglu' })
                                }
                            })
                            .catch((error) => {
                                console.log(error);
                            });
                    })
                    .catch(() => {
                        this.$message({ type: "info", message: "已取消删除" });
                    });
            }
        },
        add() {
            if (this.newform.key != '' && this.newform.value != '') {
                this.newdialogFormVisible = false
                let a = async () => {
                    try {
                        const jsonData = JSON.stringify(this.newform);
                        const response = await axios.post('http://localhost:9001/home', jsonData, {
                            headers: {
                                'Content-Type': 'application/json',
                                'token': localStorage.getItem('token')
                            }
                        });
                        if (response.data.code === 1) {
                            this.$message({ type: "success", message: "增加成功" });
                        } else {
                            this.$router.push({ name: 'denglu' })
                        }
                    } catch (error) {
                        this.$message({ type: "error", message: "增加失败" });
                    }
                }
                a().then(this.selectAll)
                this.newform = { key: '', value: '' }
            } else {
                this.$message({ type: 'error', message: '输入不能为空' })
            }
        },
        calculate() {
            if (this.input != '') {
                axios
                    .get(`http://localhost:9001/home/cal/${this.input}`, {
                        headers: {
                            'Content-Type': 'application/json',
                            'token': localStorage.getItem('token')
                        }
                    })
                    .then((response) => {
                        if (response.data.code === 1) {
                            this.result = response.data.data
                        } else {
                            this.$router.push({ name: 'denglu' })
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            } else {
                this.result = ''
            }
        },
        searchDep() {
            let url = 'http://localhost:9001/home'
            if (this.search.key !== '' && this.search.value !== '') {
                url = url + `?key=${this.search.key}&value=${this.search.value}`
            } else if (this.search.key !== '') {
                url = url + `?key=${this.search.key}`
            } else if (this.search.value !== '') {
                url = url + `?key=${this.search.value}`
            }
            axios.get(url, {
                headers: {
                    'Content-Type': 'application/json',
                    'token': localStorage.getItem('token')
                }
            })
                .then((response) => {
                    if (response.data.code === 1) {
                        this.dependencies = response.data.data
                    } else {
                        this.$router.push({ name: 'denglu' })
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        clear() {
            this.search.key = ''
            this.search.value = ''
            this.selectAll()
        }
    },
    beforeMount() {
        axios.defaults.headers.get['token'] = localStorage.getItem('token')
        // async / await 解决js异步问题
        let a = async () => {
            try {
                const response = await axios.get("http://localhost:9001/home");
                if (response.data.code === 1) {
                    this.dependencies = response.data.data;
                } else {
                    this.$router.push({ name: 'denglu' })
                }
            } catch (error) {
                console.log(error);
            }
        }
        let b = () => {
            if (this.dependencies.length === 0) {
                this.$message({ message: "获取失败", type: "error" });
            } else {
                this.$message({ message: "获取成功", type: "success" });
            }
        }
        a().then(b)
    }
};
</script>

<style scoped>
.home {
    display: flex;
    padding: 20px;
    height: 100%;
}
.dependency {
    width: 60%;
    max-height: 100%;
    padding: 14px 20px;
    border-radius: 14px;
    overflow: hidden;
    background-color: #fff;
    overflow: auto;
}
.func {
    width: 37%;
    height: 300px;
    padding: 10px;
    margin-left: 30px;
    border-radius: 14px;
    overflow: hidden;
    background-color: #fff;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
.el-dialog {
    width: 36%;
}
.func .el-input {
    margin-top: 30px;
    width: 60%;
    height: 40px;
}
.func .res {
    width: 60%;
    height: 40px;
    margin-top: 20px;
    margin-bottom: 20px;
    text-align: center;
    font-size: 22px;
}
.func .el-button {
    width: 50%;
    height: 40px;
}
.search {
    padding-bottom: 8px;
}
.search .el-input {
    width: 26%;
    padding: 0 18px;
}
.search span {
    color: #606060;
}
</style>