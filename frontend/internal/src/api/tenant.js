import {axios} from './index'

const tenant = {
    
    async addTenant(form) {
        let res = await axios.post("/admin/user/v1/createTenant", form);
        return res.data;
    },

    async getTenantList(form) {
        let res = await axios.post("/admin/user/v1/listTenant", form);
        return res.data;
    },

    
     
}

export default tenant;