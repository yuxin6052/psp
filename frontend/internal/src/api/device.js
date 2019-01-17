import {axios} from './index'

const device = {
    
    async addDevice(form) {
        let res = await axios.post("/admin/device/v1/create", form);
        return res.data;
    },

    async searchDevice(form) {
        
         
        let res = await axios.post('/admin/device/v1/searchDevice',form);
        return res.data;
    },

    
     
}

export default device;