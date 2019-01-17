import {axios} from './index'

const shipment = {
    async getShipmentList(filter) {
        let res = await axios.post("/mgr/shipment/v1/listShipments", filter);
        return res.data;
    },
    async addShipment(form) {
        let res = await axios.post("/mgr/shipment/v1/createShipment", form);
        return res.data;
    },
    async getShipmentDetail(filter) {
        let res = await axios.get(`/mgr/shipment/v1/getShipmentById/${filter.status}/${filter.shipmentId}?showNoMonitorSite=true`)
        return res.data;
    },
    async getMonitorDataByShipmentIdAndTagId(shipmentId, tagId) {
      let res = await axios.get(`/mgr/shipment/v1/getMonitorDataByShipmentIdAndTagId/${shipmentId}/${tagId}`);
      return res
    },
}

export default shipment;